package com.example.meetingorganiser.view.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.meetingorganiser.R;

public class ParticipantHomepageActivity extends AppCompatActivity {

    private final String TAG = "ParticipantHomepageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_homepage);

        this.setTitle("Participant Homepage");
    }
}