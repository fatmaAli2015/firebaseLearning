package com.example.fatmaali.firebaselearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class AddingMultipleObjectsToDatabase extends AppCompatActivity {
    private Button btnAddValue;
    private EditText mKey, mValue;
    private Firebase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_multiple_objects_to_database);

        mFirebase = new Firebase("https://fir-79b4d.firebaseio.com/");

        mKey = (EditText) findViewById(R.id.edt_Key);
        mValue = (EditText) findViewById(R.id.edt_Value);
        btnAddValue = (Button) findViewById(R.id.btn_AddFirebase);

        btnAddValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Key = mKey.getText().toString();
                String Value = mValue.getText().toString();

                Firebase mRefChild = mFirebase.child(Key);
                mRefChild.push().setValue(Value);
            }
        });

    }
}
