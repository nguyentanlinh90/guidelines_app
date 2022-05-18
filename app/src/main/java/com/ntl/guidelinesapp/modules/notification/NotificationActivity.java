package com.ntl.guidelinesapp.modules.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationActivity extends AppCompatActivity {

    private static final String CONTENT_PUSH_NOTIFICATION = "Theo đó, khi biết \"tiểu tam\" qua lại với chồng mình, người vợ không đánh ghen ầm ĩ, cũng không \"bóc phốt\" hay chửi bới qua lại trên mạng xã hội mà lựa chọn cách đánh ghen của riêng mình. \n" +
            "\n" +
            "Người vợ này sau khi biết \"tiểu tam\" đến vui chơi tại một quán bar đã nhanh chóng liên hệ với quán bar này, đặt lịch chiếu lời \"chúc mừng tiểu tam\" trên màn hình led lớn. \"Chị H. vợ anh T. chúc em T. rằm tháng 4 vui vẻ và hạnh phúc\", nội dung lời chúc mà người vợ chuẩn bị.\n" +
            "\n" +
            "Kèm theo lời chúc này, người vợ còn chuẩn bị hình ảnh của \"tiểu tam\" để chiếu lên màn hình led. Tất cả các vị khách có trong quán bar đều có thể chứng kiến, nhiều người đã nhanh tay chụp lại hình ảnh này và chia sẻ trên mạng xã hội. ";

    private static final int NOTIFICATION_ID = 113;

    private Button btShowNotification, btShowNotification2, btShowNotificationCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btShowNotification = findViewById(R.id.bt_show_notification);
        btShowNotification2 = findViewById(R.id.bt_show_notification_2);
        btShowNotificationCustom = findViewById(R.id.bt_show_notification_custom);

        btShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        btShowNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification2();
            }
        });
        btShowNotificationCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationCustom();
            }
        });
    }

    private void sendNotification() {
        //lay am thanh default notification trong device
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //tat ca cac hinh deu khac dinh danh xml thi moi hien thi o notification large icon
        //ic_baseline_notifications_24 => dinh dang xml => se ko hien thi
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_notifications_24);
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Notification title")
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setColor(getResources().getColor(R.color.purple_500))
                .setSound(uri)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            //neu cung id thi cai sau se de len cai truoc
            manager.notify(NOTIFICATION_ID, notification);
        }
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }

    private void sendNotification2() {
        //lay am thanh tu benh ngoai, file .mp3 dat vao drawable/raw
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_image);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Notification title 2")
                .setContentText("Notification content 2")
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setLargeIcon(bitmap)
                //hien thi hinh to o content notification
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setColor(getResources().getColor(R.color.purple_500))
                .setSound(sound)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
    }

    private void sendNotificationCustom() {
        //lay am thanh tu benh ngoai, file .mp3 dat vao drawable/raw
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);

        //nen resize bitmap de tranh loi exception neu size anh qua lon
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_image);

        //for default
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.tv_title, "Title Notification");
        remoteViews.setTextViewText(R.id.tv_info, "Info Notification");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String date = simpleDateFormat.format(new Date());
        remoteViews.setTextViewText(R.id.tv_time, date);
        remoteViews.setImageViewBitmap(R.id.iv_notification, bitmap);

        //for expand
        RemoteViews remoteViewsExpand = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expand);
        remoteViewsExpand.setTextViewText(R.id.tv_title, "Title Notification");
        remoteViewsExpand.setTextViewText(R.id.tv_info, "Info Notification");
        remoteViewsExpand.setImageViewBitmap(R.id.img_notification, bitmap);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setSound(sound)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsExpand)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
    }
}