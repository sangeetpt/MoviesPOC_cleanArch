package com.test.moviesdemo.features.movies

import com.test.moviesdemo.core.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}

