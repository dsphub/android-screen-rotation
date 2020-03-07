package com.dsp.androidsample

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dsp.androidsample.MainActivity.Companion.TAG
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Mock
    private lateinit var viewModel: RotatingViewModel
    @Spy
    private lateinit var viewModelSpy: RotatingViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testDestroyFragmentByButton() {
        // given
        val scenario = launch(MainActivity::class.java)
//        scenario.moveToState(Lifecycle.State.CREATED)
//        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity {
            val fragment = it.supportFragmentManager.findFragmentByTag(TAG)
            (fragment as RotatingFragment).setTestViewModel(viewModel)
            it.showFragment(fragment)
        }
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))

        // when
        onView(withId(R.id.button_finish)).perform(click())

        // then
        onView(withId(R.id.button_finish)).check(doesNotExist())
        then(viewModel).should().onCleared() //FIXIT onCleared must be called
        then(viewModel).shouldHaveNoMoreInteractions()
    }

    @Test
    fun testRotateActivity() {
        // given
        val scenario = launch(MainActivity::class.java)
        scenario.onActivity {
            val fragment = it.supportFragmentManager.findFragmentByTag(MainActivity.TAG)
            (fragment as RotatingFragment).setTestViewModel(viewModel)
        }
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))

        // when
        scenario.recreate()

        // then
        onView(withId(R.id.button_finish)).check(matches(withText("Finish")))
        then(viewModel).shouldHaveNoInteractions()
    }
}