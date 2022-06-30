package com.example.meetingorganiser.controller;

import android.content.Context;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;

import java.util.List;

public class HostController {

    private Context context;
    private MeetOrganiserDB meetOrganiserDB;
    private HostDAO hostDAO;
    private MeetingDAO meetingDAO;

    public HostController(Context context) {
       this.context = context;
       meetOrganiserDB = MeetOrganiserDB.getInstance(context);

       hostDAO = meetOrganiserDB.getHostDao();
       meetingDAO = meetOrganiserDB.getMeetingDAO();
    }

    public void insertHost(Host host) {
        hostDAO.insert(host);
    }

    public Host getHost(Host host) {
        return hostDAO.getHost(host.id);
    }

    public List<Meeting> getMeetingsOfHost(String id) { return meetingDAO.getMeetingsOfHost(id); }

}
