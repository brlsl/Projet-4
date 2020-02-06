package com.example.mareu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mareu.model.Meeting;


import java.util.Calendar;

import static com.example.mareu.MainActivity.mMeetingAdapter;

public class MeetingDialog extends AppCompatDialogFragment {

    private EditText editTextSubject;
    private EditText editTextHour;
    private EditText editTextPlace;
    private EditText editTextParticipants;

    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendar;
    int currentHour;
    int currentMinute;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_reunion_dialog,null);

        editTextSubject = view.findViewById(R.id.meeting_subject_ET);
        editTextHour = view.findViewById(R.id.meeting_hour_ET);
        editTextPlace = view.findViewById(R.id.meeting_place_ET);
        editTextParticipants = view.findViewById(R.id.meeting_participants_ET);
        setTimePickerDialog();

        builder.setView(view)
                .setTitle("Ajouter une nouvelle réunion")
                .setNegativeButton("Annuler", null)
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String subject = editTextSubject.getText().toString();
                        String hour = editTextHour.getText().toString();
                        String place = editTextPlace.getText().toString();
                        String participant = editTextParticipants.getText().toString();


                        if (!(editTextSubject.getText().toString().isEmpty() || editTextHour.getText().toString().isEmpty()
                                || editTextPlace.getText().toString().isEmpty()
                                || editTextParticipants.getText().toString().isEmpty())){

                            applyTextsToList(subject, hour, place, participant);
                            Toast.makeText(getContext(), "La réunion a été ajoutée", Toast.LENGTH_SHORT).show();
                        }
                        else {
                             Toast.makeText(getContext(), "Veuillez compléter tous les champs", Toast.LENGTH_SHORT).show();
                            }
                    }

                });

        // show alert dialog
        return builder.show();

    }

    public void applyTextsToList(String subject, String hour, String place, String participant) {
        Meeting new_meeting = new Meeting(place, hour, subject, participant);
        MyMeetingAdapter.mMeetingList.add(new_meeting);
        mMeetingAdapter.notifyDataSetChanged();
    }

    public void setTimePickerDialog(){
        editTextHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for configuring current device time
                mCalendar = Calendar.getInstance();
                currentHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = mCalendar.get(Calendar.MINUTE);

                mTimePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    //when user click on the editText, it opens the TimePickerDialog
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextHour.setText(hourOfDay +":" + minute );

                    }
                }, currentHour, currentMinute,true);
                mTimePickerDialog.show();
            }
        });
    }
}
