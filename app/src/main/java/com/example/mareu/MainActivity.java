package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mareu.api.MeetingApiServiceGenerator;
import com.example.mareu.model.Meeting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //for design
    static RecyclerView mRecyclerView;
    private FloatingActionButton fab_button;

    //for data
    static MyMeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // get List and adapt to RecyclerView
        mMeetingAdapter= new MyMeetingAdapter(MeetingApiServiceGenerator.generateMeetingList());

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

    public void openDialog(){
        MeetingDialog meetingDialog = new MeetingDialog();
        meetingDialog.show(getSupportFragmentManager(),"tag");

    }

}
