package com.test.moviesdemo.features.login

import com.test.moviesdemo.core.platform.BaseViewModel
import javax.inject.Inject

class LoginViewModel
@Inject constructor() : BaseViewModel() {
    fun loadValidation() {
            ValidateUser()
    }
}