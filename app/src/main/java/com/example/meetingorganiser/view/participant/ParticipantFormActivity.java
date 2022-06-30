package com.example.meetingorganiser.view.participant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Participant;
import com.example.meetingorganiser.view.MainActivity;

import java.io.ByteArrayOutputStream;

public class ParticipantFormActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQ_CODE = 102;
    private final String TAG = "ParticipantFormActivity";
    private final String EXTRA_PARTICIPANT = "participant";
    private final String PREFERENCES_NAME = "participant.dataStorageForm";
    private SharedPreferences sp;

    private final String FNAME_KEY = "p_fname";
    private final String LNAME_KEY = "p_lname";
    private final String PHONE_KEY = "p_phone";
    private final String EMAIL_KEY = "p_email";
    private final String SIGNATURE_KEY = "p_signature";

    EditText fname, lname, phone, email;
    ImageView signature;
    String signatureStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_form);

        this.setTitle("Participant Form");

        fname = (EditText) findViewById(R.id.participant_fname);
        lname = (EditText) findViewById(R.id.participant_lname);
        phone = (EditText) findViewById(R.id.participant_phone_number);
        email = (EditText) findViewById(R.id.participant_email);
        signature = (ImageView) findViewById(R.id.participant_signature);

        sp = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        String fname_sp = sp.getString(FNAME_KEY, null);
        String lname_sp = sp.getString(LNAME_KEY, null);
        String phone_sp = sp.getString(PHONE_KEY, null);
        String email_sp = sp.getString(EMAIL_KEY, null);
        String signature_sp = sp.getString(SIGNATURE_KEY, null);

        if (fname_sp != null) fname.setText(fname_sp);
        if (lname_sp != null) lname.setText(lname_sp);
        if (phone_sp != null) phone.setText(phone_sp);
        if (email_sp != null) email.setText(email_sp);
        if (signature_sp != null) {
            signatureStr = signature_sp;
            byte [] encodeByte = Base64.decode(signature_sp,Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            signature.setImageBitmap(image);
        }

    }

    public void onClickAddSignature(View view) {
        Log.i(TAG, " onClickAddSignature");
        askCameraPermission();
    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.i(TAG, "Going to MainActivity");
        finish();
    }

    public void onClickCreateParticipant(View view) {
        if (validFields()) {
            Intent intent = new Intent(this, ParticipantHomepageActivity.class);

            Participant participant = new Participant(fname.getText().toString().trim(), lname.getText().toString().trim(),
                    phone.getText().toString().trim(), email.getText().toString().trim(), signatureStr);

            SharedPreferences.Editor editor = sp.edit();
            editor.putString(FNAME_KEY, fname.getText().toString().trim());
            editor.putString(LNAME_KEY, lname.getText().toString().trim());
            editor.putString(PHONE_KEY, phone.getText().toString().trim());
            editor.putString(EMAIL_KEY, email.getText().toString().trim());
            editor.putString(SIGNATURE_KEY, signatureStr);
            editor.apply();

            intent.putExtra(EXTRA_PARTICIPANT, participant);
            startActivity(intent);
            Log.i(TAG, "Going to ParticipantHomepageActivity");
            finish();
        }
    }

    private boolean validFields() {
        boolean error = false;

        if (!fname.getText().toString().matches("[-\\sA-Za-z]+$")) {
            fname.setError("This field required or not correctly filled");
            error = true;
        }

        if (!lname.getText().toString().matches("[-\\sA-Za-z]+$")) {
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

        if (signature.getDrawable() == null || signatureStr.length() == 0) {
            error = true;
            Toast.makeText(this, "Signature required!", Toast.LENGTH_LONG).show();Toast.makeText(this, "Signature required!", Toast.LENGTH_LONG).show();
        }

        return !error;
    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permission is required to use camera!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQ_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            signatureStr = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);

            ImageView signature = (ImageView) findViewById(R.id.participant_signature);
            signature.setImageBitmap(image);
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQ_CODE);
    }
}