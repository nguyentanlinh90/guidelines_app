package com.ntl.guidelinesapp.modules.service.type;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ntl.guidelinesapp.Constants;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;
import com.ntl.guidelinesapp.modules.service.Song;

public class MyMusicService extends Service {
    private static final int ACTION_PLAY = 1;
    private static final int ACTION_RESUME = 2;
    private static final int ACTION_CLOSE = 3;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Song song = (Song) bundle.get(Constants.SONG_OBJECT);
            playMusic(song);
            sendNotification(song);
        }

        return START_NOT_STICKY;
    }

    private void playMusic(Song song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        mediaPlayer.start();
    }

    private void sendNotification(Song song) {

        Intent intent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getResource());

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_music_notifycation);
        remoteViews.setTextViewText(R.id.tv_title, song.getTitle());
        remoteViews.setTextViewText(R.id.tv_single, song.getSingle());
//        remoteViews.setImageViewBitmap(R.id.iv_song, bitmap);
        remoteViews.setImageViewResource(R.id.iv_song, song.getImage());
        remoteViews.setImageViewResource(R.id.iv_action_pause_play, R.drawable.ic_baseline_pause_24);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setLargeIcon(bitmap)
                .setCustomContentView(remoteViews)
                .setContentIntent(pendingIntent)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    
}
