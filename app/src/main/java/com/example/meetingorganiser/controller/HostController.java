package com.example.meetingorganiser.controller;

import android.content.Context;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.HostDAO;

public class HostController {

    public void insertHost(Host host, Context context) {
        /*
        MeetOrganiserDB meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        HostDAO hostDAO = meetOrganiserDB.getHostDao();

        hostDAO.insert(host);
         */
    }

    public Host getHost(Context context) {
        /*
        MeetOrganiserDB meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        HostDAO hostDAO = meetOrganiserDB.getHostDao();

        return hostDAO.getHosts().get(0);
         */
        return null;
    }

}
