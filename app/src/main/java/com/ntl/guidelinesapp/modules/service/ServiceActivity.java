package com.ntl.guidelinesapp.modules.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.service.unbound.ForegroundServiceActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        AppUtils.setTitleBar(this, ServiceActivity.class);

        findViewById(R.id.bt_foreground_service).setOnClickListener(v -> AppUtils.gotoScreen(ServiceActivity.this, ForegroundServiceActivity.class));

    }
}