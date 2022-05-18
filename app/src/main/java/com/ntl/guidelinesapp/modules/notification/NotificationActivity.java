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

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;

import java.util.Date;

public class NotificationActivity extends AppCompatActivity {

    private static final String CONTENT_PUSH_NOTIFICATION = "Theo đó, khi biết \"tiểu tam\" qua lại với chồng mình, người vợ không đánh ghen ầm ĩ, cũng không \"bóc phốt\" hay chửi bới qua lại trên mạng xã hội mà lựa chọn cách đánh ghen của riêng mình. \n" +
            "\n" +
            "Người vợ này sau khi biết \"tiểu tam\" đến vui chơi tại một quán bar đã nhanh chóng liên hệ với quán bar này, đặt lịch chiếu lời \"chúc mừng tiểu tam\" trên màn hình led lớn. \"Chị H. vợ anh T. chúc em T. rằm tháng 4 vui vẻ và hạnh phúc\", nội dung lời chúc mà người vợ chuẩn bị.\n" +
            "\n" +
            "Kèm theo lời chúc này, người vợ còn chuẩn bị hình ảnh của \"tiểu tam\" để chiếu lên màn hình led. Tất cả các vị khách có trong quán bar đều có thể chứng kiến, nhiều người đã nhanh tay chụp lại hình ảnh này và chia sẻ trên mạng xã hội. ";

    private static final int NOTIFICATION_ID = 113;

    private Button btShowNotification, btShowNotification2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btShowNotification = findViewById(R.id.bt_show_notification);
        btShowNotification2 = findViewById(R.id.bt_show_notification_2);
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_notifications_24);
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Notification title 2")
                .setContentText("Notification content 2")
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setLargeIcon(bitmap)
                //hien thi hinh to o content notification
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setColor(getResources().getColor(R.color.purple_500))
                .setSound(sound)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
    }
}