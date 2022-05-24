package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.meetingorganiser.R;

public class CreateMeetingActivity extends AppCompatActivity {

    private final String TAG = "CreateMeetingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
    }
}