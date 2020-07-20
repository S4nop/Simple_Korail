package com.example.korailauto;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Notificator {
    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    PendingIntent intent;
    Context supCon;

    public Notificator(Context supContext){
        supCon = supContext;
        intent = PendingIntent.getActivity(supContext, 0, new Intent(supContext.getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

    }

    public void showNotification(String title, String body, String ticker){
        Notification.Builder builder;
        notificationManager = (NotificationManager)supCon.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNoficiationChannel();
            builder = new Notification.Builder(supCon, "train");
        }
        else builder = new Notification.Builder(supCon);

        builder = builder
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(body)
                .setTicker(ticker)
                .setAutoCancel(true)
                .setContentIntent(intent);


        notificationManager.notify(123, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setNoficiationChannel(){
        notificationChannel = new NotificationChannel("train", "ReserveAvailable", NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription("Notification for your train reservable status");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
