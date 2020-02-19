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
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setUp(){
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsListWithSuccess() {
        List<Meeting> meetings = service.getMeetingList();
        List<Meeting> expectedMeeting = DummyMeetingApiServiceGenerator.MEETING_LIST;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    //TODO: modifier la méthode addMeeting() pour que le test passe
    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetingList().contains(meetingToAdd));
    }

    @Test
    public void removeMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com");
        service.deleteMeeting(meetingToAdd);
        assertFalse(service.getMeetingList().contains(meetingToAdd));
    }



    @Test
    public void sortMeetingListAZ(){

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