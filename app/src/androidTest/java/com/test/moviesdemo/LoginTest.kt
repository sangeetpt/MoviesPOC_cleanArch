package com.test.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.test.moviesdemo.R
import com.test.moviesdemo.features.login.LoginActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.test.moviesdemo", appContext.packageName)
    }

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)

    @Test
    fun testUsernameIsEmpty() {
        Espresso.onView(withId(R.id.username)).perform(ViewActions.clearText())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText("admin"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
    }

    @Test
    fun testPasswordIsEmpty() {
        Espresso.onView(withId(R.id.username)).perform(ViewActions.typeText("admin"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.clearText())
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
    }

    @Test
    fun testLoginSuccess() {
        Espresso.onView(withId(R.id.username))
            .perform(ViewActions.typeText("admin"), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText("admin"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
    }

    @Test
    fun testLoginFailed() {
        Espresso.onView(withId(R.id.username))
            .perform(ViewActions.typeText("xyz"), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.password))
            .perform(ViewActions.typeText("xyz"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
    }
}
