package com.test.moviesdemo.features.movies

data class MovieEntity(private val title: String, private val image: String , private val releaseYear: Int) {
    fun toMovie() = Movie(title, image , releaseYear)
}
