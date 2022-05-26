package com.ntl.guidelinesapp.modules.service.bound_with_message;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.service.bound_with_ibinder_class.MusicBoundIBinderService;

public class BoundWithMessageActivity extends AppCompatActivity {
    private Button btPlay, btStop;

    private Messenger messenger;
    private boolean isServiceConnected;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            isServiceConnected = true;

            Message message = Message.obtain(null, MusicBoundMessageService.MSG_PLAY_MUSIC, 0, 0);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        AppUtils.setTitleBar(this, BoundWithMessageActivity.class);

        btPlay = findViewById(R.id.bt_play);
        btStop = findViewById(R.id.bt_stop);

        btPlay.setOnClickListener(v -> startPlayMusic());

        btStop.setOnClickListener(v -> startStopMusic());
    }

    private void startPlayMusic() {
        Intent intent = new Intent(this, MusicBoundMessageService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void startStopMusic() {
        if (isServiceConnected) {
            unbindService(connection);
            isServiceConnected = false;
        }
    }
}