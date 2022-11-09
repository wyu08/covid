package cl.mario.covid.ui.main

import android.content.Context
import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import cl.mario.covid.covidmodule.R
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@LargeTest
@RunWith(JUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var instrumentationContext: Context

    @Before
    fun setup(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkDatePickerDialogSetDate() {

        val expectedDateText = "1 de NOVEMBER del 2022"

        Thread.sleep(3000L)

        val dateTitle =
            instrumentationContext.resources.getString(R.string.date_title)
                .replace("%s", "")

        val confirmed =
            instrumentationContext.resources.getString(R.string.confirmed_cases)
                .replace("%d", "")

        val death =
            instrumentationContext.resources.getString(R.string.deceased_people)
                .replace("%d", "")

        onView(withId(R.id.tvDate))
            .check(matches(withSubstring(dateTitle)))

        onView(withId(R.id.tvConfirm))
            .check(matches(withSubstring(confirmed)))

        onView(withId(R.id.tvDeath))
            .check(matches(withSubstring(death)))

        setDatePickerDialog(R.id.chooseDateDialogBtn,2022,11,1)

        onView(withId(R.id.progress))
            .check(matches(isDisplayed()))

        Thread.sleep(2000L)

        onView(withId(R.id.tvDate))
            .check(matches(withSubstring(expectedDateText)))

        Thread.sleep(1000L)

    }

    private fun setDatePickerDialog(datePickerLaunchViewId: Int, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        onView(withId(datePickerLaunchViewId))
            .perform(click())

        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .perform(PickerActions.setDate(year, monthOfYear, dayOfMonth))

        onView(withText(android.R.string.ok))
            .inRoot(isDialog())
            .perform(click())
    }
}