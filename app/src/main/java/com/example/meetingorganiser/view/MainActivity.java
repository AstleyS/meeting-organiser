package com.example.meetingorganiser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.view.host.HostFormActivity;
import com.example.meetingorganiser.view.host.HostHomepageActivity;
import com.example.meetingorganiser.view.participant.ParticipantFormActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Meeting Organiser");
    }

    public void onClickHost(View view) {
        startActivity(new Intent(this, HostFormActivity.class));
        Log.i(TAG, "Going to Host Form");
    }

    public void onClickParticipant(View view) {
        startActivity(new Intent(this, ParticipantFormActivity.class));
        Log.i(TAG, "Going to Participant Form");
    }

}