/*

    Created by Linh Nguyen 18/5/2022

*/

package com.ntl.guidelinesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntl.guidelinesapp.modules.broadcast_receiver.BroadcastReceiverActivity;
import com.ntl.guidelinesapp.modules.download_file.DownloadFileActivity;
import com.ntl.guidelinesapp.modules.notification.NotificationActivity;
import com.ntl.guidelinesapp.modules.retrofit.RetrofitActivity;
import com.ntl.guidelinesapp.modules.send_data_fragment_to_fragment.SendDataFragmentToFragmentActivity;
import com.ntl.guidelinesapp.modules.send_data_to_fragment.SendDataToFragmentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btRetrofit, btNotification, btDownloadFile, btBroadcastReceiver, btSendDataActivityToFragment, btSendDataFragmentToFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRetrofit = findViewById(R.id.bt_retrofit);
        btNotification = findViewById(R.id.bt_notification);
        btDownloadFile = findViewById(R.id.bt_download_file);
        btBroadcastReceiver = findViewById(R.id.bt_broadcast_receiver);
        btSendDataActivityToFragment = findViewById(R.id.bt_send_data_to_fragment);
        btSendDataFragmentToFragment = findViewById(R.id.bt_send_data_fragment_to_fragment);

        btNotification.setOnClickListener(this);
        btRetrofit.setOnClickListener(this);
        btDownloadFile.setOnClickListener(this);
        btBroadcastReceiver.setOnClickListener(this);
        btSendDataActivityToFragment.setOnClickListener(this);
        btSendDataFragmentToFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notification:
                gotoScreen(NotificationActivity.class);
                break;
            case R.id.bt_retrofit:
                gotoScreen(RetrofitActivity.class);
                break;
            case R.id.bt_download_file:
                gotoScreen(DownloadFileActivity.class);
                break;
            case R.id.bt_broadcast_receiver:
                gotoScreen(BroadcastReceiverActivity.class);
                break;
            case R.id.bt_send_data_to_fragment:
                gotoScreen(SendDataToFragmentActivity.class);
                break;

            case R.id.bt_send_data_fragment_to_fragment:
                gotoScreen(SendDataFragmentToFragmentActivity.class);
                break;

        }
    }

    private void gotoScreen(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}