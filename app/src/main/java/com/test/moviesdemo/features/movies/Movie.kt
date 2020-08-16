package com.test.moviesdemo.features.movies

import com.test.moviesdemo.core.extension.empty

data class Movie(val title: String, val image: String, val releaseYear:Int) {

    companion object {
        fun empty() = Movie(String.empty(), String.empty(),0)
    }
}
