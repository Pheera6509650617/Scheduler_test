package com.example.scheduler_test;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class SampleSchedulingService extends IntentService {

    public SampleSchedulingService() {
        super("SchedulingService");
    }

    public static final int NOTIFICATION_ID = 1;
    String CHANNEL_ID = "1" ;

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification(getString(R.string.msg));
    }

    private void sendNotification(String msg) {
        createNotificationChannel();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.header))
                .setContentText(getString(R.string.msg))
                .setSmallIcon(R.drawable.baseline_arrow_forward_24) // ไอคอนที่ต้องกำหนดใน res/drawable/
                .build();

        // เรียก startForeground()
        startForeground(NOTIFICATION_ID, notification);
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.header);
            String description = getString(R.string.msg);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.header))
                .setContentText(getString(R.string.msg))
                .setSmallIcon(R.drawable.baseline_arrow_forward_24) // ไอคอนที่ต้องกำหนดใน res/drawable/
                .build();

        // เรียก startForeground()
        startForeground(NOTIFICATION_ID, notification);
    }
}
