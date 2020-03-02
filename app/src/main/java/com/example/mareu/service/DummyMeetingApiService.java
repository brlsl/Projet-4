package com.example.mareu.service;

import com.example.mareu.MyMeetingAdapter;
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

    @Override
    public void sortMeetingsPlaceAZ() {
       Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                // TODO: forcer la première lettre à etre en majuscule
                return o1.getPlace().compareTo(o2.getPlace());
            }
        });

    }

    @Override
    public void sortMeetingsPlaceZA() {
        Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o2.getPlace().compareTo(o1.getPlace());
            }
        });
    }

    @Override
    public void sortMeetingsChronologicalOrder(){
        Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);

                Date conversion1 = null;
                try {
                    conversion1 = sdf.parse(o1.getFusion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date conversion2 = null;
                try {
                    conversion2 = sdf.parse(o2.getFusion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return conversion1.compareTo(conversion2);
            }
        });
    }

    @Override
    public void sortMeetingsAntiChronological() {
        Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);

                Date conversion1 = null;
                try {
                    conversion1 = sdf.parse(o1.getFusion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date conversion2 = null;
                try {
                    conversion2 = sdf.parse(o2.getFusion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return conversion2.compareTo(conversion1);
            }
        });
    }

}
