package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntl.guidelinesapp.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        getSupportActionBar().setTitle("BroadcastReceiverActivity");

        findViewById(R.id.bt_listener_network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadcastReceiverActivity.this, ListenerNetworkActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_custom_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadcastReceiverActivity.this, CustomBroadcastReceiverActivity.class);
                startActivity(intent);
            }
        });
    }
}