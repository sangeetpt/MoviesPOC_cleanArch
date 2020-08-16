package com.test.moviesdemo.features.movies

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.moviesdemo.R
import com.test.moviesdemo.core.exception.Failure
import com.test.moviesdemo.core.extension.*
import com.test.moviesdemo.core.navigation.Navigator
import com.test.moviesdemo.core.platform.BaseFragment
import com.test.moviesdemo.features.login.LoginActivity
import com.test.moviesdemo.features.movies.MovieFailure.ListNotAvailable
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
        setSearch()
        logout()
    }

    private fun setSearch(){
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        titleSearch.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        titleSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                moviesAdapter.filter.filter(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isEmpty()){
                    moviesAdapter.notifyDataSetChanged()
                    loadMoviesList()
                }else{
                    moviesAdapter.filter.filter(newText)
                }
                return false
            }
        })
    }

    private fun logout(){
        btnLogout.setOnClickListener {
            activity?.finish()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeView() {
        movieList.layoutManager = LinearLayoutManager(context)
        movieList.adapter = moviesAdapter
    }

    private fun loadMoviesList() {
        emptyView.invisible()
        movieList.visible()
        moviesViewModel.loadMovies()
    }


    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        moviesAdapter.movieFilterList = movies.orEmpty()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        movieList.invisible()
        emptyView.visible()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }
}
