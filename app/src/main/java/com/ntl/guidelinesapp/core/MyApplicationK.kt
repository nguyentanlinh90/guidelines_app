package com.ntl.guidelinesapp.core

import com.ntl.guidelinesapp.modules.sharepreference.DataLocalManager
import android.os.Build
import android.media.AudioAttributes
import android.app.NotificationChannel
import com.ntl.guidelinesapp.core.MyApplicationK
import com.ntl.guidelinesapp.R
import android.app.NotificationManager
import android.media.RingtoneManager
import android.annotation.SuppressLint
import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import com.ntl.guidelinesapp.modules.broadcast_receiver.ListenerNetworkBroadcastReceiver

class MyApplicationK : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        DataLocalManager.init(applicationContext)
        //        registerConnectivity();
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()

            //config channel 1
            val channel = NotificationChannel(CHANNEL_ID, getString(R.string.channel_name), NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = getString(R.string.channel_des)
            //lay am thanh default notification trong device
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            //            channel.setSound(uri, audioAttributes);
            channel.setSound(null, null)

            //config channel 2
            @SuppressLint("WrongConstant") val channel2 = NotificationChannel(CHANNEL_ID_2, getString(R.string.channel_name_2), NotificationManager.IMPORTANCE_MAX)
            channel2.description = getString(R.string.channel_des_2)
            val sound = Uri.parse("android.resource://" + packageName + "/" + R.raw.snezee)
            channel2.setSound(sound, audioAttributes)

            //config channel 3
            val channel3 = NotificationChannel(CHANNEL_ID_3, getString(R.string.channel_name_3), NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = getString(R.string.channel_des_3)
            val manager = getSystemService<NotificationManager>(NotificationManager::class.java)
            if (manager != null) {
                manager.createNotificationChannel(channel)
                manager.createNotificationChannel(channel2)
                manager.createNotificationChannel(channel3)
            }
        }
    }

    //use for android 7 (api 24) or higher
    private fun registerConnectivity() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(ListenerNetworkBroadcastReceiver(), intentFilter)
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
        const val CHANNEL_ID_2 = "CHANNEL_ID_2"
        const val CHANNEL_ID_3 = "CHANNEL_ID_3"
    }
}