package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingApiServiceGenerator.generateMeetingList();

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);

    @Override
    public List<Meeting> getMeetingsList() {
        return mMeetingList;
    }


    @Override
    public void addMeeting(Meeting meeting) {mMeetingList.add(meeting);}

    public void deleteMeeting(Meeting meeting) {mMeetingList.remove(meeting);
    }

}
