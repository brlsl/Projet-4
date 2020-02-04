package com.example.mareu.api;

import com.example.mareu.model.Meeting;

import java.util.List;
import static com.example.mareu.api.MeetingApiServiceGenerator.generateMeetingList;

public class MeetingApiService {
    private List<Meeting> mMeetingList = generateMeetingList();

    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }
}
