package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.meetingorganiser.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HostHomepageActivity extends AppCompatActivity {

    private final String TAG = "HostHomepageActivity";
    private final String EXTRA_MEETINGS_LIST = "meetingsList";
    private List<String> meetingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_homepage);

        this.setTitle("Host Homepage");

        meetingsList = (List<String>) getIntent().getSerializableExtra(EXTRA_MEETINGS_LIST);
        System.out.println(meetingsList);

        if (meetingsList == null) {
            meetingsList = new ArrayList<>();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, meetingsList);
        ListView listView = (ListView) findViewById(R.id.list_meetings);
        listView.setAdapter(adapter);
    }

    public void onClickCreateMeeting(View view) {
        Intent intent = new Intent(this, CreateMeetingActivity.class);

        System.out.println(meetingsList);
        intent.putExtra(EXTRA_MEETINGS_LIST, (Serializable) meetingsList);

        Log.i(TAG, "Going to Create Meeting Homepage");
        startActivity(intent);
    }
}