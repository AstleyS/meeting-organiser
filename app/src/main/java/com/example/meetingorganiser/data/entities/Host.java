package com.example.meetingorganiser.data.entities;

public class Host {

    public int id;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;

    public String profilePic;

    public Host(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
