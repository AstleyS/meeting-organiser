package com.example.meetingorganiser.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Host.class,
        parentColumns = "id",
        childColumns = "meetingID",
        onDelete =  ForeignKey.CASCADE))
public class Participant implements Serializable {

    @PrimaryKey
    public int id;

    public int meetingID;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;

    public String signature;

    public Participant(String firstName, String lastName, String phoneNumber, String email, String signature) {
        id = 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.signature = signature;
    }
}
