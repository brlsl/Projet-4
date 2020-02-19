package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetingList();

    public void addMeeting(Meeting meeting);

    public void deleteMeeting(Meeting meeting);

    public void sortArrayListAZ();

    public void sorArrayListZA();

    public void sortArrayListChronologicalOrder();

    public void sortArrayListAntiChronological();



}
