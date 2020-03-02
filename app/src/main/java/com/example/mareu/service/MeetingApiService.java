package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.text.ParseException;
import java.util.List;

public interface MeetingApiService {


    List<Meeting> getMeetingsList();

    public void addMeeting(Meeting meeting);

    public void deleteMeeting(Meeting meeting);

    public void sortMeetingsPlaceAZ();

    public void sortMeetingsPlaceZA();

    public void sortMeetingsChronologicalOrder();

    public void sortMeetingsAntiChronological();



}
