package com.ntl.guidelinesapp.modules.notification;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.Button;
import android.widget.RemoteViews;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.core.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationActivity extends BaseActivity {

    private static final String CONTENT_PUSH_NOTIFICATION = "Theo đó, khi biết \"tiểu tam\" qua lại với chồng mình, người vợ không đánh ghen ầm ĩ, cũng không \"bóc phốt\" hay chửi bới qua lại trên mạng xã hội mà lựa chọn cách đánh ghen của riêng mình. \n" +
            "\n" +
            "Người vợ này sau khi biết \"tiểu tam\" đến vui chơi tại một quán bar đã nhanh chóng liên hệ với quán bar này, đặt lịch chiếu lời \"chúc mừng tiểu tam\" trên màn hình led lớn. \"Chị H. vợ anh T. chúc em T. rằm tháng 4 vui vẻ và hạnh phúc\", nội dung lời chúc mà người vợ chuẩn bị.\n" +
            "\n" +
            "Kèm theo lời chúc này, người vợ còn chuẩn bị hình ảnh của \"tiểu tam\" để chiếu lên màn hình led. Tất cả các vị khách có trong quán bar đều có thể chứng kiến, nhiều người đã nhanh tay chụp lại hình ảnh này và chia sẻ trên mạng xã hội. ";

    private static final int NOTIFICATION_ID = 113;

    private Button btShowNotification, btShowNotification2, btShowNotificationCustom, btGoToListProduct, btShowNotificationMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setTitle("NotificationActivity");

        btShowNotification = findViewById(R.id.bt_show_notification);
        btShowNotification2 = findViewById(R.id.bt_show_notification_2);
        btShowNotificationCustom = findViewById(R.id.bt_show_notification_custom);
        btGoToListProduct = findViewById(R.id.bt_go_to_list_product);
        btShowNotificationMedia = findViewById(R.id.bt_send_notification_media);

        btShowNotification.setOnClickListener(v -> sendNotification());

        btShowNotification2.setOnClickListener(v -> sendNotification2());
        btShowNotificationCustom.setOnClickListener(v -> sendNotificationCustom());

        btGoToListProduct.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationActivity.this, ProductActivity.class);
            startActivity(intent);
        });

        btShowNotificationMedia.setOnClickListener(v -> {
            sendNotificationMedia();
        });
    }

    private void sendNotification() {
        // get ringtone default notification of device
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //all image should be different .xml
        //ic_baseline_notifications_24 is .xml file => not display
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
            //if the same id: notification after will override notification before
            manager.notify(NOTIFICATION_ID, notification);
        }
    }

    private void sendNotification2() {
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_400_600);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Notification title 2")
                .setContentText("Notification content 2")
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setLargeIcon(bitmap)
                //display big image in content notification
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setColor(getResources().getColor(R.color.purple_500))
                .setSound(sound)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getRandomNotificationId(), notification);
    }

    private void sendNotificationCustom() {

        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snezee);

        //should resize bitmap to avoid exception if size image to big
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_400_600);

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
//                .setContentIntent(pendingIntentRegular())
                .setContentIntent(pendingIntentSpecial())
                .setAutoCancel(true) // delete notification on notification bar after click
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getRandomNotificationId(), notification);
    }

    private PendingIntent pendingIntentRegular() {
        //will goto ResultActivity, if backpress will goto ProductActivity, continue backpress will go to NotificationActivity
        /*need insert
        android:parentActivityName=".modules.notification.ProductActivity"
        to Manifest*/
        Intent intent = new Intent(this, DetailActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    private PendingIntent pendingIntentSpecial() {
        //OPEN SEPARATE SCREEN, will show 2 task when click multitasking on device

        //will goto ResultActivity, if backpress will goto NotificationActivity, not back to ProductActivity

        /*need insert
            android:excludeFromRecents="true"
            android:launchMode="singleTask"
            android:taskAffinity=""
        to Manifest*/
        Intent intent = new Intent(this, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    private void sendNotificationMedia() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_400_600);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "tag");

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setSubText("Linhnt")
                .setContentTitle("Title of Song")
                .setContentText("Single of Song")
                .setLargeIcon(bitmap)
                .addAction(R.drawable.ic_baseline_skip_previous_24, "Pre", null)
                .addAction(R.drawable.ic_baseline_pause_24, "Pause", null)
                .addAction(R.drawable.ic_baseline_skip_previous_24, "Next", null)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setNumber(1)
                .setGroup("Group Test")
                .setGroupSummary(true)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                .setShowActionsInCompactView(1)
                        //todo Couldn't find a unique registered media button receiver in the given context.
                        //.setMediaSession(mediaSessionCompat.getSessionToken())
                )
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getRandomNotificationId(), notification);

    }
}