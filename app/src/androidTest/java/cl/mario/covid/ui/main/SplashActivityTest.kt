package cl.mario.covid.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cl.mario.covid.R
import cl.mario.covid.ui.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashActivityTest{

    @get:Rule var activityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun checkActivityVisibility(){
        onView(withId(R.id.layout_splashActivity))
            .check(matches(isDisplayed()))

    }

    @Test
    fun checkImageVisibility(){
        onView(withId(R.id.ivSplash))
            .check(matches(isDisplayed()))
    }

}