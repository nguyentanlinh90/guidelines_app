package com.ntl.guidelinesapp.modules.firebase.fcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.R;

public class FCMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmactivity);

        getSupportActionBar().setTitle("FCMActivity");
    }
}