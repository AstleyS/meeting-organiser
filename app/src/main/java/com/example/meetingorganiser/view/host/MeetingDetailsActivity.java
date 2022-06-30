package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.MeetingController;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;

import java.io.Serializable;
import java.util.List;

public class MeetingDetailsActivity extends AppCompatActivity {

    MeetingController controller;

    private final String TAG = "MeetingDetailsActivity";
    private final String EXTRA_HOST = "host";
    private final String EXTRA_MEETING = "meeting";

    private Meeting meeting;
    private Host host;

    EditText title, description, date, time;
    Button startButton, updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);
        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);

        setTitle(meeting.title);

        title = (EditText) findViewById(R.id.meeting_title);
        description = (EditText) findViewById(R.id.meeting_description);
        date = (EditText) findViewById(R.id.meeting_date);
        time = (EditText) findViewById(R.id.meeting_time);
        startButton = (Button) findViewById(R.id.start_button);
        updateButton = (Button) findViewById(R.id.update_button);
        deleteButton = (Button) findViewById(R.id.delete_button);

        title.setText(meeting.title);
        description.setText(meeting.description);
        date.setText(meeting.date);
        time.setText(meeting.time);

        controller = new MeetingController(getApplicationContext());
    }

    public void onClickStartMeeting(View view) {
        Intent intent = new Intent(this, MeetingEventActivity.class);

        meeting.available = 0;
        controller.updateMeeting(meeting);

        intent.putExtra(EXTRA_MEETING, meeting);
        intent.putExtra(EXTRA_HOST, host);

        Toast.makeText(this, "Meeting \" " + meeting.title + "\" is starting...", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStart. Going to MeetingEventActivity");
        startActivity(intent);
        finish();
    }

    public void onClickUpdate(View view) {
        if (updateButton.getText().toString().equals("Save Update")) {
            if (validFields()) {
                Intent intent = new Intent(this, HostHomepageActivity.class);

                Meeting updatedMeeting = new Meeting(host.id, title.getText().toString().trim(),
                                    description.getText().toString().trim(), date.getText().toString().trim(), time.getText().toString().trim());

                List<Meeting> meetingsList = controller.getMeetingsOfHost(host);
                if (meetingsList == null || meetingsList.stream().noneMatch(m -> m.id.equals(updatedMeeting.id))) {
                    controller.deleteMeeting(meeting);
                    controller.insertMeeting(updatedMeeting);
                } else {
                    Toast.makeText(this, "A Meeting with the same values already exists!", Toast.LENGTH_SHORT).show();
                }

                intent.putExtra(EXTRA_HOST, host);
                Toast.makeText(this, "Meeting \" " + meeting.title + "\" updated!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onUpdate. Going to HostHomepageActivity");
                startActivity(intent);
                finish();
            }
        } else {
            updateButton.setText("Save Update");
            title.setEnabled(true);
            description.setEnabled(true);
            date.setEnabled(true);
            time.setEnabled(true);

            startButton.setVisibility(View.GONE);
            deleteButton.setText("Cancel");
        }
    }

    public void onClickDelete(View view) {
        if (updateButton.getText().toString().equals("Save Update")) {

            startButton.setVisibility(View.VISIBLE);
            updateButton.setText("Update");
            deleteButton.setText("Delete");

            title.setText(meeting.title);
            description.setText(meeting.description);
            date.setText(meeting.date);
            time.setText(meeting.time);

            title.setEnabled(false);
            description.setEnabled(false);
            date.setEnabled(false);
            time.setEnabled(false);

        } else {
            controller.deleteMeeting(meeting);

            Intent intent = new Intent(this, HostHomepageActivity.class);
            intent.putExtra(EXTRA_HOST, host);

            Toast.makeText(this, "Meeting \" " + meeting.title + "\" deleted!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onDelete. Going to HostHomepageActivity");
            startActivity(intent);
            finish();
        }
    }

    private boolean validFields() {
        boolean error = false;

        if (title.getText().toString().trim().length() == 0) {
            title.setError("This field is required");
            error = true;
        }
        if (description.getText().toString().trim().length() == 0) {
            description.setError("This field is required");
            error = true;
        }
        if (date.getText().toString().trim().length() == 0) {
            date.setError("This field is required");
            error = true;
        }
        if (time.getText().toString().trim().length() == 0) {
            time.setError("This field is required");
            error = true;
        }

        return !error;
    }
}