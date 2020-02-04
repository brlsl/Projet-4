package com.example.mareu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mareu.model.Meeting;

import static com.example.mareu.MainActivity.mMeetingAdapter;

public class MeetingDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ajouter une nouvelle réunion")
                .setMessage("Test de la Dialog")
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Meeting test = new Meeting("123","456","789", "@gmail.com");
                        MyMeetingAdapter.mMeetingList.add(test);
                        mMeetingAdapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });

        // return a created Dialog
        return builder.create();
    }
}
