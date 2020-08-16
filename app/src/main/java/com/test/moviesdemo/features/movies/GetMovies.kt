package com.test.moviesdemo.features.movies

import com.test.moviesdemo.core.interactor.UseCase
import com.test.moviesdemo.core.interactor.UseCase.None
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}
