package com.example.mareu.model;

import java.util.Date;

public class Meeting {
    private String place;
    private String hour;
    private String subject;
    private String participant;
    private int date;

    public Meeting(String subject, String hour, String place, String participant, int date) {
        this.subject = subject;
        this.hour = hour;
        this.place = place;
        this.participant = participant;
        this.date = date;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}

