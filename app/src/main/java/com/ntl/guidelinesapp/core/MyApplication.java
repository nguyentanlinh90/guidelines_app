package com.ntl.guidelinesapp.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.broadcast_receiver.ListenerNetworkBroadcastReceiver;
import com.ntl.guidelinesapp.modules.sharepreference.DataLocalManager;

public class MyApplication extends Application {
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final String CHANNEL_ID_2 = "CHANNEL_ID_2";
    public static final String CHANNEL_ID_3 = "CHANNEL_ID_3";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        DataLocalManager.init(getApplicationContext());
//        registerConnectivity();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            //config channel 1
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, getString(R.string.channel_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getString(R.string.channel_des));
            //lay am thanh default notification trong device
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            channel.setSound(uri, audioAttributes);
            channel.setSound(null, null);

            //config channel 2
            @SuppressLint("WrongConstant") NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, getString(R.string.channel_name_2), NotificationManager.IMPORTANCE_MAX);
            channel2.setDescription(getString(R.string.channel_des_2));
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);
            channel2.setSound(sound, audioAttributes);
//            channel2.setShowBadge(true);

            //config channel 3
            NotificationChannel channel3 = new NotificationChannel(CHANNEL_ID_3, getString(R.string.channel_name_3), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getString(R.string.channel_des_3));

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
                manager.createNotificationChannel(channel2);
                manager.createNotificationChannel(channel3);
            }
        }
    }

    //use for android 7 (api 24) or higher
    private void registerConnectivity() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new ListenerNetworkBroadcastReceiver(), intentFilter);
    }

}

