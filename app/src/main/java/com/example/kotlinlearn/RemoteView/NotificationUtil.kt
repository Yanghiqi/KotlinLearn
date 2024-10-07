package com.example.kotlinlearn.RemoteView

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.kotlinlearn.R

object NotificationUtil {
    private const val CHANNEL_ID = "my_channel_id"
    private const val CHANNEL_NAME = "My Channel"

    fun showCustomNotification(context: Context) {
        // 创建通知渠道（仅适用于 Android O 及以上版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        // 创建 RemoteView
        val remoteViews = RemoteViews(context.packageName, R.layout.notification_layout).apply {
            setTextViewText(R.id.notification_title, "自定义通知标题")
            setTextViewText(R.id.notification_content, "这是自定义通知内容")
        }

        // 设置点击通知的行为
        val intent = Intent(context, RemoteViewActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomContentView(remoteViews)
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }
}
