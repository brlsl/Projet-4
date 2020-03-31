package com.example.mareu.model;

public class Meeting {
    private String place;
    private String hour;
    private String subject;
    private String participant;
    private String meetingDate;


    public Meeting(String subject, String hour, String place, String participant, String meetingDate) {
        this.subject = subject;
        this.hour = hour;
        this.place = place;
        this.participant = participant;
        this.meetingDate= meetingDate;
    }

    public String getHour() {
        return hour;
    }

    public String getPlace() {
        return place;
    }

    public String getSubject() {
        return subject;
    }

    public String getParticipant() {
        return participant;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

}

