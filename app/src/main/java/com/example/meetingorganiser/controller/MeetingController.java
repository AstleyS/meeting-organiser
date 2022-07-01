package com.example.meetingorganiser.controller;

import android.content.Context;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;

import java.util.List;

public class MeetingController {

    private Context context;
    private MeetOrganiserDB meetOrganiserDB;
    private  MeetingDAO meetingDAO;

    public MeetingController(Context context) {
        this.context = context;
        meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        meetingDAO = meetOrganiserDB.getMeetingDAO();
    }

    public void insertMeeting(Meeting meeting) {
        meetingDAO.insert(meeting);
    }

    public void updateMeeting(Meeting meeting) { meetingDAO.update(meeting); }

    public void deleteMeeting(Meeting meeting) {
        meetingDAO.delete(meeting);
    }

    public Meeting getMeeting(String meeting) { return meetingDAO.getMeeting(meeting); }

    public List<Meeting> getMeetings() { return meetingDAO.getMeetings(); }

    public List<Meeting> getMeetingsOfHost(Host host) { return meetingDAO.getMeetingsOfHost(host.id); }

}
