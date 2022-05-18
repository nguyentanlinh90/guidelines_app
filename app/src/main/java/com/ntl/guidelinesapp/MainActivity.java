package com.ntl.guidelinesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntl.guidelinesapp.modules.notification.NotificationActivity;
import com.ntl.guidelinesapp.modules.retrofit.RetrofitActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btNotification, btRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNotification = findViewById(R.id.bt_notification);
        btRetrofit = findViewById(R.id.bt_retrofit);

        btNotification.setOnClickListener(this);
        btRetrofit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notification:
                gotoScreen(NotificationActivity.class);
                break;
            case R.id.bt_retrofit:
                gotoScreen(RetrofitActivity.class);
                break;
        }
    }

    private void gotoScreen(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}