package com.test.moviesdemo.features.movies

import android.os.Build
import com.test.moviesdemo.kotlin.UnitTest
import com.test.moviesdemo.core.functional.Either.Right
import com.test.moviesdemo.core.interactor.UseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers.experimental
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.annotation.Config


@Config(sdk = intArrayOf(Build.VERSION_CODES.P))
class GetMoviesTest : UnitTest() {

    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before fun setUp() {
        getMovies = GetMovies(moviesRepository)
        Mockito.`when`(moviesRepository.movies()).thenReturn(Right(listOf(Movie.empty())))

    }

    @Test fun `should get data from repository`() {
        runBlocking { getMovies.run(UseCase.None()) }

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)
    }
}
