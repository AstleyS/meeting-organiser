package com.example.meetingorganiser.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Host implements Serializable {

    @NonNull
    @PrimaryKey
    public String id;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;

    public Host(String firstName, String lastName, String phoneNumber, String email) {
        this.id = (firstName + lastName).toLowerCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
