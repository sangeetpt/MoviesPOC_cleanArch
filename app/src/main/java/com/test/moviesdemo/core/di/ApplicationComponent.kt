package com.test.moviesdemo.core.di

import com.test.moviesdemo.AndroidApplication
import com.test.moviesdemo.core.di.viewmodel.ViewModelModule
import com.test.moviesdemo.core.navigation.RouteActivity
import com.test.moviesdemo.features.login.LoginActivity
import com.test.moviesdemo.features.login.LoginFragment
import com.test.moviesdemo.features.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)
    fun inject(loginActivity: LoginActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(loginFragment: LoginFragment)
}
