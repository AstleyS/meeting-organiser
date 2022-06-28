package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.entities.Meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreateMeetingActivity extends AppCompatActivity {

    private final String TAG = "CreateMeetingActivity";
    private final String EXTRA_MEETINGS_LIST = "meetingsList";
    private final String EXTRA_HOST = "host";

    private List<Meeting> meetingsList;
    private Host host;

    EditText title, description, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        title = (EditText) findViewById(R.id.meeting_title);
        description = (EditText) findViewById(R.id.meeting_description);
        date = (EditText) findViewById(R.id.meeting_date);
        time = (EditText) findViewById(R.id.meeting_time);

        meetingsList = (List<Meeting>) getIntent().getSerializableExtra(EXTRA_MEETINGS_LIST);
        host = (Host) getIntent().getSerializableExtra(EXTRA_HOST);

        //System.out.println("List" + meetingsList);
        this.setTitle("Create Meeting");
    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(this, HostHomepageActivity.class);
        Log.i(TAG, "Going to HostHomepageActivity");
        startActivity(intent);
        finish();
    }

    public void onClickCreateMeeting(View view) {
        if (validFields()) {
            Intent intent = new Intent(this, HostHomepageActivity.class);

            Meeting meeting = new Meeting(host.id, title.getText().toString().trim(),
                                        description.getText().toString().trim(), date.getText().toString().trim(), time.getText().toString().trim());
            meetingsList.add(meeting);

            intent.putExtra(EXTRA_MEETINGS_LIST, (Serializable) meetingsList);
            intent.putExtra(EXTRA_HOST, (Serializable) host);

            Log.i(TAG, "Going to HostHomepageActivity");
            startActivity(intent);
            finish();
        }
    }

    public void onClickDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        EditText meetingDate = (EditText) findViewById(R.id.meeting_date);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, dd MMM yyyy", calendar1).toString();
                Log.i(TAG, "Date: " + dateText);

                meetingDate.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    public void onClickTime(View view) {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        EditText meetingTime = (EditText) findViewById(R.id.meeting_time);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("hh:mm a", calendar1).toString();

                meetingTime.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
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