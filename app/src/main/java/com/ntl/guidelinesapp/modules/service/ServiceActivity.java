package com.ntl.guidelinesapp.modules.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.service.bound_with_ibinder_class.BoundWithIBinderClassActivity;
import com.ntl.guidelinesapp.modules.service.bound_with_message.BoundWithMessageActivity;
import com.ntl.guidelinesapp.modules.service.foreground_bound.ForegroundAndBoundActivity;
import com.ntl.guidelinesapp.modules.service.unbound.ForegroundServiceActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        AppUtils.setTitleBar(this, ServiceActivity.class);

        findViewById(R.id.bt_foreground_service).setOnClickListener(v -> AppUtils.gotoScreen(ServiceActivity.this, ForegroundServiceActivity.class));
        findViewById(R.id.bt_bound_ibinder_service).setOnClickListener(v -> AppUtils.gotoScreen(ServiceActivity.this, BoundWithIBinderClassActivity.class));
        findViewById(R.id.bt_bound_message_service).setOnClickListener(v -> AppUtils.gotoScreen(ServiceActivity.this, BoundWithMessageActivity.class));
        findViewById(R.id.bt_foreground_bound_service).setOnClickListener(v -> AppUtils.gotoScreen(ServiceActivity.this, ForegroundAndBoundActivity.class));

    }
}