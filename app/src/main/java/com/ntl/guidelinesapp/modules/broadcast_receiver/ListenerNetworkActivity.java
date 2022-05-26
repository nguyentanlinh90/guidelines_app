package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.ConnectionStateMonitor;

public class ListenerNetworkActivity extends AppCompatActivity {
    private ListenerNetworkBroadcastReceiver listenerNetworkBroadcastReceiver;
    private ConnectionStateMonitor connectionStateMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_network);

        //1 way
        listenerNetworkBroadcastReceiver = new ListenerNetworkBroadcastReceiver();

        //2 way
        connectionStateMonitor = new ConnectionStateMonitor(this);
        connectionStateMonitor.enable(this);

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