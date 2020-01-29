package com.example.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetingList = new ArrayList<>();

    public MyMeetingAdapter(List<Meeting> mMeetingList) {
        this.mMeetingList = mMeetingList;
    }

    @NonNull
    @Override
    public MyMeetingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_recyclerview_item,parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMeetingAdapter.ViewHolder holder, int position) {

        holder.display(mMeetingList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeetingInformation;

         public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMeetingInformation = itemView.findViewById(R.id.meeting_information);
        }

        public void display(Meeting meeting){

             mMeetingInformation.setText(meeting.getMeeting());
        }
    }
}
