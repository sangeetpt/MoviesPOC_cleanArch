package com.test.moviesdemo.features.movies

import android.os.Build
import com.nhaarman.mockitokotlin2.verify
import com.test.moviesdemo.core.functional.Either
import com.test.moviesdemo.core.platform.NetworkHandler
import com.test.moviesdemo.kotlin.UnitTest
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.annotation.Config
import retrofit2.Call
import retrofit2.Response

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))
class MoviesRepositoryTest : UnitTest() {

    private lateinit var networkRepository: MoviesRepository.Network

    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var service: MoviesService

    @Mock private lateinit var moviesCall: Call<List<MovieEntity>>
    @Mock private lateinit var moviesResponse: Response<List<MovieEntity>>

    @Before fun setUp() {
        networkRepository = MoviesRepository.Network(networkHandler, service)
    }

    @Test fun `should return empty list by default`() {
        Mockito.`when`(networkHandler.isConnected ).thenReturn(true)
        Mockito.`when`(moviesResponse.body() ).thenReturn(null)
        Mockito.`when`(moviesResponse.isSuccessful ).thenReturn(true)
        Mockito.`when`(moviesCall.execute()  ).thenReturn(moviesResponse)
        Mockito.`when`( service.movies() ).thenReturn(moviesCall)

        val movies = networkRepository.movies()
        movies shouldEqual  Either.Right(emptyList<Movie>())
        verify(service).movies()
    }

    @Test fun `should get movie list from service`() {
        Mockito.`when`(networkHandler.isConnected).thenReturn( true)
        Mockito.`when`(moviesResponse.body()).thenReturn( listOf(MovieEntity("Dawn of the Planet of the Apes", "https://api.androidhive.info/json/movies/1.jpg",2014)))
        Mockito.`when`(moviesResponse.isSuccessful).thenReturn( true)
        Mockito.`when`( moviesCall.execute()).thenReturn( moviesResponse)
        Mockito.`when`( service.movies()).thenReturn( moviesCall)


        val movies = networkRepository.movies()
        movies shouldEqual Either.Right(listOf(Movie("Dawn of the Planet of the Apes", "https://api.androidhive.info/json/movies/1.jpg",2014)))
        verify(service).movies()
    }
}