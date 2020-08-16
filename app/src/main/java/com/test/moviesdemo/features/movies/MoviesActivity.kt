package com.test.moviesdemo.features.movies

import android.content.Context
import android.content.Intent
import com.test.moviesdemo.core.platform.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()
}
