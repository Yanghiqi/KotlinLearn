package com.example.kotlinlearn.RemoteView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.kotlinlearn.R;

public class NotificationUtil {

    public static void showCustomNotification(Context context) {
        // 创建通知渠道（仅适用于 Android O 及以上版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_channel_id", "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // 创建 RemoteView
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.notification_title, "自定义通知标题");
        remoteViews.setTextViewText(R.id.notification_content, "这是自定义通知内容");

        // 设置点击通知的行为
        Intent intent = new Intent(context, RemoteViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        Notification notification = new Notification.Builder(context, "my_channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}