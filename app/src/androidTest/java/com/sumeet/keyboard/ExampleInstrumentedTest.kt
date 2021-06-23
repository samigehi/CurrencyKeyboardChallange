package com.sumeet.keyboard

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sumeet.keyboard", appContext.packageName)
    }

    @Test
    fun uiTest() {
        // Context of the app under test.
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button7)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button8)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_currency)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("1728.00")))
    }

}