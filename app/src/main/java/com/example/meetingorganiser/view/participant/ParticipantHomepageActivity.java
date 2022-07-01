package com.example.meetingorganiser.view.participant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.MeetingController;
import com.example.meetingorganiser.controller.ParticipantController;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;
import com.example.meetingorganiser.view.host.MeetingEventActivity;

import java.util.List;

public class ParticipantHomepageActivity extends AppCompatActivity {

    ParticipantController pController;
    MeetingController mController;

    private final String TAG = "ParticipantHomepageActivity";
    private final String EXTRA_MEETING = "meeting";
    private final String EXTRA_PARTICIPANT = "participant";
    private final String EXTRA_IS_PARTICIPANT = "isParticipant";

    Participant participant;
    Meeting meeting;

    TextView noMeetings;
    EditText insertedMeetingID;
    ImageButton participate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_homepage);

        participant = (Participant) getIntent().getSerializableExtra(EXTRA_PARTICIPANT);
        this.setTitle(participant.firstName + "'s Homepage");

        noMeetings = (TextView) findViewById(R.id.no_meetings);
        insertedMeetingID = (EditText) findViewById(R.id.insert_meeting_id);
        participate = (ImageButton) findViewById(R.id.participate_meeting);

        pController = new ParticipantController(getApplicationContext());
        mController = new MeetingController(getApplicationContext());
    }

    public void onClickParticipateMeeting(View view) {

        List<Meeting> meetings = mController.getMeetings();

        if (meetings != null && meetings.size() != 0) {
            Intent intent = new Intent(this, MeetingEventActivity.class);

            if (meetings.stream().anyMatch(m -> m.id.equals(insertedMeetingID.getText().toString().trim()))) {
                participant.meetingID = insertedMeetingID.getText().toString().trim();

                meeting = mController.getMeeting(participant.meetingID);
                pController.updateParticipant(participant);

                intent.putExtra(EXTRA_PARTICIPANT, participant);
                intent.putExtra(EXTRA_IS_PARTICIPANT, 1);
                intent.putExtra(EXTRA_MEETING, meeting);

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "There are no Meetings with this ID!", Toast.LENGTH_SHORT).show();
            }
        } else {
            noMeetings.setVisibility(View.VISIBLE);
        }
    }
}