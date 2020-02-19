package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingApiServiceGenerator.generateMeetingList();


    @Override
    public List<Meeting> getMeetingList() {
        return mMeetingList;
    }

    @Override
    public void addMeeting(Meeting meeting) {

    }

    public void deleteMeeting(Meeting meeting) {mMeetingList.remove(meeting);
    }

    @Override
    public void sortArrayListAZ() {

    }

    @Override
    public void sorArrayListZA() {

    }

    @Override
    public void sortArrayListChronologicalOrder() {

    }

    @Override
    public void sortArrayListAntiChronological() {

    }


}
