package com.ntl.guidelinesapp.modules.service.bound_with_message;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ntl.guidelinesapp.R;

public class MusicBoundMessageService extends Service {
    private final String TAG = MusicBoundMessageService.class.getSimpleName();
    public static final int MSG_PLAY_MUSIC = 1;

    public class MyHandler extends Handler {
        private Context applicationContext;

        MyHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PLAY_MUSIC:
                    playMusic();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger mMessenger;
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        mMessenger = new Messenger(new MyHandler(this));
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.mp3_test);
        }
        mediaPlayer.start();
    }
}
