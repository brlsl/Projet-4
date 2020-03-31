package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    //for design
    static RecyclerView mRecyclerView;
    static MyMeetingAdapter mMeetingListAdapter;

    //for data
    private MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get list
        mMeetingApiService.getMeetingsList();

        buildRecyclerView();

        //declaration
        FloatingActionButton fab_button;
        fab_button = findViewById(R.id.fab_add_reunion);
        // when user clicks on fab, it opens a dialog window
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewList);
        // get List and adapt to RecyclerView
        mMeetingListAdapter = new MyMeetingAdapter(mMeetingApiService.getMeetingsList());

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMeetingListAdapter);

        mMeetingListAdapter.setOnItemClickListener(new MyMeetingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "La date de la réunion est le "
                        +dateAndTime(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                Toast.makeText(MainActivity.this, "La réunion a été supprimée", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeItem(int position){
        // delete from original and filtered list
        mMeetingApiService.getMeetingsList().remove(position);
        mMeetingListAdapter.mMeetingListFiltered.remove(position);
        mMeetingListAdapter.notifyItemRemoved(position);
    }
    public String dateAndTime(int position){
        Meeting meeting = mMeetingApiService.getMeetingsList().get(position);
        return meeting.getMeetingDate() +" à "+ meeting.getHour();
    }

    //for menu item in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Filtrez par date ou par lieu");
        searchView.setMaxWidth(3840); // in pixel
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE); //in keyboard

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mMeetingListAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void openDialog(){
        MeetingDialog meetingDialog = new MeetingDialog();
        meetingDialog.show(getSupportFragmentManager(),"opens the dialog box");
    }
}