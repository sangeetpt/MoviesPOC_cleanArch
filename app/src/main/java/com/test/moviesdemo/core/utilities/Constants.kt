package com.test.moviesdemo.core.utilities

class Constants {
    companion object {
        const val BASE_URL = "https://api.androidhive.info/json/";
        const val END_URL = "movies.json";
        const val SHARED_PREFERENCES = "SharedPreferences"
        const val KEYSTORE_ENCRPT_UNAME = "test_uname"
        const val KEYSTORE_ENCRPT_PWD = "test_pwd"
        const val UNAME_PREF = "username_pref"
        const val PWD_PREF = "password_pref"
        const val LOGIN_FIRSTIME = "isFirstTime"
        const val DEFAULT_USERNAME = "admin"
        const val DEFAULT_PASSWORD = "admin"
        const val DEFAULT_ERROR_MSG = "Something went wrong, please try again"
        const val TIMEOUT_ERROR_MSG = "Request timed out, please try again"
        const val CONNECTION_ERROR_MSG = "Unable to connect server, please try again"
    }


}