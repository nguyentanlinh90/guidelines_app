package com.ntl.guidelinesapp.modules.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ntl.guidelinesapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomBroadcastReceiverActivity extends AppCompatActivity {
    private String MY_BROADCAST_ACTION = "com.ntl.MY_ACTION";
    private String MY_BROADCAST_OBJECT_ACTION = "com.ntl.MY_OBJECT_ACTION";
    public static final String MY_KEY = "my_key";
    private String MY_KEY_LIST_OBJECT = "my_key_list_object";

    private Button btSendBroadcast, btSendBroadcastWithObject, btSendBroadcastExplicit, btSendBroadcastTo1Project, btSendBroadcastToManyProject;
    private TextView tvReceiver, tvReceiverObject;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_BROADCAST_ACTION.equals(intent.getAction())) {
                String strData = intent.getStringExtra(MY_KEY);
                tvReceiver.setText(strData);
            }

            if (MY_BROADCAST_OBJECT_ACTION.equals(intent.getAction())) {
                Gson gson = new Gson();

                //todo case 1: get OBJECT
                String strUser = intent.getStringExtra(MY_KEY);
                User user = gson.fromJson(strUser, User.class);

                //todo case 2: get LIST OBJECT with logic below
                String strJson = intent.getStringExtra(MY_KEY_LIST_OBJECT);
                List<User> list = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(strJson);
                    JSONObject jsonObject;
                    User user1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        user1 = gson.fromJson(jsonObject.toString(), User.class);
                        list.add(user1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvReceiverObject.setText(user.getName() + " : " + user.getAddress() + "\n" +
                        "size list = " + list.size());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast_receiver);

        getSupportActionBar().setTitle("CustomBroadcastReceiverActivity");

        btSendBroadcast = findViewById(R.id.bt_send_broadcast);
        btSendBroadcastWithObject = findViewById(R.id.bt_send_broadcast_object);
        btSendBroadcastExplicit = findViewById(R.id.bt_send_broadcast_explicit);
        btSendBroadcastTo1Project = findViewById(R.id.bt_send_broadcast_to_1_pj);
        btSendBroadcastToManyProject = findViewById(R.id.bt_send_broadcast_to_many_pj);
        tvReceiver = findViewById(R.id.tv_receiver);
        tvReceiverObject = findViewById(R.id.tv_receiver_object);

        btSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcast();
            }
        });

        btSendBroadcastWithObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcastWithObject();
            }
        });

        btSendBroadcastExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcastExplicit();
            }
        });

        btSendBroadcastTo1Project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcastTo1Project();
            }
        });

        btSendBroadcastToManyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcastToManyProject();
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

    private void sendCustomBroadcastWithObject() {
        Intent intent = new Intent(MY_BROADCAST_OBJECT_ACTION);
        User user = new User("Linhnt", "Ho Chi Minh");
        User user2 = new User("Tinhnh", "Lam Dong");

        Gson gson = new Gson();

        //todo case 1: put OBJECT
        String strUser = gson.toJson(user);
        intent.putExtra(MY_KEY, strUser);

        //todo case 2: put LIST OBJECT with logic below
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        JsonArray jsonElements = gson.toJsonTree(list).getAsJsonArray();
        String strJson = jsonElements.toString();
        intent.putExtra(MY_KEY_LIST_OBJECT, strJson);

        sendBroadcast(intent);

        /*
            todo: receiver in another app with "MY_BROADCAST_OBJECT_ACTION"
            another app: https://github.com/nguyentanlinh90/guidelines_app_receiver
            NOTE: on another app need "unregisterReceiver" in onDestroy(), NOT onStop()
         */
    }

    private void sendCustomBroadcastExplicit() {
        Intent intent = new Intent(this, MyBroadCast.class);
        intent.putExtra(MY_KEY, "This is BroadcastExplicit");
        sendBroadcast(intent);
    }

    private void sendCustomBroadcastTo1Project() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(
                "com.ntl.guidelinesappreceiver", "com.ntl.guidelinesappreceiver.MyBroadcastReceiver");
        intent.setComponent(componentName);
        intent.putExtra(MY_KEY, "This is BroadcastExplicit");
        sendBroadcast(intent);

    }

    private void sendCustomBroadcastToManyProject() {
        /* todo in another app need to add below code receiver to manifest
        <receiver
            android:name=".MyBroadcastReceiver1"
            android:exported="true">
            <intent-filter>
                <action android:name="com.ntl.MY_ACTION" />
            </intent-filter>
        </receiver>
        * */

        Intent intent = new Intent(MY_BROADCAST_ACTION);
        intent.putExtra(MY_KEY, "This is BroadcastExplicit");
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryBroadcastReceivers(intent, 0);
        for (ResolveInfo info : resolveInfoList) {
            ComponentName componentName = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
            intent.setComponent(componentName);
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(MY_BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

        IntentFilter intentFilterObject = new IntentFilter(MY_BROADCAST_OBJECT_ACTION);
        registerReceiver(broadcastReceiver, intentFilterObject);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}