package com.ntl.guidelinesapp.modules.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra(CustomBroadcastReceiverActivity.MY_KEY);
        Toast.makeText(context, "MyBroadCast " + text, Toast.LENGTH_SHORT).show();
    }
}
