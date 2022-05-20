package com.ntl.guidelinesapp.modules.firebase.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG = MyFirebaseMessagingService.class.getName();
    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        /*
        todo Message String
            RemoteMessage.Notification notification = message.getNotification();
            if (notification == null) {
                return;
            }
            String strTitle = notification.getTitle();
            String strMessage = notification.getBody();
        */

        /*
         TODO: 21/05/2022 Data Message
            Sử dụng Postman để call API gửi Push Notification dạng Data Messages
            Method: POST
            Url API : https://fcm.googleapis.com/fcm/send
            Header:
            Authorization : key="SERVER API KEY"
            SERVER get from FCM
            https://console.firebase.google.com/u/0/project/guidelinesapp-a000d/settings/cloudmessaging/android:com.ntl.guidelinesapp
            Content-Type:application/json
            Body:
            {
                "data": {
                    "user_name": "value_1",
                     "description": "value_2"
                            ...
            },
                "to" : "token_firebase_of_device" // get on onNewToken
            }
        */

        Map<String, String> map = message.getData();
        String strTitle = map.get("user_name");
        String strMessage = map.get("description");

        sendNotification(strTitle, strMessage);
    }

    private void sendNotification(String strTitle, String strMessage) {
        Intent intent = new Intent(this, FCMActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_3)
                .setContentTitle(strTitle)
                .setContentText(strMessage)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, notification);
        }
    }

    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reInstalls the app
     * C) User clears app data
     */
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, token);
    }
}
