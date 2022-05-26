package com.ntl.guidelinesapp.modules.service.bound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ntl.guidelinesapp.R;

public class MusicBoundService extends Service {
    private final String TAG = MusicBoundService.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        MusicBoundService getMusicBoundService() {
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myBinder;
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

    public void playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.mp3_test);
        }
        mediaPlayer.start();
    }
}
