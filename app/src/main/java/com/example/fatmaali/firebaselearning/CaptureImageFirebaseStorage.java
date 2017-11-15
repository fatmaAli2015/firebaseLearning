package com.example.fatmaali.firebaselearning;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CaptureImageFirebaseStorage extends AppCompatActivity {
    private Button mCaptureImg;
    private ImageView mImageView;
    private StorageReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image_firebase_storage);
        mCaptureImg = (Button) findViewById(R.id.btn_CaptureImage);
        mImageView = (ImageView) findViewById(R.id.imageCaptureView);
        mReference = FirebaseStorage.getInstance().getReference();

        mCaptureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference filepath = mReference.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri1 = taskSnapshot.getDownloadUrl();
                    Toast.makeText(getApplicationContext(),"hhhhhhh",Toast.LENGTH_SHORT).show();
                    Picasso.with(CaptureImageFirebaseStorage.this).load(uri1.toString()).into(mImageView);

                    Toast.makeText(getApplicationContext(), "upload file", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed upload file", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
