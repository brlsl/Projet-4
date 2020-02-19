package com.example.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.util.List;

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.ViewHolder> {

    static List<Meeting> mMeetingList;
    private Context context;
    private MeetingApiService mApiService;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyMeetingAdapter(List<Meeting> mMeetingList, Context context) {
        this.mMeetingList = mMeetingList;
        this.context = context;
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
        private ImageView mDelete;

         public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMeetingInformation = itemView.findViewById(R.id.meeting_information_TV);
            mMeetingParticipants = itemView.findViewById(R.id.participant_TV);
            mDelete = itemView.findViewById(R.id.item_delete_btn);
        }

        public void display(final Meeting meeting){
             mMeetingInformation.setText(meeting.getSubject()+" - " + meeting.getHour()+" - " + meeting.getPlace());
             mMeetingParticipants.setText(meeting.getParticipant());

             mDelete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     mApiService = DI.getMeetingApiService();
                     //mApiService.deleteMeeting(meeting);

                     mMeetingList.remove(meeting);
                     // refresh RecyclerView
                     MainActivity.mMeetingAdapter.notifyDataSetChanged();
                     Toast.makeText(context, "La réunion a été supprimée", Toast.LENGTH_SHORT).show();

                 }
             });

        }

    }
}
