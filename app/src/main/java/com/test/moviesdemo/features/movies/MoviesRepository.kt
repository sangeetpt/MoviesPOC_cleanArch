package com.test.moviesdemo.features.movies

import com.test.moviesdemo.core.exception.Failure
import com.test.moviesdemo.core.exception.Failure.NetworkConnection
import com.test.moviesdemo.core.exception.Failure.ServerError
import com.test.moviesdemo.core.functional.Either
import com.test.moviesdemo.core.functional.Either.Left
import com.test.moviesdemo.core.functional.Either.Right
import com.test.moviesdemo.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> request(service.movies(), { it.map { movieEntity -> movieEntity.toMovie() } }, emptyList())
                false, null -> Left(NetworkConnection)
            }
        }


        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}
