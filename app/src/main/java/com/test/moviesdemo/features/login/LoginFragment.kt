package com.test.moviesdemo.features.login

import android.content.Context
import android.os.Bundle
import android.view.View
import com.test.moviesdemo.R
import com.test.moviesdemo.core.extension.viewModel
import com.test.moviesdemo.core.platform.BaseFragment
import com.test.moviesdemo.features.movies.MoviesActivity
import com.test.moviesdemo.features.movies.UserLogin
import kotlinx.android.synthetic.main.fragment_login.*

private lateinit var loginViewModel: LoginViewModel
private lateinit var  authenticator: Authenticator
private lateinit var  validateUser: ValidateUser

class LoginFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        validateUser = ValidateUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            onLoginClick()
        }
    }

    private fun onLoginClick(){
        loginViewModel = viewModel(viewModelFactory) {
            validateUserName()
        }
    }

    private fun validateUserName() {
        if (validateUser.isValidUserName(username.text)){
            validatePassword()
        }else{
            context?.let { showToast(it,resources.getString(R.string.invalid_username)) }
        }
    }

    private fun validatePassword(){
    if (validateUser.isValidPassword(password.text)){
        context?.let { showToast(it,resources.getString(R.string.logging_in)) }
            UserLogin.loggedIn = true
            context?.let { showMovies(it) }
        }else{
        context?.let { showToast(it,resources.getString(R.string.invalid_password)) }
        }
    }

    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

}
