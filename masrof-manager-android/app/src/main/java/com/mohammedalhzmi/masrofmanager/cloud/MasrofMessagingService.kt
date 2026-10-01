package com.mohammedalhzmi.masrofmanager.cloud

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MasrofMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        // The next authenticated session persists this token in the user's device document.
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification?.title ?: message.data["title"] ?: "تنبيه نظام المالية"
        val body = message.notification?.body ?: message.data["body"].orEmpty()
        val channelId = "masrof_admin"
        val manager = getSystemService(NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) manager.createNotificationChannel(NotificationChannel(channelId, "تنبيهات نظام المالية", NotificationManager.IMPORTANCE_HIGH))
        val intent = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(com.example.R.drawable.official_emblem)
            .setContentTitle(title).setContentText(body).setAutoCancel(true)
            .setContentIntent(intent).setPriority(NotificationCompat.PRIORITY_HIGH).build()
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
