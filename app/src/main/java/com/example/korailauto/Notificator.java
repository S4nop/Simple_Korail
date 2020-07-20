package com.example.korailauto;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Notificator {
    NotificationManager notificationManager;
    PendingIntent intent;
    Context supCon;

    public Notificator(Context supContext){
        supCon = supContext;
        intent = PendingIntent.getActivity(supContext, 0, new Intent(supContext.getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void showNotification(String title, String body, String ticker){
        Notification.Builder builder = new Notification.Builder(supCon)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(body)
                .setTicker(ticker)
                .setAutoCancel(true)
                .setContentIntent(intent);

        notificationManager = (NotificationManager)supCon.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
