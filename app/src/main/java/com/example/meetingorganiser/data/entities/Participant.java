package com.example.meetingorganiser.data.entities;

import java.io.Serializable;

public class Participant implements Serializable {

    public int id;

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
