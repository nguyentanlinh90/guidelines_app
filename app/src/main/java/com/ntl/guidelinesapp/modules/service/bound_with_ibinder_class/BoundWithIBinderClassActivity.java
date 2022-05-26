package com.ntl.guidelinesapp.modules.service.bound_with_ibinder_class;

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

public class BoundWithIBinderClassActivity extends AppCompatActivity {
    private Button btPlay, btStop;
    private MusicBoundIBinderService musicBoundService;
    private boolean isServiceConnected;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBoundIBinderService.MyBinder myBinder = (MusicBoundIBinderService.MyBinder) service;
            musicBoundService = myBinder.getMusicBoundService();
            musicBoundService.playMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        AppUtils.setTitleBar(this, BoundWithIBinderClassActivity.class);

        btPlay = findViewById(R.id.bt_play);
        btStop = findViewById(R.id.bt_stop);

        btPlay.setOnClickListener(v -> startPlayMusic());

        btStop.setOnClickListener(v -> startStopMusic());
    }

    private void startPlayMusic() {
        Intent intent = new Intent(this, MusicBoundIBinderService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void startStopMusic() {
        if (isServiceConnected) {
            unbindService(connection);
            isServiceConnected = false;
        }
    }
}