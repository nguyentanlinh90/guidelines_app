package com.ntl.guidelinesapp.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.ntl.guidelinesapp.R;

public class MyApplication extends Application {
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final String CHANNEL_ID_2 = "CHANNEL_ID_2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            //config channel 1
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, getString(R.string.channel_name), NotificationManager.IMPORTANCE_MIN);
            channel.setDescription(getString(R.string.channel_des));
            //lay am thanh default notification trong device
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            channel.setSound(uri, audioAttributes);

            //config channel 2
            @SuppressLint("WrongConstant") NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, getString(R.string.channel_name_2), NotificationManager.IMPORTANCE_MAX);
            channel2.setDescription(getString(R.string.channel_des_2));
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);
            channel2.setSound(sound, audioAttributes);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
                manager.createNotificationChannel(channel2);
            }
        }
    }
}
