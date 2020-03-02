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
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;
    private final Meeting meeting1 = new Meeting("Sujet 1", "14h00", "AAA", "test@gmail.com","20/01/2000");
    private final Meeting meeting2 = new Meeting("Sujet 2", "14h00", "BBB", "test@gmail.com","20/01/2030");


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
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com","20/10/2020");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void removeMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("SujetTest4","00h00","Place4","test@gmail.com","20/10/2020");
        service.deleteMeeting(meetingToAdd);
        assertFalse(service.getMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void sortMeetingListAZ(){
        // vide la liste
        service.getMeetingsList().clear();
        service.addMeeting(meeting1);
        service.addMeeting(meeting2);
        //test
        service.sortMeetingsPlaceAZ();
        assertTrue((meeting1.getPlace().compareTo(meeting2.getPlace()))< 0);

    }

    @Test
    public void sortMeetingZA(){
        // vide la liste
        service.getMeetingsList().clear();
        service.addMeeting(meeting1);
        service.addMeeting(meeting2);
        //test
        service.sortMeetingsPlaceZA();
        assertTrue((meeting1.getPlace().compareTo(meeting2.getPlace()))> 0);

    }

    @Test
    public void sortMeetingChronological(){

    }

    @Test
    public void sortMeetingAntiChronological(){

    }
}