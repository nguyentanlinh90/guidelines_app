package com.ntl.guidelinesapp.modules.service.foreground_bound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

public class ForegroundAndBoundActivity extends AppCompatActivity {
    private Button btPlay, btStopForeground, btStopBound;
    private MyMusicService myMusicService;
    private boolean isServiceConnected;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyMusicService.MyBinder myBinder = (MyMusicService.MyBinder) service;
            myMusicService = myBinder.getMyMusicService();
            myMusicService.playMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myMusicService = null;
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_and_bound);
        AppUtils.setTitleBar(this, ForegroundAndBoundActivity.class);

        btPlay = findViewById(R.id.bt_play);
        btStopForeground = findViewById(R.id.bt_stop_foreground);
        btStopBound = findViewById(R.id.bt_stop_bound);

        btPlay.setOnClickListener(v -> startPlayMusic());

        btStopForeground.setOnClickListener(v -> startStopForegroundMusic());
        btStopBound.setOnClickListener(v -> startStopBoundMusic());
    }

    private void startPlayMusic() {
        Intent intent = new Intent(this, MyMusicService.class);
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void startStopForegroundMusic() {
        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);
    }

    private void startStopBoundMusic() {
        if (isServiceConnected) {
            unbindService(connection);
            isServiceConnected = false;
        }
    }
}