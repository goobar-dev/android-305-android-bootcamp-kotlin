package dev.goobar.androidstudyguide

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  /**
   * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
   * and close it after each test. This is a replacement for
   * [androidx.test.rule.ActivityTestRule].
   */
  @get:Rule
  var activityRule: ActivityScenarioRule<MainActivity>
      = ActivityScenarioRule(MainActivity::class.java)

  @Test
  fun notesTabSelectedAtStartup() {
    onView(withId(R.id.myNotesFragment)).check(matches(isSelected()))
    onView(withId(R.id.studyGuideFragment)).check(matches(isNotSelected()))
  }

  @Test
  fun notesTabSelectedAtStartup_ThenClickGuide() {
    onView(withId(R.id.myNotesFragment)).check(matches(isSelected()))
    onView(withId(R.id.studyGuideFragment)).check(matches(isNotSelected()))

    onView(withId(R.id.studyGuideFragment)).perform(click())

    onView(withId(R.id.myNotesFragment)).check(matches(isNotSelected()))
    onView(withId(R.id.studyGuideFragment)).check(matches(isSelected()))
  }

  @Test
  fun twitterShareIsVisibleInToolbar() {
    onView(withId(R.id.toolbar)).check(matches(hasDescendant(withId(R.id.shareOnTwitter))))
  }
}