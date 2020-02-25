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
            new Meeting("Réunion A","14h00","Peach","test@gmail.com, test2@gmail.com, test3@gmail.com","01/01/2019"),
            new Meeting("Réunion B","16h00","Mario","test@gmail.com, test2@gmail.com, test3@gmail.com","25/02/2020"),
            new Meeting("Réunion C","19h50","Luigi","test@gmail.com, test2@gmail.com, test3@gmail.com","30/12/2021")
    );

}
