package com.example.meetingorganiser.data.entities;

import java.io.Serializable;

public class Host implements Serializable {

    public int id;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String email;


    public Host(String firstName, String lastName, String phoneNumber, String email) {
        id = 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
