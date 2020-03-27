package com.example.mareu.di;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;

public class DI {
    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService(){return service;}

    public static MeetingApiService getNewInstanceApiService(){ return new DummyMeetingApiService();}

}
