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
            new Meeting("a","b","c", "aaaaaaaaaaaaaaaaaaa@gmail.com"),
            new Meeting("d","e", "f", "test@gmail.com, test2@gmail.com"),
            new Meeting("g","h", "i", "test@gmail.com, test2@gmail.com"),
            new Meeting("j","k", "l", "test@gmail.com, test2@gmail.com"),
            new Meeting("m","n", "o", "test@gmail.com, test2@gmail.com"),
            new Meeting("p","p", "q", "test@gmail.com, test2@gmail.com"),
            new Meeting("r","s", "t","test@gmail.com, test2@gmail.com")
    );

}
