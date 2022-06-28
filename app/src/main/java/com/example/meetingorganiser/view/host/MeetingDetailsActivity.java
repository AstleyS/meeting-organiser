package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;

import java.io.Serializable;
import java.util.List;

public class MeetingDetailsActivity extends AppCompatActivity {

    private final String TAG = "MeetingDetailsActivity";
    private final String EXTRA_HOST = "host";
    private final String EXTRA_MEETING = "meeting";
    private final String EXTRA_MEETINGS_LIST = "meetingsList";

    private List<Meeting> meetingsList;
    Meeting meeting;
    Host host;

    EditText title, description, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);
        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);
        meetingsList = (List<Meeting>) getIntent().getSerializableExtra(EXTRA_MEETINGS_LIST);

        setTitle(meeting.title);

        title = (EditText) findViewById(R.id.meeting_title);
        description = (EditText) findViewById(R.id.meeting_description);
        date = (EditText) findViewById(R.id.meeting_date);
        time = (EditText) findViewById(R.id.meeting_time);

        title.setText(meeting.title);
        description.setText(meeting.description);
        date.setText(meeting.date);
        time.setText(meeting.time);

    }

    public void onClickStartMeeting(View view) {}

    public void onClickUpdate(View view) {}

    public void onClickDelete(View view) {

        meetingsList.removeIf(m -> m.id == meeting.id);

        Intent intent = new Intent(this, HostHomepageActivity.class);
        intent.putExtra(EXTRA_HOST, host);
        intent.putExtra(EXTRA_MEETINGS_LIST, (Serializable) meetingsList);

        Log.i(TAG, "oDelete. Going to Host Homepage");
        startActivity(intent);
        finish();
    }
}