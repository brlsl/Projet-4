package com.example.mareu.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Meeting {
    private String place;
    private String hour;
    private String subject;
    private String participant;
    private String meetingDate;
    private String fusion;


    public Meeting(String subject, String hour, String place, String participant, String meetingDate) {
        this.subject = subject;
        this.hour = hour;
        this.place = place;
        this.participant = participant;
        this.meetingDate= meetingDate;
        this.fusion = meetingDate+" "+hour;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getFusion() {
        return fusion;
    }

    public void setFusion(String fusion) {
        this.fusion = fusion;
    }

}

