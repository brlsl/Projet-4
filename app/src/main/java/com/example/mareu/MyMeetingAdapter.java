package com.example.mareu;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mareu.model.Meeting;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.ViewHolder> {

    public static List<Meeting> mMeetingList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    //get data out from List to Adapter with constructor
    //1) create Adapter, pass a list of Meeting to it and pass this list to our mMeetingList variable
    //2) then  get information out into our Adapter
    public MyMeetingAdapter(List<Meeting> meetingList) {
        mMeetingList = meetingList;
    }

    @NonNull
    @Override
    public MyMeetingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_recyclerview_item,parent, false);
        return new ViewHolder (view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMeetingAdapter.ViewHolder holder, int position) {

        holder.display(mMeetingList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeetingInformation;
        private TextView mMeetingParticipants;
        private ImageView mDeleteImage;
        private ImageView mAvatarColor;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mMeetingInformation = itemView.findViewById(R.id.meeting_information_TV);
            mMeetingParticipants = itemView.findViewById(R.id.participant_TV);
            mDeleteImage = itemView.findViewById(R.id.item_delete_btn);
            mAvatarColor = itemView.findViewById(R.id.color_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        // provide position for us
                        int position = getAdapterPosition();
                        //make sure the position is valid
                        if (position != RecyclerView.NO_POSITION){
                            //pass the position to our interface
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        // provide position for us
                        int position = getAdapterPosition();
                        //make sure the position is valid
                        if (position != RecyclerView.NO_POSITION){
                            //pass the position to our interface
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }

        public void display(final Meeting meeting){
            mMeetingInformation.setText(meeting.getSubject()+" - " + meeting.getHour()+" - " + meeting.getPlace());
            mMeetingParticipants.setText(meeting.getParticipant());


           // private static final DateFormat actualDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            // Date date = new Date();
            // ajouter une condition pour la couleur de l'avatar en fonction de la date
            if (meeting.getDate() < 0)
                mAvatarColor.setColorFilter(Color.parseColor("#EDD9D0"));
            if (meeting.getDate() == 0)
                mAvatarColor.setColorFilter(Color.parseColor("#FFE793"));
            if (meeting.getDate() > 0)
                mAvatarColor.setColorFilter(Color.parseColor("#AECEB8"));
        }

    }
}
