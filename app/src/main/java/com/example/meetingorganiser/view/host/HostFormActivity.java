package com.example.meetingorganiser.view.host;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.view.MainActivity;

public class HostFormActivity extends AppCompatActivity {

    private final String TAG = "HostFormActivity";

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
            startActivity(intent);
            Log.i(TAG, "Going to HostHomepageActivity");
            finish();
        }
    }

    private boolean validFields() {
        boolean error = false;

        if (!fname.getText().toString().matches("[A-Za-z]+$")) {
            fname.setError("This field required or not correctly filled");
            error = true;
        }

        if (!lname.getText().toString().matches("[A-Za-z]+$")) {
            lname.setError("This field required or not correctly filled");
            error = true;
        }

        if (!phone.getText().toString().matches("^[+][(]?[0-9]{1,4}[)]?[-\\s\\./0-9]*$")) {
            phone.setError("This field required or not correctly filled");
            error = true;
        }

        if (!email.getText().toString().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            email.setError("This field required or not correctly filled");
            error = true;
        }

        return !error;
    }

}