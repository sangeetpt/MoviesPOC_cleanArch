package com.test.moviesdemo.core.platform

import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.test.moviesdemo.core.exception.Failure
import com.test.moviesdemo.core.exception.Failure.NetworkConnection
import com.test.moviesdemo.kotlin.AndroidTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))

class BaseViewModelTest : AndroidTest() {

    @Test fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(NetworkConnection)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}