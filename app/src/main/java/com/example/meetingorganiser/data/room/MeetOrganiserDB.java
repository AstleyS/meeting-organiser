package com.example.meetingorganiser.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.data.room.dao.MeetingDAO;
import com.example.meetingorganiser.data.room.dao.ParticipantDAO;


@Database(entities = { Host.class, Meeting.class, Participant.class}, version = 6)
public abstract class MeetOrganiserDB extends RoomDatabase {

    public abstract HostDAO getHostDao();
    public abstract MeetingDAO getMeetingDAO();
    public abstract ParticipantDAO getParticipantDAO();

    private static MeetOrganiserDB INSTANCE;
    public static MeetOrganiserDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MeetOrganiserDB.class, "meeting_organiser_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


}
