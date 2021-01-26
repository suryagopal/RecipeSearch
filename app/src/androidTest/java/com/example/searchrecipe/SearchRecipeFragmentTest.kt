package com.example.searchrecipe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.searchrecipe.view.LauncherActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchRecipeFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun searchActivityLaunchTest() {
        val searchView = onView(
            allOf(
                withParent(withParent(withId(R.id.searchView))),
                isDisplayed()
            )
        )
        searchView.check(ViewAssertions.matches(isDisplayed()))
    }
}