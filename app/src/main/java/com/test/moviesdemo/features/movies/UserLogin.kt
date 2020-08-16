package com.test.moviesdemo.features.movies

data class UserLogin(val login: Boolean) {

    companion object {
        var loggedIn : Boolean = false
    }
}
