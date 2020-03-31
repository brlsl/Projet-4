package com.example.mareu;

import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.utils.DeleteMeetingAction;
import com.example.mareu.utils.RecyclerViewMatcher;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void meetingListIsNotEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.recyclerViewList), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void dialogShouldShowsUp() {
        onView(withId(R.id.fab_add_reunion))
                .perform(click());
        onView(withId(R.id.meeting_subject_ET))
                .check(matches(isDisplayed()));
    }

    @Test
    public void dialogFieldsShouldPrints(){
        onView(withId(R.id.fab_add_reunion))
                .perform(click());
        onView(withId(R.id.meeting_subject_ET))
                .check(matches(isDisplayed()));
        onView(withId(R.id.choose_hour_btn))
                .check(matches(isDisplayed()));
        onView(withId(R.id.meeting_hour_TV))
                .check(matches(isDisplayed()));
        onView(withId(R.id.choose_date_btn)).
                check(matches(isDisplayed()));
        onView(withId(R.id.meeting_date_TV))
                .check(matches(isDisplayed()));
        onView(withId(R.id.meeting_place_ET)).
                check(matches(isDisplayed()));
        onView(withId(R.id.meeting_participants_ET))
                .check(matches(isDisplayed()));
    }

    @Test
    public void timePickerShouldWorks(){
        onView(withId(R.id.fab_add_reunion))
                .perform(click());
        onView(withId(R.id.choose_hour_btn))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10,10));
        onView(withText("OK")).perform(click());
    }

    @Test
    public void datePickerShouldWorks(){
        onView(withId(R.id.fab_add_reunion))
                .perform(click());
        onView(withId(R.id.choose_date_btn))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2020,11,18));
        onView(withText("OK")).perform(click());
    }



    @Test
    public void filterByPlaceShouldWorks(){
        onView(withId(R.id.action_search))
                .perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class))
                .perform(typeText("Mario"));
        onView(new RecyclerViewMatcher(R.id.recyclerViewList)
                .atPositionOnView(0, R.id.meeting_information_TV))
                .check(matches(withText("Réunion B - 16:00 - Mario")));
        onView(isAssignableFrom(AutoCompleteTextView.class))
                .perform(clearText());
    }


    @Test
    public void filterByDateShouldWorks(){
        onView(withId(R.id.action_search))
                .perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class))
                .perform(typeText("18/11/2020"));
        onView(new RecyclerViewMatcher(R.id.recyclerViewList)
                .atPositionOnView(0, R.id.meeting_information_TV))
                .check(matches(withText("Réunion C - 19:00 - Luigi")));
        onView(isAssignableFrom(AutoCompleteTextView.class))
                .perform(clearText());

    }

    @Test
    public void shouldRemoveAndAddMeeting(){
        int ITEM_COUNT = 3;
        onView(withId(R.id.recyclerViewList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteMeetingAction()));
        onView(withId(R.id.recyclerViewList)).check(withItemCount(ITEM_COUNT - 1));
        onView(withId(R.id.fab_add_reunion))
                .perform(click());
        onView(withId(R.id.meeting_subject_ET))
                .perform(typeText("Sujet test"));
        onView(withId(R.id.meeting_place_ET))
                .perform(typeText("Lieu test"));
        onView(withId(R.id.meeting_participants_ET))
                .perform(typeText("test@gmail.com"));
        onView(withId(R.id.choose_hour_btn))
                .perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.choose_date_btn))
                .perform(click());
        onView(withText("OK")).perform(click());
        onView(withText("CONFIRMER"))
                .perform(click());
        onView(withId(R.id.recyclerViewList)).check(withItemCount(ITEM_COUNT));
    }
}
