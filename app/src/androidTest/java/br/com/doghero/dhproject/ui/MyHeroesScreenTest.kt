package br.com.doghero.dhproject.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import br.com.doghero.dhproject.R
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
//Source api is already faked so we can trust in items data and positions
class MyHeroesScreenTest {

    companion object {

        private const val SUPERHERO_NAME = "Eduardo"
        private const val NOT_SUPERHERO_NAME = "Maria"
        private const val FAVORITE_HERO_NAME = "Cris"
    }

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MyHeroesActivity>(MyHeroesActivity::class.java)

    @Test
    fun showingRecentsAndFavoritesHeaders() {
        onView(withId(R.id.rv_my_heroes)).check(matches(hasDescendant(withText(R.string.myheroes_recents_header))))
        onView(withId(R.id.rv_my_heroes)).check(matches(hasDescendant(withText(R.string.myheroes_likes_header))))
    }

    @Test
    fun showingAllHeroes() {
        onView(withText(SUPERHERO_NAME)).check(matches(isDisplayed()))
        onView(withText(NOT_SUPERHERO_NAME)).check(matches(isDisplayed()))
        onView(withText(FAVORITE_HERO_NAME)).check(matches(isDisplayed()))
    }

    @Test
    fun showingSuperHeroBadgeToSuperHeroes() {
        onView(allOf(withId(R.id.iv_superhero), hasSibling(withText(SUPERHERO_NAME)))).check(matches(isDisplayed()))
    }

    @Test
    fun notShowingSuperHeroBadgeToNormalHeroes() {
        onView(allOf(withId(R.id.iv_superhero), hasSibling(withText(NOT_SUPERHERO_NAME)))).check(matches(not(isDisplayed())))
    }

    @Test
    fun likeHeroShowConfirmationToast() {
        onView(withId(R.id.rv_my_heroes)).perform(actionOnItemAtPosition<MyHeroHolder>(1, clickChildViewWithId(R.id.btn_like)))

        onView(withText(R.string.myheroes_toast_favorite_added))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun clickChatButtonShowChatToast() {
        onView(withId(R.id.rv_my_heroes)).perform(actionOnItemAtPosition<MyHeroHolder>(1, clickChildViewWithId(R.id.btn_chat)))

        onView(withText(R.string.myheroes_toast_chat))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun clickBookAgainButtonShowBookAgainToast() {
        onView(withId(R.id.rv_my_heroes)).perform(actionOnItemAtPosition<MyHeroHolder>(1, clickChildViewWithId(R.id.btn_book_again)))

        onView(withText(R.string.myheroes_toast_bookagain))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                view.findViewById<View>(id)?.performClick()
            }
        }
    }
}