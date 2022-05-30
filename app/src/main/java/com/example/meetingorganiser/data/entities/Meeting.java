package com.example.meetingorganiser.data.entities;

import java.util.Calendar;

public class Meeting {

    public int id;

    public int hostID;

    public String description;

    public Calendar date;

    public Calendar time;

    public String qrCode;

    /*
    @ColumnInfo(name = "sentiment_level")
    public byte sentiment_level;

    @ColumnInfo(name = "type_feedback")
    public String typeFeedback;
     */

    public Meeting(int hostID, String description) {
        this.hostID = hostID;
        this.description = description;
    }

}
