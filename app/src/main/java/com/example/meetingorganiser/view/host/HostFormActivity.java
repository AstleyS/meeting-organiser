package com.example.meetingorganiser.view.host;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.controller.HostController;
import com.example.meetingorganiser.data.entities.Host;
import com.example.meetingorganiser.data.room.dao.HostDAO;
import com.example.meetingorganiser.view.MainActivity;

import java.io.Serializable;

public class HostFormActivity extends AppCompatActivity {

    HostController controller;

    private final String TAG = "HostFormActivity";
    private final String EXTRA_HOST = "host";
    private final String PREFERENCES_NAME = "host.dataStorageForm";
    private SharedPreferences sp;

    private final String FNAME_KEY = "h_fname";
    private final String LNAME_KEY = "h_lname";
    private final String PHONE_KEY = "h_phone";
    private final String EMAIL_KEY = "h_email";

    EditText fname, lname, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_form);

        this.setTitle("Host Form");

        fname = (EditText) findViewById(R.id.host_fname);
        lname = (EditText) findViewById(R.id.host_lname);
        phone = (EditText) findViewById(R.id.host_phone_number);
        email = (EditText) findViewById(R.id.host_email);

        sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        String fname_sp = sp.getString(FNAME_KEY, null);
        String lname_sp = sp.getString(LNAME_KEY, null);
        String phone_sp = sp.getString(PHONE_KEY, null);
        String email_sp = sp.getString(EMAIL_KEY, null);

        if (fname_sp != null) fname.setText(fname_sp);
        if (lname_sp != null) lname.setText(lname_sp);
        if (phone_sp != null) phone.setText(phone_sp);
        if (email_sp != null) email.setText(email_sp);

        controller = new HostController(getApplicationContext());
    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.i(TAG, "Going to MainActivity");
        finish();
    }

    public void onClickCreateHost(View view) {
        if (validFields()) {
            Intent intent = new Intent(this, HostHomepageActivity.class);

            Host host = new Host(fname.getText().toString().trim(), lname.getText().toString().trim(),
                                phone.getText().toString().trim(), email.getText().toString().trim());

            Host hostDB = controller.getHost(host);
            if (hostDB == null) controller.insertHost(host);

            SharedPreferences.Editor editor = sp.edit();
            editor.putString(FNAME_KEY, fname.getText().toString().trim());
            editor.putString(LNAME_KEY, lname.getText().toString().trim());
            editor.putString(PHONE_KEY, phone.getText().toString().trim());
            editor.putString(EMAIL_KEY, email.getText().toString().trim());
            editor.apply();

            intent.putExtra(EXTRA_HOST, host);
            startActivity(intent);
            Log.i(TAG, "Going to HostHomepageActivity");
            finish();
        }
    }

    private boolean validFields() {
        boolean error = false;

        if (!fname.getText().toString().trim().matches("[-\\sA-Za-z]+$")) {
            fname.setError("This field required or not correctly filled");
            error = true;
        }

        if (!lname.getText().toString().trim().matches("[-\\sA-Za-z]+$")) {
            lname.setError("This field required or not correctly filled");
            error = true;
        }

        if (!phone.getText().toString().trim().matches("^[+][(]?[0-9]{1,4}[)]?[-\\s\\./0-9]*$")) {
            phone.setError("This field required or not correctly filled");
            error = true;
        }

        if (!email.getText().toString().trim().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            email.setError("This field required or not correctly filled");
            error = true;
        }

        return !error;
    }

}