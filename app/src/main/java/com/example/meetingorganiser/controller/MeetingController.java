package com.example.meetingorganiser.controller;

import android.content.Context;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;

import java.util.List;

public class MeetingController {

    public void insertMeeting(Context context, Meeting meeting) {
        /*
        MeetOrganiserDB meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        MeetingDAO meetingDAO = meetOrganiserDB.getMeetingDao();

        meetingDAO.insert(meeting);
         */
    }

    public List<Meeting> getMeetingsOfHost(Context context, Host host) {
        /*
        MeetOrganiserDB meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        MeetingDAO meetingDAO = meetOrganiserDB.getMeetingDao();

        return meetingDAO.getMeetingsOfHost(host.id);
         */
        return null;
    }

}
