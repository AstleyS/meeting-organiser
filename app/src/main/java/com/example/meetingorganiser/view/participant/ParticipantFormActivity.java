package com.example.meetingorganiser.view.participant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.view.MainActivity;
import com.example.meetingorganiser.view.host.HostHomepageActivity;

public class ParticipantFormActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQ_CODE = 102;
    private final String TAG = "ParticipantFormActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreated Invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_form);
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
        /*
        Intent intent = new Intent(this, HostHomepageActivity.class);
        startActivity(intent);
        Log.i(TAG, "Going to HostHomepageActivity");
        finish();

         */
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

            ImageView signature = (ImageView) findViewById(R.id.participant_signature);
            signature.setImageBitmap(image);
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQ_CODE);
    }
}