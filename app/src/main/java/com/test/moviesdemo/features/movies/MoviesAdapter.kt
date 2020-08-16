package com.test.moviesdemo.features.movies

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.test.moviesdemo.R
import com.test.moviesdemo.core.extension.inflate
import com.test.moviesdemo.core.extension.loadFromUrl
import kotlinx.android.synthetic.main.row_movie.view.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(),Filterable {


    var collection: List<MovieView> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    var movieFilterList = collection



    init {
        movieFilterList = collection
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])


    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: MovieView) {
            itemView.moviePoster.loadFromUrl(movieView.image)
            itemView.title.text = movieView.title
            itemView.year.text = movieView.releaseYear.toString()
        }
    }



    override fun getFilter(): Filter {
        return object : Filter() {

            //test
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                movieFilterList = if (charSearch.isEmpty()) {
                    movieFilterList
                } else {

                    val resultList = ArrayList<MovieView>()
                    for (movie in movieFilterList) {
                        if (movie.title?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(movie)
                        }
                    }
                    resultList

                }
                val filterResults = FilterResults()
                filterResults.values = movieFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                collection = (results?.values as? ArrayList<MovieView>)!!
                notifyDataSetChanged()
            }
        }
    }
}
