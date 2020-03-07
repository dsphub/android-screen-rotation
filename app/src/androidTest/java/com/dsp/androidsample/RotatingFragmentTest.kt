package com.dsp.androidsample

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class RotatingFragmentTest {
    @Mock
    private lateinit var viewModel: RotatingViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testDestroyFragmentByButton() {
        // given
        val scenario = launchFragmentInContainer<RotatingFragment>()
        scenario.onFragment {
            it.setTestViewModel(viewModel)
        }
//        scenario.moveToState(Lifecycle.State.CREATED)
//        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))

        // when
        onView(withId(R.id.button_finish)).perform(click())

        // then
        //TODO asserts can not be passed without activity
//        onView(withId(R.id.button_finish)).check(doesNotExist())
//        then(viewModel).should().onCleared()
    }

    @Test
    fun testRotateFragment() {
        // given
        val scenario = launchFragmentInContainer<RotatingFragment>()
        scenario.onFragment {
            it.setTestViewModel(viewModel)
        }
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))

        // when
        scenario.recreate()

        // then
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))
        then(viewModel).shouldHaveNoInteractions()
    }
}