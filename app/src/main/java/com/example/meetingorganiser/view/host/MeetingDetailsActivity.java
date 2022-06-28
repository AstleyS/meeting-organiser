package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Meeting;

public class MeetingDetailsActivity extends AppCompatActivity {

    private final String EXTRA_MEETING = "meeting";
    Meeting meeting;

    EditText title, description, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);

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
}