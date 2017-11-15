package com.example.fatmaali.firebaselearning;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Fatma Ali on 02/11/2017.
 */

public class FireApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /* previous version*/
      //  Firebase.setAndroidContext(this);

        /* old version*/
        if (!com.google.firebase.FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
