package com.example.meetingorganiser.controller;

import android.content.Context;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;
import com.example.meetingorganiser.data.room.MeetOrganiserDB;
import com.example.meetingorganiser.data.room.dao.ParticipantDAO;

import java.util.List;

public class ParticipantController {

    private Context context;
    private MeetOrganiserDB meetOrganiserDB;
    private ParticipantDAO participantDAO;

    public ParticipantController(Context context) {
        this.context = context;
        meetOrganiserDB = MeetOrganiserDB.getInstance(context);
        participantDAO = meetOrganiserDB.getParticipantDAO();
    }

    public void insertParticipant(Participant participant) {
        participantDAO.insert(participant);
    }

    public void updateParticipant(Participant participant) {
        participantDAO.update(participant);
    }

    public void deleteParticipant(Participant participant) {
        participantDAO.delete(participant);
    }

    public Participant getParticipant(Participant participant) {
        return participantDAO.getParticipant(participant.id);
    }

    public List<Participant> getParticipantsOfMeeting(Meeting meeting) {
        return participantDAO.getParticipantsOfMeeting(meeting.id);
    }

}
