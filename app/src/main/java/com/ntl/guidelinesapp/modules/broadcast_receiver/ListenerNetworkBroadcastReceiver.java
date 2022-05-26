package com.ntl.guidelinesapp.modules.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.ntl.guidelinesapp.AppUtils;

public class ListenerNetworkBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (AppUtils.isNetworkAvailable(context)) {
                Toast.makeText(context, "Internet Connected 1", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Internet Disconnected 1", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
