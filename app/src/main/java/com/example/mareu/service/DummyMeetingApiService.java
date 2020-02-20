package com.example.mareu.service;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MainActivity;
import com.example.mareu.MeetingDialog;
import com.example.mareu.MyMeetingAdapter;
import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> mMeetingList = DummyMeetingApiServiceGenerator.generateMeetingList();
    private MyMeetingAdapter mAdapter = new MyMeetingAdapter(mMeetingList);

    @Override
    public List<Meeting> getMeetingList() {
        return mMeetingList;
    }

    @Override
    public void addMeeting(Meeting meeting) {mMeetingList.add(meeting);}

    public void deleteMeeting(Meeting meeting) {mMeetingList.remove(meeting);
    }

    @Override
    public void sortArrayListAZ() {
       Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getPlace().compareTo(o2.getPlace());
            }
        });

    }

    @Override
    public void sorArrayListZA() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o2.getPlace().compareTo(o1.getPlace());
            }
        });
    }

    @Override
    public void sortArrayListChronologicalOrder() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getHour().compareTo(o2.getHour());
            }
        });
    }

    @Override
    public void sortArrayListAntiChronological() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o2.getHour().compareTo(o1.getHour());
            }
        });
    }


}
