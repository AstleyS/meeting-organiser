package com.example.meetingorganiser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.view.host.HostHomepageActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickHost(View view) {
        startActivity(new Intent(this, HostHomepageActivity.class));
        Log.i(TAG, "Going to Host Homepage");
        // finish();
    }

    public void onClickParticipant(View view) {
        //startActivity(new Intent(this, ParticipantHomepageActivity.class));
        //Log.i(TAG, "Going to Participant Homepage");
        // finish();
    }

}