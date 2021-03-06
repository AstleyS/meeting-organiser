package com.example.meetingorganiser.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;

import java.util.List;

@Dao
public interface ParticipantDAO {
    
    @Insert
    void insert(Participant participant);

    @Update
    void update(Participant participant);

    @Delete
    void delete(Participant participant);

    @Query("SELECT * FROM participant WHERE id=:id")
    Participant getParticipant(final String id);

    @Query("SELECT * FROM participant WHERE meetingID=:meetingID")
    List<Participant> getParticipantsOfMeeting(String meetingID);
}
