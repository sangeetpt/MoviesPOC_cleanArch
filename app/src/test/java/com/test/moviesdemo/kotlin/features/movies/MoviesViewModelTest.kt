package com.test.moviesdemo.features.movies

import android.os.Build
import com.test.moviesdemo.kotlin.AndroidTest
import com.test.moviesdemo.core.functional.Either.Right
import com.test.moviesdemo.core.interactor.UseCase
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))
class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel


    @Mock private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test fun `loading movies should update live data`() {
        val moviesList = listOf(Movie("Dawn of the Planet of the Apes", "https://api.androidhive.info/json/movies/1.jpg",2014),
            Movie("District 9", "https://api.androidhive.info/json/movies/2.jpg",2009))
        `when`(runBlocking { getMovies.run(any(UseCase.None::class.java)) } ).thenReturn(Right(moviesList))

        moviesViewModel.movies.observeForever {
            it!!.size shouldEqualTo 2
            it[0].title shouldEqualTo "Dawn of the Planet of the Apes"
            it[0].image shouldEqualTo "https://api.androidhive.info/json/movies/1.jpg"
            it[0].releaseYear shouldEqualTo 2014

            it[1].title shouldEqualTo "District 9"
            it[1].image shouldEqualTo "https://api.androidhive.info/json/movies/2.jpg"
            it[1].releaseYear shouldEqualTo 2009
        }

        runBlocking { moviesViewModel.loadMovies() }
    }
}