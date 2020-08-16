package com.test.moviesdemo.features.login


import android.os.Build
import android.text.TextUtils
import com.nhaarman.mockitokotlin2.any
import com.test.moviesdemo.kotlin.UnitTest
import org.amshove.kluent.any
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(Build.VERSION_CODES.P))
class LoginTest : UnitTest() {

    private val validateUser = ValidateUser()
    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Before
    public fun setup() {

    }
    @Test fun `returns default value`() {
        validateUser.isValidUserName("admin") shouldBe true
        validateUser.isValidUserName("admin") shouldBe true
    }

    @Test fun `returns empty validation`() {
        validateUser.isValidUserName("") shouldBe false
        validateUser.isValidUserName("") shouldBe false
    }

    @Test fun `returns invalid credentials`() {
        validateUser.isValidUserName("abc") shouldBe false
        validateUser.isValidUserName("abc") shouldBe false
    }
}
