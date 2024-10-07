package com.example.kotlinlearn.RemoteView

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.kotlinlearn.R

const val channelId = "channelId";

class RemoteViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_remote_view)
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            showDefaultNotification(this)
        }


    }
}

private fun showDefaultNotification(context: Context) {
    NotificationUtil.showCustomNotification(context);
}