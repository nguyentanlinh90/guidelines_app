package com.ntl.guidelinesapp.modules.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.firebase.fcm.FCMActivity;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        getSupportActionBar().setTitle("FirebaseActivity");

        findViewById(R.id.bt_fcm).setOnClickListener(v->{
            Intent intent = new Intent(this, FCMActivity.class);
            startActivity(intent);
        });
    }
}