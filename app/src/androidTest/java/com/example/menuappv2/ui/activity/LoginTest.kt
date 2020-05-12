package com.example.menuappv2.ui.activity

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.menuappv2.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginTest() {
        onView(withId(R.id.profileFragment)).perform(click())
        onView(withId(R.id.usernameInput)).perform(click(), replaceText("robbieverdurme@telenet.be"), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(replaceText("Password"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
    }
}
