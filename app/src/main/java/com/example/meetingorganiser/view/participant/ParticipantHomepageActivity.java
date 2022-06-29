package com.example.meetingorganiser.view.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Participant;

public class ParticipantHomepageActivity extends AppCompatActivity {

    private final String TAG = "ParticipantHomepageActivity";
    private final String EXTRA_PARTICIPANT = "participant";

    Participant participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_homepage);

        participant = (Participant) getIntent().getSerializableExtra(EXTRA_PARTICIPANT);

        this.setTitle(participant.firstName + "'s Homepage");
    }
}