package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingApiServiceGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;
    private List<Meeting> testList = new ArrayList<>(Arrays.asList(
            new Meeting("Sujet 2", "14h00", "BBB", "test@gmail.com"),
            new Meeting("Sujet 1", "10h00", "AAA", "test@gmail.com"),
            new Meeting("Sujet 3", "16h00", "CCC", "test@gmail.com")
    ));

    @Before
    public void setUp(){
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsListWithSuccess() {
        List<Meeting> meetings = service.getMeetingsList();
        List<Meeting> expectedMeeting = DummyMeetingApiServiceGenerator.MEETING_LIST;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    //TODO: modifier la méthode addMeeting() pour que le test passe
    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void removeMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com");
        service.deleteMeeting(meetingToAdd);
        assertFalse(service.getMeetingsList().contains(meetingToAdd));
    }



    @Test
    public void sortMeetingListAZ(){
        service.sortMeetingsPlaceAZ();

    }

    @Test
    public void sortMeetingZA(){

    }

    @Test
    public void sortMeetingChronological(){

    }

    @Test
    public void sortMeetingAntiChronological(){

    }
}