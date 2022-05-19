package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.ntl.guidelinesapp.R;

public class ListenerNetworkActivity extends AppCompatActivity {
    private ListenerNetworkBroadcastReceiver listenerNetworkBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_network);

        listenerNetworkBroadcastReceiver = new ListenerNetworkBroadcastReceiver();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // listener network connect
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(listenerNetworkBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(listenerNetworkBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // still continue run when app on background
        // unregisterReceiver(myBroadcastReceiver);
    }
}