package com.example.fatmaali.firebaselearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    private Button mSendData;
    private Firebase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mFirebase= new Firebase("https://fir-79b4d.firebaseio.com/");

        mSendData = (Button)findViewById(R.id.btn_firebase);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase mRefChild = mFirebase.child("Name");
                mRefChild.setValue("firstData");
            }
        });
    }
}
