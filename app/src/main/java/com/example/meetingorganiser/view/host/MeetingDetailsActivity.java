package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button startButton, updateButton, deleteButton;

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
        startButton = (Button) findViewById(R.id.start_button);
        updateButton = (Button) findViewById(R.id.update_button);
        deleteButton = (Button) findViewById(R.id.delete_button);

        title.setText(meeting.title);
        description.setText(meeting.description);
        date.setText(meeting.date);
        time.setText(meeting.time);
    }

    public void onClickStartMeeting(View view) {}

    public void onClickUpdate(View view) {
        if (updateButton.getText().toString().equals("Save Update")) {
            if (validFields()) {
                Intent intent = new Intent(this, HostHomepageActivity.class);

                meeting.title = title.getText().toString().trim();
                meeting.description = description.getText().toString().trim();
                meeting.date = date.getText().toString().trim();
                meeting.time = time.getText().toString().trim();

                meetingsList.removeIf(m -> m.id == meeting.id);
                meetingsList.add(meeting);
                intent.putExtra(EXTRA_MEETINGS_LIST, (Serializable) meetingsList);
                intent.putExtra(EXTRA_HOST, (Serializable) host);

                System.out.println(meeting.title);
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
            meetingsList.removeIf(m -> m.id == meeting.id);
            Meeting.currID--;

            Intent intent = new Intent(this, HostHomepageActivity.class);
            intent.putExtra(EXTRA_HOST, host);
            intent.putExtra(EXTRA_MEETINGS_LIST, (Serializable) meetingsList);

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