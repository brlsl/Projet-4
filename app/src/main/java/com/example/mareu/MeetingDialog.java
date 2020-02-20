package com.example.mareu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.util.Calendar;

import static com.example.mareu.MainActivity.mMeetingAdapter;

public class MeetingDialog extends AppCompatDialogFragment {

    private EditText mSubject_ET;
    private TextView mHour_TV;
    private EditText mPlace_ET;
    private EditText mParticipants_ET;
    private ImageButton mTime_IB;

    private MeetingApiService mMeetingApiService;

    private TimePickerDialog mTimePickerDialog;
    private Calendar mCalendar;
    private int currentHour;
    private int currentMinute;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_reunion_dialog,null);

        mMeetingApiService = DI.getMeetingApiService();

        mSubject_ET = view.findViewById(R.id.meeting_subject_ET);
        mHour_TV = view.findViewById(R.id.meeting_hour_ET);
        mTime_IB = view.findViewById(R.id.choose_time_btn);
        mPlace_ET = view.findViewById(R.id.meeting_place_ET);
        mParticipants_ET = view.findViewById(R.id.meeting_participants_ET);

        setTimePickerDialog();

        builder.setView(view)
                .setTitle("Ajouter une nouvelle réunion")
                .setNegativeButton("Annuler", null)
                .setPositiveButton("Confirmer", null);

        Dialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        String subject = mSubject_ET.getText().toString();
                        String hour = mHour_TV.getText().toString();
                        String place = mPlace_ET.getText().toString();
                        String participant = mParticipants_ET.getText().toString();

                        // if all form are filled,
                        if (!(mSubject_ET.getText().toString().isEmpty() || mHour_TV.getText().toString().isEmpty()
                                || mPlace_ET.getText().toString().isEmpty()
                                || mParticipants_ET.getText().toString().isEmpty())){

                            applyTextsToList(subject, hour, place, participant);
                            Toast.makeText(getContext(), "La réunion a été ajoutée", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), "Veuillez compléter tous les champs", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // prevent dialog box from getting dismissed on outside touch
        dialog.setCanceledOnTouchOutside(false);

        // show alert dialog
        return dialog;

    }

    private void applyTextsToList(String subject, String hour, String place, String participant) {
        Meeting new_meeting = new Meeting(place, hour, subject, participant);
        mMeetingApiService.addMeeting(new_meeting);
        mMeetingAdapter.notifyDataSetChanged();
    }

    private void setTimePickerDialog(){
        mTime_IB.setOnClickListener(new View.OnClickListener() {
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
                        if(hourOfDay < 10 && minute <10){
                            mHour_TV.setText("0" + hourOfDay +"h" + "0" + minute );
                        }
                        else if (minute < 10){
                            mHour_TV.setText(hourOfDay +"h" +"0"+ minute );
                        }
                        else if(hourOfDay < 10){
                            mHour_TV.setText("0" + hourOfDay +"h" + minute );
                        }
                        else
                            mHour_TV.setText(hourOfDay +"h" + minute);

                    }
                }, currentHour, currentMinute,true);
                mTimePickerDialog.show();
            }
        });
    }
}
