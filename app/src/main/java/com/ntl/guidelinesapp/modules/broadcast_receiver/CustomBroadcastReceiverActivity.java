package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;

import java.util.Date;

public class CustomBroadcastReceiverActivity extends AppCompatActivity {
    private String MY_BROADCAST_ACTION = "com.ntl.MY_ACTION";
    private String MY_KEY = "my_key";

    private Button btSendBroadcast;
    private TextView tvReceiver;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_BROADCAST_ACTION.equals(intent.getAction())) {
                String strData = intent.getStringExtra(MY_KEY);
                tvReceiver.setText(strData);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast_receiver);

        btSendBroadcast = findViewById(R.id.bt_send_broadcast);
        tvReceiver = findViewById(R.id.tv_receiver);

        btSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcast();
            }
        });
    }

    private void sendCustomBroadcast() {
        Intent intent = new Intent(MY_BROADCAST_ACTION);
        intent.putExtra(MY_KEY, "This is Linh channel: " + new Date().getTime());
        sendBroadcast(intent);

        /*
            todo: receiver in another app with "MY_BROADCAST_ACTION"
            another app: https://github.com/nguyentanlinh90/guidelines_app_receiver
            NOTE: on another app need "unregisterReceiver" in onDestroy(), NOT onStop()
         */
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(MY_BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}