package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.ntl.guidelinesapp.R;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private MyBroadcastReceiver myBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // listener network connect
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // not continue run when app on background
        unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // still continue run when app on background
//        unregisterReceiver(myBroadcastReceiver);
    }
}