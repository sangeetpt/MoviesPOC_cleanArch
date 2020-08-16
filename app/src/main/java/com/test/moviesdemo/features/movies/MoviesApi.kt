package com.test.moviesdemo.features.movies

import retrofit2.Call
import retrofit2.http.GET

internal interface MoviesApi {
    companion object {
        private const val MOVIES = "movies.json"
    }

    @GET(MOVIES) fun movies(): Call<List<MovieEntity>>
}
