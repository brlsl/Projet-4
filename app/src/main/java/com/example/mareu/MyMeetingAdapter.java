package com.example.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.api.MeetingApiService;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.ViewHolder> {

    static List<Meeting> mMeetingList;

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
        private TextView mMeetingParticipants;
        private ImageView mDeleteItem;

         public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMeetingInformation = itemView.findViewById(R.id.meeting_information_TV);
            mMeetingParticipants = itemView.findViewById(R.id.participant_TV);
            mDeleteItem = itemView.findViewById(R.id.item_delete_btn);
        }

        public void display(final Meeting meeting){
             mMeetingInformation.setText(meeting.getPlace()+" - " + meeting.getHour()+" - " + meeting.getSubject());
             mMeetingParticipants.setText(meeting.getParticipant());

             mDeleteItem.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     mMeetingList.remove(meeting);
                     // refresh RecyclerView
                     MainActivity.mMeetingAdapter.notifyDataSetChanged();

                     // Toast.makeText(  , "La réunion a été supprimée", Toast.LENGTH_SHORT).show();

                 }
             });

        }

    }
}
