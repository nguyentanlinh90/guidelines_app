package com.ntl.guidelinesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntl.guidelinesapp.modules.notification.NotificationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNotification = findViewById(R.id.bt_notification);

        btNotification.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notification:
                gotoScreen(NotificationActivity.class);
                break;
        }
    }

    private void gotoScreen(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}