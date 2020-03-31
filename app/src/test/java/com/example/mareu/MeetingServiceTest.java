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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;
    private final Meeting meeting1 = new Meeting("Sujet 1", "13:00", "BBB", "test@gmail.com","18/11/2020");
    private final Meeting meeting2 = new Meeting("Sujet 2", "11:00", "AAA", "test@gmail.com","20/02/2022");
    private final Meeting meeting3 = new Meeting("Sujet 3", "12:00", "CCC", "test@gmail.com","01/01/2021");

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

    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("Sujet Test4","00:00","Place4","test@gmail.com","20/10/2020");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void removeMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("Sujet Test4","00:00","Place4","test@gmail.com","20/10/2020");
        service.addMeeting(meetingToAdd);
        service.deleteMeeting(meetingToAdd);
        assertFalse(service.getMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void filterMeetingByPlaceShouldWorks(){
        service.getMeetingsList().clear();
        service.addMeeting(meeting1);
        service.addMeeting(meeting2);
        service.addMeeting(meeting3);
        assertTrue(service.filterMeetingListByDateOrPlace("b").contains(meeting1));
        assertFalse(service.filterMeetingListByDateOrPlace("b").contains(meeting2));
        assertFalse(service.filterMeetingListByDateOrPlace("b").contains(meeting3));
    }

    @Test
    public void filterMeetingByDateShouldWorks(){
        service.getMeetingsList().clear();
        service.addMeeting(meeting1);
        service.addMeeting(meeting2);
        service.addMeeting(meeting3);
        assertFalse(service.filterMeetingListByDateOrPlace("2022").contains(meeting1));
        assertTrue(service.filterMeetingListByDateOrPlace("2022").contains(meeting2));
        assertFalse(service.filterMeetingListByDateOrPlace("2022").contains(meeting3));
    }
}