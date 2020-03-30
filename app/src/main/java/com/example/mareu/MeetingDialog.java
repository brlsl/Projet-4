package com.example.mareu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
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

public class MeetingDialog extends AppCompatDialogFragment{

    //build dialog
    private View view;
    private AlertDialog.Builder builder;
    private Dialog dialog;

    //fields of new meeting creation
    private EditText mSubject_ET;
    private TextView mHour_TV;
    private EditText mPlace_ET;
    private EditText mParticipants_ET;
    private ImageButton mTimePicker_IB;
    private ImageButton mDatePicker_IB;
    private TextView mDate_TV;

    private MeetingApiService mMeetingApiService;

    //for time picker and date picker
    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;
    private int mCurrentHour;
    private int mCurrentMinute;
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDayOfMonth;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.add_reunion_dialog,null);

        // for service
        mMeetingApiService = DI.getMeetingApiService();

        // referencing Dialog fields
        mSubject_ET = view.findViewById(R.id.meeting_subject_ET);
        mHour_TV = view.findViewById(R.id.meeting_hour_TV);
        mTimePicker_IB = view.findViewById(R.id.choose_hour_btn);
        mPlace_ET = view.findViewById(R.id.meeting_place_ET);
        mParticipants_ET = view.findViewById(R.id.meeting_participants_ET);
        mDate_TV = view.findViewById(R.id.meeting_date_TV);
        mDatePicker_IB = view.findViewById(R.id.choose_date_btn);

        setTimePickerDialog();
        setDatePickerDialog();
        buildDialog();

        // show alert dialog
        return dialog;
    }

    private void buildDialog() {
        builder.setView(view)
                .setTitle("Ajouter une nouvelle réunion")
                .setNegativeButton("Annuler", null)
                .setPositiveButton("Confirmer", null);

        dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String subject = mSubject_ET.getText().toString();
                        String hour = mHour_TV.getText().toString();
                        String place = mPlace_ET.getText().toString();
                        String participant = mParticipants_ET.getText().toString();
                        String meetingDate = mDate_TV.getText().toString();

                        // if all form are filled,
                        if (!(mSubject_ET.getText().toString().isEmpty() || mHour_TV.getText().toString().isEmpty()
                                || mPlace_ET.getText().toString().isEmpty()
                                || mParticipants_ET.getText().toString().isEmpty() || mDate_TV.getText().toString().isEmpty() )){

                            applyTextsToList(subject, hour, place, participant, meetingDate);
                            Toast.makeText(getContext(), "La réunion a été ajoutée", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        }
                        else
                            Toast.makeText(getContext(), "Veuillez compléter tous les champs", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // prevent dialog box from getting dismissed on outside touch
        dialog.setCanceledOnTouchOutside(false);
    }

    private void applyTextsToList(String subject, String hour, String place, String participant, String meetingDate) {
        Meeting new_meeting = new Meeting(place, hour, subject, participant, meetingDate);
        mMeetingApiService.addMeeting(new_meeting);

        mMeetingAdapter.notifyDataSetChanged();
    }

    private void setTimePickerDialog(){
        mTimePicker_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for configuring current device time
                mCalendar = Calendar.getInstance();
                mCurrentHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                mCurrentMinute = mCalendar.get(Calendar.MINUTE);

                mTimePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay < 10 && minute <10)
                            mHour_TV.setText("0" + hourOfDay +":" + "0" + minute );
                        else if (minute < 10)
                            mHour_TV.setText(hourOfDay +":" +"0"+ minute );
                        else if(hourOfDay < 10)
                            mHour_TV.setText("0" + hourOfDay +":" + minute );
                        else
                            mHour_TV.setText(hourOfDay +":" + minute);
                    }
                }, mCurrentHour, mCurrentMinute,true);
                mTimePickerDialog.show();
            }
        });
    }

    private void setDatePickerDialog(){
        mDatePicker_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for configuring current device date
                mCalendar = Calendar.getInstance();
                mCurrentYear = mCalendar.get(Calendar.YEAR);
                mCurrentMonth = mCalendar.get(Calendar.MONTH);
                mCurrentDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

                if (getContext() != null){
                    mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            if(dayOfMonth <10 && month < 10)
                                mDate_TV.setText("0"+ dayOfMonth +"/" + "0"+(month + 1) + "/"+ year);
                            else if (dayOfMonth < 10)
                                mDate_TV.setText("0"+ dayOfMonth +"/" +(month + 1) + "/"+ year);
                            else if (month < 10)
                                mDate_TV.setText(dayOfMonth +"/" +"0"+(month + 1) + "/"+ year);
                            else
                                mDate_TV.setText(dayOfMonth +"/" +(month + 1) + "/"+ year);
                        }
                    }, mCurrentYear, mCurrentMonth, mCurrentDayOfMonth);
                    mDatePickerDialog.show();}
            }
        });
    }
}