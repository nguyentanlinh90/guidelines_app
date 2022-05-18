package com.ntl.guidelinesapp.modules.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("DetailActivity");
    }
}