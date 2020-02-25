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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.ViewHolder> {

    public static List<Meeting> mMeetingList;
    private OnItemClickListener mListener;

    private static String ACTUAL_TIME_STAMP;
    private static SimpleDateFormat sdf;


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
        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        //actual date constant for defining avatar color
      ACTUAL_TIME_STAMP = new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).format(new Date());
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

    public static Date getDateWithoutTimeUsingFormat()
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        return formatter.parse(formatter.format(new Date()));
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

        void display(final Meeting meeting) {
            mMeetingInformation.setText(meeting.getSubject()+" - " + meeting.getHour()+" - " + meeting.getPlace());
            mMeetingParticipants.setText(meeting.getParticipant());

            try {
                Date meetingDate = sdf.parse(meeting.getMeetingDate());
                Date currentDate = getDateWithoutTimeUsingFormat();
            // set avatar color depending meeting date is passed, today or in future
                if (meetingDate.before(currentDate))
                    mAvatarColor.setColorFilter(Color.parseColor("#EDD9D0")); //red
                else if(meetingDate.equals(currentDate))
                    mAvatarColor.setColorFilter(Color.parseColor("#FFE793")); //yellow
                else if(meetingDate.after(currentDate))
                    mAvatarColor.setColorFilter(Color.parseColor("#AECEB8")); //green
            } catch (ParseException e) {
                // cas où le format de la date est erronée
                mAvatarColor.setColorFilter(Color.parseColor("#AAAAAA"));
            }

        }

    }
}
