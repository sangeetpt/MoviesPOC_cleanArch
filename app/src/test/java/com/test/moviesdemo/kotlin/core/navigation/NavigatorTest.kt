package com.test.moviesdemo.core.navigation

import android.os.Build
import com.test.moviesdemo.features.login.Authenticator
import com.test.moviesdemo.features.login.LoginActivity
import com.test.moviesdemo.features.movies.MoviesActivity
import com.nhaarman.mockitokotlin2.whenever
import com.test.moviesdemo.kotlin.AndroidTest
import com.test.moviesdemo.kotlin.shouldNavigateTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Mock private lateinit var authenticator: Authenticator

    @Before fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test fun `should forward user to login screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo  LoginActivity::class
    }

    @Test fun `should forward user to movies screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MoviesActivity::class
    }
}
