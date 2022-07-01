package com.example.meetingorganiser.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Meeting.class,
        parentColumns = "id",
        childColumns = "meetingID",
        onDelete =  ForeignKey.CASCADE))
public class Participant implements Serializable {

    @NonNull
    @PrimaryKey
    public String id;

    public String meetingID;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;

    public String signature;

    public Participant(String firstName, String lastName, String phoneNumber, String email, String signature) {
        this.id = (firstName + lastName).toLowerCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.signature = signature;
    }
}
