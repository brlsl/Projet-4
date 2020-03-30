package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.text.ParseException;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetingsList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

}
