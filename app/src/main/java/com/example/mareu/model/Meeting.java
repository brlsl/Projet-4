package com.example.mareu.model;

public class Meeting {
    private String place;
    private String hour;
    private String subject;
    private String participant;

    public Meeting(String subject, String hour, String place, String participant) {
        this.subject = subject;
        this.hour = hour;
        this.place = place;
        this.participant = participant;
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

}

