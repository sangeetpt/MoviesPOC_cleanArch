package com.test.moviesdemo.core.navigation

import android.content.Context
import com.test.moviesdemo.features.login.Authenticator
import com.test.moviesdemo.features.login.LoginActivity
import com.test.moviesdemo.features.movies.MoviesActivity
import com.test.moviesdemo.features.movies.UserLogin
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
//        when (authenticator.userLoggedIn()) {
        when (UserLogin.loggedIn) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))
}


