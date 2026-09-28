package com.mohammedalhzmi.masrofmanager.cloud;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.C2530R;
import com.example.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MasrofMessagingService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/cloud/MasrofMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "<init>", "()V", "onNewToken", "", "token", "", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class MasrofMessagingService extends FirebaseMessagingService {
    public static final int $stable = 8;

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage message) {
        String title;
        String body;
        Intrinsics.checkNotNullParameter(message, "message");
        RemoteMessage.Notification notification = message.getNotification();
        if ((notification == null || (title = notification.getTitle()) == null) && (title = message.getData().get(LinkHeader.Parameters.Title)) == null) {
            title = "تنبيه نظام المالية";
        }
        RemoteMessage.Notification notification2 = message.getNotification();
        if ((notification2 == null || (body = notification2.getBody()) == null) && (body = message.getData().get("body")) == null) {
            body = "";
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("masrof_admin", "تنبيهات نظام المالية", 4));
        }
        MasrofMessagingService masrofMessagingService = this;
        Notification notificationBuild = new NotificationCompat.Builder(masrofMessagingService, "masrof_admin").setSmallIcon(C2530R.drawable.official_emblem).setContentTitle(title).setContentText(body).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(masrofMessagingService, 0, new Intent(masrofMessagingService, (Class<?>) MainActivity.class), 201326592)).setPriority(1).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        notificationManager.notify((int) System.currentTimeMillis(), notificationBuild);
    }
}
