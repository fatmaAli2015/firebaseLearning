package com.example.fatmaali.firebaselearning;

import android.app.ProgressDialog;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class RecordAudioFirebaseStorageUploadAudio extends AppCompatActivity {

    private Button mRecordBtn;
    private TextView mRecordLabel;
    private StorageReference mStorage;
    private MediaRecorder mRecorder;
    private ProgressDialog mProgressDialog;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio_firebase_storage_upload_audio);
        mStorage = FirebaseStorage.getInstance().getReference();
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/recor.m4a";
        mProgressDialog = new ProgressDialog(this);
        mRecordBtn = (Button) findViewById(R.id.btn_Record);
        mRecordLabel = (TextView) findViewById(R.id.txtRecord);

        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startRecording();
                    mRecordLabel.setText("Recording started....");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopRecording();
                    mRecordLabel.setText("Recording stopped.....");
                }
                return false;
            }
        });
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        uploadAudio();
    }

    private void uploadAudio() {
        mProgressDialog.setMessage("Upload Audio...");
        mProgressDialog.show();
        StorageReference filepath = mStorage.child("Audio").child("new_audio.3gp");
        Uri uri = Uri.fromFile(new File(mFileName));
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mProgressDialog.dismiss();
                mRecordLabel.setText("Uploading finished...");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
