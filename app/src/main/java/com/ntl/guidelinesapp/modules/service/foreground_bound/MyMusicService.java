package com.ntl.guidelinesapp.modules.service.foreground_bound;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;

public class MyMusicService extends Service {
    private final String TAG = MyMusicService.class.getSimpleName();

    private MediaPlayer mediaPlayer;

    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        MyMusicService getMyMusicService() {
            return MyMusicService.this;
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        sendNotification();
        return START_NOT_STICKY;
    }

    private void sendNotification() {
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setContentTitle("Dragon Z")
                .setContentTitle("Test Music Song")
                .build();
        startForeground(1, notification);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
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
