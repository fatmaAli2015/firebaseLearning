package com.example.fatmaali.firebaselearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUIFirebaseListAdapters extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_uifirebase_list_adapters);

        mListView = (ListView) findViewById(R.id.listChangeRunTime);

        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-79b4d.firebaseio.com/Users");
        FirebaseListAdapter<String> mFirebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                mDatabaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };
        mListView.setAdapter(mFirebaseListAdapter);
    }
}
