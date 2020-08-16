package com.test.moviesdemo.features.login

import android.text.TextUtils
import com.test.moviesdemo.core.utilities.Constants

class ValidateUser() {

    fun isValidUserName(target: CharSequence?): Boolean {
        return (!TextUtils.isEmpty(target) && target!!.contains(Constants.DEFAULT_USERNAME))
    }

    fun isValidPassword(target: CharSequence?): Boolean {
        return (!TextUtils.isEmpty(target) && target!!.contains(Constants.DEFAULT_PASSWORD))
    }
}
