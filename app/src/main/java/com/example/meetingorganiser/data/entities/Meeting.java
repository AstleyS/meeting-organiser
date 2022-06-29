package com.example.meetingorganiser.data.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Meeting implements Serializable {

    public static int currID = 0;

    public int id;

    public int hostID;

    public String title;

    public String description;

    public String date;

    public String time;

    public String qrCode;

    /*
    @ColumnInfo(name = "sentiment_level")
    public byte sentiment_level;

    @ColumnInfo(name = "type_feedback")
    public String typeFeedback;
     */

    public Meeting(int hostID, String title, String description, String date, String time) {
        id = ++currID;
        this.hostID = hostID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;

    }

}
