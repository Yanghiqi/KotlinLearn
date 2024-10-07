package com.example.kotlinlearn.RemoteView

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinlearn.Common.PermissionUtils
import com.example.kotlinlearn.R


class RemoteViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionUtils.getInstance().checkPermission(this)
        setContentView(R.layout.activity_remote_view)
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            NotificationUtil.showCustomNotification(this);
        }
    }
}
