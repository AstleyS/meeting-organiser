package com.example.meetingorganiser.data.room;

import android.content.Context;
/*
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetingorganiser.data.entities.MeetingParticipant;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;
 */

// @Database(entities = { Host.class, Meeting.class, Participant.class, MeetingParticipant.class},
   //     version = 1)
public abstract class MeetOrganiserDB /*extends RoomDatabase*/ {

    /*
    public abstract HostDAO getHostDao();
    public abstract MeetingDAO getMeetingDao();
    public abstract MeetingParticipantDAO getMeetingParticipantDAO();

    private static MeetOrganiserDB INSTANCE;

    public static MeetOrganiserDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MeetOrganiserDB.class, "meeting_organiser_db")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
     */

}
