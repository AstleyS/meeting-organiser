package com.example.meetingorganiser.data.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.meetingorganiser.data.entities.Host;

import java.util.List;

@Dao
public interface HostDAO {

    @Insert
    void insert(Host host);

    @Update
    void update(Host host);

    @Delete
    void delete(Host host);

    @Query("SELECT * FROM host")
    List<Host> getHosts();

    @Query("SELECT * FROM host WHERE id=:id")
    Host getHost(final int id);
}
