package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingApiServiceGenerator {

    public static List<Meeting> generateMeetingList(){
        return new ArrayList<>( MEETING_LIST);
    }

    public static List <Meeting> MEETING_LIST = Arrays.asList(
            new Meeting("Réunion A","14:00","Peach","test@gmail.com; test2@gmail.com; test3@gmail.com","01/01/2020"),
            new Meeting("Réunion B","16:00","Mario","test@gmail.com; test2@gmail.com; test3@gmail.com","31/08/2020"),
            new Meeting("Réunion C","19:00","Luigi","test@gmail.com; test2@gmail.com; test3@gmail.com","18/11/2020")
    );

}
