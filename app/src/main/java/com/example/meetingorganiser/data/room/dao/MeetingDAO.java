package com.example.meetingorganiser.data.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetingorganiser.data.entities.Meeting;

import java.util.List;

@Dao
public interface MeetingDAO {

    @Insert
    void insert(Meeting meeting);

    @Update
    void update(Meeting meeting);

    @Delete
    void delete(Meeting meeting);

    @Query("SELECT * FROM meeting")
    List<Meeting> getMeetings();

    @Query("SELECT * FROM meeting WHERE hostID=:hostID")
    List<Meeting> getMeetingsOfHost(int hostID);
}
