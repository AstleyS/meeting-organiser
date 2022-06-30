package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.ParticipantController;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;

import java.io.Serializable;
import java.util.List;

public class MeetingEventActivity extends AppCompatActivity {

    ParticipantController controller;

    private final String TAG = "MeetingEventActivity";
    private final String EXTRA_MEETING = "meeting";
    private final String EXTRA_HOST = "host";

    private List<Meeting> meetingsList;
    private Meeting meeting;
    private Host host ;

    Chronometer timer;
    Button stopMeeting;
    TextView nrParticipant;
    ImageButton download;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_event);

        meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);
        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);

        timer = (Chronometer) findViewById(R.id.timer);
        stopMeeting = (Button) findViewById(R.id.stop_button);
        nrParticipant = (TextView) findViewById(R.id.meeting_participants_nr);
        download = (ImageButton) findViewById(R.id.meeting_participants_download);
        listView = (ListView) findViewById(R.id.meeting_participants);

        setTitle("\"" + meeting.title + "\"" + " meeting");

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000 ;
                String t = (h < 10 ? "0"+ h: h)+ ":" +  (m < 10 ? "0"+ m: m) + ":" + (s < 10 ? "0" + s: s);
                chronometer.setText(t);
            }
        });
         timer.setBase(SystemClock.elapsedRealtime());
         timer.setText("00:00:00");
         timer.start();

         controller = new ParticipantController(getApplicationContext());
         List<Participant> participants = controller.getParticipantsOfMeeting(meeting);
         nrParticipant.setText(nrParticipant.getText().toString() + " " + (participants == null ? 0 : participants.size()));
    }

    public void onClickStopMeeting(View view) {
        Intent intent = new Intent(this, HostHomepageActivity.class);

        intent.putExtra(EXTRA_HOST, host);
        startActivity(intent);
        finish();
    }

    public void onClickDownloadMParticipants(View view) { }
}