package com.example.mareu.api;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingApiServiceGenerator {

    public static List<Meeting> generateMeetingList(){
        return new ArrayList<>( MEETING_LIST);
    }

    private static List <Meeting> MEETING_LIST = Arrays.asList(
            new Meeting("place1","12h00","sujet1","test@gmail.com, test2@gmail.com, test3@gmail.com"),
            new Meeting("place2","11h00","sujet2","test@gmail.com, test2@gmail.com"),
            new Meeting("place3","18h50","sujet3","test@gmail.com, test2@gmail.com"),
            new Meeting("place4","01h20","sujet4","test@gmail.com, test2@gmail.com")
    );

}
