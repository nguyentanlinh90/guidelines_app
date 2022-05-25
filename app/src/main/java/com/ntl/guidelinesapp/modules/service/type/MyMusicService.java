package com.ntl.guidelinesapp.modules.service.type;

import static com.ntl.guidelinesapp.Constants.ACTION_MUSIC;
import static com.ntl.guidelinesapp.Constants.ACTION_SERVICE_MUSIC;
import static com.ntl.guidelinesapp.Constants.MUSIC_IS_PLAYING;
import static com.ntl.guidelinesapp.Constants.SEND_DATA_ACTION_MUSIC_TO_MAIN;
import static com.ntl.guidelinesapp.Constants.SONG_OBJECT;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.ntl.guidelinesapp.Constants;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;
import com.ntl.guidelinesapp.modules.service.Song;

public class MyMusicService extends Service {
    public static final int ACTION_PAUSE = 1;
    public static final int ACTION_RESUME = 2;
    public static final int ACTION_CLOSE = 3;
    public static final int ACTION_PLAY = 4;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private Song mSong;

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
            if (song != null) {
                mSong = song;
                playMusic(song);
                sendNotification(song);
            }
        }
        int actionMusic = intent.getIntExtra(ACTION_SERVICE_MUSIC, 0);
        handleMusic(actionMusic);

        return START_NOT_STICKY;
    }

    private void playMusic(Song song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        mediaPlayer.start();
        isPlaying = true;
        handleSendActionToMain(ACTION_PLAY);
    }

    private void sendNotification(Song song) {

        Intent intent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getResource());

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_music_notification);
        remoteViews.setTextViewText(R.id.tv_title, song.getTitle());
        remoteViews.setTextViewText(R.id.tv_single, song.getSingle());
//        remoteViews.setImageViewBitmap(R.id.iv_song, bitmap);
        remoteViews.setImageViewResource(R.id.iv_song, song.getImage());
        remoteViews.setImageViewResource(R.id.iv_action_pause_play,
                isPlaying ? R.drawable.ic_baseline_pause_24 : R.drawable.ic_baseline_play_arrow_24);

        remoteViews.setOnClickPendingIntent(R.id.iv_action_pause_play,
                getPendingIntent(this, isPlaying ? ACTION_PAUSE : ACTION_RESUME));
        remoteViews.setOnClickPendingIntent(R.id.iv_action_close, getPendingIntent(this, ACTION_CLOSE));

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

    private void handleMusic(int action) {
        switch (action) {
            case ACTION_PAUSE:
                pauseMusic();
                break;
            case ACTION_RESUME:
                resumeMusic();
                break;
            case ACTION_CLOSE:
                stopSelf();
                handleSendActionToMain(ACTION_CLOSE);
                break;
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
            sendNotification(mSong);
            handleSendActionToMain(ACTION_PAUSE);
        }
    }

    private void resumeMusic() {
        if (mediaPlayer != null && !isPlaying) {
            mediaPlayer.start();
            isPlaying = true;
            sendNotification(mSong);
            handleSendActionToMain(ACTION_RESUME);
        }
    }

    private PendingIntent getPendingIntent(Context context, int action) {
        Intent intent = new Intent(this, MyMusicBroadcastReceiver.class);
        intent.putExtra(ACTION_MUSIC, action);
        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void handleSendActionToMain(int action) {
        Intent intent = new Intent(SEND_DATA_ACTION_MUSIC_TO_MAIN);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SONG_OBJECT, mSong);
        bundle.putBoolean(MUSIC_IS_PLAYING, isPlaying);
        bundle.putInt(ACTION_MUSIC, action);
        intent.putExtras(bundle);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
