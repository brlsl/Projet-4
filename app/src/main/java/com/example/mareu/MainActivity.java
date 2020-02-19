package com.example.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.mareu.service.DummyMeetingApiServiceGenerator;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    //for design
    static RecyclerView mRecyclerView;
    private FloatingActionButton fab_button;
    private MeetingApiService mMeetingApiService;

    //for data
    static MyMeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // get List and adapt to RecyclerView
        mMeetingAdapter= new MyMeetingAdapter(DummyMeetingApiServiceGenerator.generateMeetingList(), this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mMeetingAdapter);

        // when user clicks on fab, it opens a dialog window
        fab_button = findViewById(R.id.fab_add_reunion);
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    //for menu item in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_1){
            sortArrayListAZ();
        }
        if(id == R.id.item_2){
            sortArrayListZA();
        }

        if(id == R.id.item_3){
            sortArrayListChronologicalOrder();
        }
        if(id == R.id.item_4){
            sortArrayListAntiChronological();
        }

        return super.onOptionsItemSelected(item);
    }

    // list in order from A to Z
    private void sortArrayListAZ() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getPlace().compareTo(o2.getPlace());
            }
        });
        mMeetingAdapter.notifyDataSetChanged();
    }

    //list in order from Z to A
    private void sortArrayListZA() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o2.getPlace().compareTo(o1.getPlace());
            }
        });
        mMeetingAdapter.notifyDataSetChanged();
    }

    private void sortArrayListChronologicalOrder() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getHour().compareTo(o2.getHour());
            }
        });
        mMeetingAdapter.notifyDataSetChanged();
    }

    private void sortArrayListAntiChronological() {
        Collections.sort(MyMeetingAdapter.mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o2.getHour().compareTo(o1.getHour());
            }
        });
        mMeetingAdapter.notifyDataSetChanged();
    }

    public void openDialog(){
        MeetingDialog meetingDialog = new MeetingDialog();
        meetingDialog.show(getSupportFragmentManager(),"opens the dialog box");
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mMeetingApiService.deleteMeeting(event.meeting);
    }

 */
}
