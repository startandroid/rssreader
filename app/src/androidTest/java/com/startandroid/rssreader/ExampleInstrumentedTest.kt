package com.startandroid.rssreader

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.startandroid.rssreader", appContext.packageName)
    }

    @Test
    fun myTest() {
        //onView(withId(R.id.fabFeeds)).perform(click())
        onView(withId(R.id.fabFeeds)).check(matches(isDisplayed()))
        onView(withId(R.id.fabFeeds)).perform(click())
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()))
        onView(withId(R.id.fabAdd)).perform(click())
        onView(withId(R.id.buttonCheck)).check(matches(isDisplayed()))
    }
}