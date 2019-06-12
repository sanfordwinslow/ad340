package com.san.agster;



import androidx.test.espresso.ViewAction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
    private String emailInput;
    private String passwordInput;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidInput() {
        emailInput = "name";
        passwordInput = "something";
    }

    @Test
    public void changeLoginButton() {
        onView(withId(R.id.editText3)).perform(typeText(emailInput), closeSoftKeyboard());
        onView(withId(R.id.button_toast)).perform((ViewAction) click());
        onView(withId(R.id.editText4)).perform((ViewAction) typeText(passwordInput)).check(matches(withText(passwordInput)));
    }
}
