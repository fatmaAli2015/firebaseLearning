package com.example.fatmaali.firebaselearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class FirebaseRetrieveRealtimeData extends AppCompatActivity {
    private TextView txtFireBase;
    private Firebase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_retrieve_realtime_data);
        txtFireBase = (TextView) findViewById(R.id.txtFirebaes);
        // mFirebase = new Firebase("https://fir-79b4d.firebaseio.com/Name");
        mFirebase = new Firebase("https://fir-79b4d.firebaseio.com/");
        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                txtFireBase.setText(value);
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String name = map.get("Name");
                String age = map.get("Age");
                Log.v("E_VALUE", "Name : " + name);
                Log.v("E_VALUE", "Age : " + age);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
