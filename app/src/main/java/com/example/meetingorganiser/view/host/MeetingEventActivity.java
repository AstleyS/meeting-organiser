package com.example.meetingorganiser.view.host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.ParticipantController;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;
import com.example.meetingorganiser.data.entities.Participant;
import com.example.meetingorganiser.view.participant.ParticipantAdapter;
import com.example.meetingorganiser.view.participant.ParticipantHomepageActivity;

import java.util.Arrays;
import java.util.List;

public class MeetingEventActivity extends AppCompatActivity {

    ParticipantController controller;
    public static final int CONTACT_CODE = 103;

    private final String TAG = "MeetingEventActivity";
    private final String EXTRA_MEETING = "meeting";
    private final String EXTRA_HOST = "host";
    private final String EXTRA_PARTICIPANT = "participant";
    private final String EXTRA_IS_PARTICIPANT = "isParticipant";

    private Meeting meeting;
    private Host host;
    private Participant participant;
    private int isParticipant;
    List<Participant> participants;

    Chronometer timer;
    Button stopMeeting;
    TextView nrParticipant;
    ImageButton download;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_event);

        isParticipant = getIntent().getIntExtra(EXTRA_IS_PARTICIPANT, 0);
        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);
        meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);
        participant = (Participant) getIntent().getSerializableExtra(EXTRA_PARTICIPANT);

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
         participants = controller.getParticipantsOfMeeting(meeting);

         nrParticipant.setText(nrParticipant.getText().toString() + " " + (participants == null ? 0 : participants.size()));

        ParticipantAdapter adapter = new ParticipantAdapter(this, R.layout.list_item_participants, participants);
        listView.setAdapter(adapter);

        if (isParticipant == 0) checkPermissions();
    }

    public void onClickStopMeeting(View view) {
        Intent intent;

        if (isParticipant == 1) {
            intent = new Intent(this, ParticipantHomepageActivity.class);

            participant.meetingID = null;
            controller.updateParticipant(participant);
            intent.putExtra(EXTRA_PARTICIPANT, participant);
        } else {
            intent = new Intent(this, HostHomepageActivity.class);
            intent.putExtra(EXTRA_HOST, host);
        }

        startActivity(intent);
        finish();
    }

    public void onClickDownloadMParticipants(View view) { }

    private void checkContacts() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Cursor cursor = getContentResolver().query(uri, null, null,null, null);
        CursorLoader cursorLoader = new CursorLoader(this, uri, null, null,null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                for (Participant p : participants) {
                    String nameP = p.firstName + " " + p.lastName;
                    if (name.equals(nameP)) {
                        Toast.makeText(this, "Your contact " + p.phoneNumber + " is in the meeting!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_CODE);
        } else {
            checkContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACT_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkContacts();
            } else {
                Toast.makeText(this, "Contacts Permission is required to read them!", Toast.LENGTH_LONG).show();
            }
        }
    }

}