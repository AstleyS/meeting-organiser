package com.example.meetingorganiser.controller;

import android.content.Context;

import androidx.room.Room;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;

import java.util.List;

public class HostController {

    private Context context;
    private MeetOrganiserDB meetOrganiserDB;

    public HostController(Context context) {
       this.context = context;
       meetOrganiserDB = MeetOrganiserDB.getInstance(context);
    }

    public void insertHost(Host host) {
        HostDAO hostDAO = meetOrganiserDB.getHostDao();
        hostDAO.insert(host);
    }

    public List<Meeting> getMeetingsOfHost(Context context, int id) {
        MeetingDAO meetingDAO = meetOrganiserDB.getMeetingsOfHostDAO();
        return meetingDAO.getMeetingsOfHost(id);

    }

}
