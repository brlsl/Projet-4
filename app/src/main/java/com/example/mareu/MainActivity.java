package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List <Meeting> mMeetingList;
    private MyMeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mMeetingList = new ArrayList<>();

        mMeetingList.add(new Meeting("Chine"));
        mMeetingList.add(new Meeting("France"));
        mMeetingList.add(new Meeting("Brésil"));
        mMeetingList.add(new Meeting("Allemagne"));
        mMeetingList.add(new Meeting("Italie"));
        mMeetingList.add(new Meeting("Suède"));
        mMeetingList.add(new Meeting("Grèce"));
        mMeetingList.add(new Meeting("Etats-unis"));
        mMeetingList.add(new Meeting("Argentine"));
        mMeetingList.add(new Meeting("Japon"));
        mMeetingList.add(new Meeting("Australie"));
        mMeetingList.add(new Meeting("Suède"));

        mMeetingAdapter= new MyMeetingAdapter(mMeetingList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mMeetingAdapter);
    }
}
