package com.jhowcs.nasameteoritelandings;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jhowcs.nasameteoritelandings.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by jonathan_campos on 27/10/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.rv_meteorites)).check(matches(isDisplayed()));
        onView(withId(R.id.rl_progress)).check(matches(isDisplayed()));
        onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
    }

    @Test
    public void whenActivityIsLaunchedAndHasNoConnection_showDisplayRetry() {
        onView(withId(R.id.rl_retry)).check(matches(isDisplayed()));
    }
}
