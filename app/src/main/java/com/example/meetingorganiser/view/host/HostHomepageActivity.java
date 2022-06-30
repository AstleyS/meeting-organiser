package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.HostController;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostHomepageActivity extends AppCompatActivity {

    HostController controller;

    private final String TAG = "HostHomepageActivity";
    private final String EXTRA_MEETINGS_LIST = "meetingsList";
    private final String EXTRA_MEETING = "meeting";
    private final String EXTRA_HOST = "host";

    private List<Meeting> meetingsList;
    private Host host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_homepage);

        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);
        this.setTitle(host.firstName + "'s Homepage");

        controller = new HostController(getApplicationContext());
        meetingsList = controller.getMeetingsOfHost(host.id);
        if (meetingsList == null) meetingsList = new ArrayList<>();

        // System.out.println("oncreate list: " + meetingsList);
        // System.out.println("oncreate host: " + host);

        MeetingAdapter adapter = new MeetingAdapter(this, R.layout.list_item_meeting, meetingsList.stream().filter(m -> m.available == 1).collect(Collectors.toList()));
        ListView listView = (ListView) findViewById(R.id.list_meetings);
        listView.setAdapter(adapter);

        Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meeting meeting = adapter.getItem(i);
                Intent intent = new Intent(context, MeetingDetailsActivity.class);

                intent.putExtra(EXTRA_MEETING, meeting);
                intent.putExtra(EXTRA_HOST, host);

                Log.i(TAG, "Going to Meeting Details");
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClickCreateMeeting(View view) {
        Intent intent = new Intent(this, CreateMeetingActivity.class);

        // System.out.println(meetingsList);
        intent.putExtra(EXTRA_HOST, host);

        Log.i(TAG, "Going to Create Meeting Homepage");
        startActivity(intent);
        finish();
    }
}