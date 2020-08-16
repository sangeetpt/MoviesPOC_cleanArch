package com.test.moviesdemo.features.login

import com.test.moviesdemo.core.extension.empty

data class User(val title: String, val image: String) {

    companion object {
        fun empty() = User(String.empty(), String.empty())
    }
}
