package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingApiServiceGenerator.generateMeetingList();

    @Override
    public List<Meeting> getMeetingsList() {
        return mMeetingList;
    }


    @Override
    public void addMeeting(Meeting meeting) {mMeetingList.add(meeting);}

    @Override
    public void deleteMeeting(Meeting meeting) {mMeetingList.remove(meeting);
    }

    @Override
    public List<Meeting> filterMeetingListByDateOrPlace(String input) {
        List<Meeting> meetingListFiltered = new ArrayList<>();
        for (int i = 0; i < mMeetingList.size() ; i++) {
            if (mMeetingList.get(i).getPlace().toLowerCase().contains(input)) {
                meetingListFiltered.add(mMeetingList.get(i));
            }
            else if(mMeetingList.get(i).getMeetingDate().toLowerCase().contains(input)) {
                meetingListFiltered.add(mMeetingList.get(i));
            }
        }
        return meetingListFiltered;
    }

}
