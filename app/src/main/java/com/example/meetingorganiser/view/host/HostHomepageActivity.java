package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.meetingorganiser.R;

public class HostHomepageActivity extends AppCompatActivity {

    private final String TAG = "HostHomepageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_homepage);

        String[] meetings = {"M1", "M2", "M3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, meetings);
        ListView listView = (ListView) findViewById(R.id.list_meetings);
        listView.setAdapter(adapter);
    }

    public void onClickCreateMeeting(View view) {
        startActivity(new Intent(this, CreateMeetingActivity.class));
        Log.i(TAG, "Going to Create Meeting Homepage");
    }
}