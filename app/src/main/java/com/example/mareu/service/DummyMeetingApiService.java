package com.example.mareu.service;

import com.example.mareu.model.Meeting;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingApiServiceGenerator.generateMeetingList();

    private List<Meeting> mMeetingListFiltered;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);

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
    public List<Meeting> filterMeetingList(String parameter) {
        List<Meeting> meetingList = new ArrayList<>();
        for (int i = 0; i < mMeetingList.size() ; i++) {
            if (mMeetingList.get(i).getPlace().toLowerCase().contains(parameter)) {
                meetingList.add(mMeetingList.get(i));
            }
            else if(mMeetingList.get(i).getMeetingDate().toLowerCase().contains(parameter)) {
                meetingList.add(mMeetingList.get(i));
            }
        }
        return meetingList;

    }

}
