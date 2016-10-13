package com.example.owenkeith.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    NotificationCompat.Builder mBuilder;

    @Override
    public void onReceive(Context context, Intent intent) {

        mBuilder= new NotificationCompat.Builder(context);

        // For our recurring task, we'll just display a message
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        displayNotification("notify", context);

    }
    private void displayNotification(String s, Context context) {


        Notification notify = mBuilder.build();
        mBuilder.setSmallIcon(R.drawable.ic_face_black_24dp);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(9999,notify);
    }
}