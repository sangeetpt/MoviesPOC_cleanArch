package com.test.moviesdemo.features.login


import android.os.Build
import com.test.moviesdemo.kotlin.UnitTest
import org.amshove.kluent.shouldBe
import org.junit.Test
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))
class AuthenticatorTest : UnitTest() {

    private val authenticator = Authenticator()

    @Test fun `returns default value`() {
        authenticator.userLoggedIn() shouldBe true
    }
}
