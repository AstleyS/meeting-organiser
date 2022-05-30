package com.example.meetingorganiser.data.entities;

public class Participant {

    public int id;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;

    public String profilePic;

    public String signature;

    public Participant(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
