package com.example.owenkeith.alarmtest;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder mBuilder;
    private PendingIntent pendingIntent;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        findViewById(R.id.button_startAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();

            }
        });
    }


    public void start() {

        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
        scheduleNotification(getNotification("Notification Alert, Click Me!"), 15000);
    }

    private void scheduleNotification(Notification notification, int delay){
        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, i);
        i++;
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String s) {
        mBuilder= new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_face_black_24dp);
        mBuilder.setContentTitle(s);
        mBuilder.setContentText("Hi, This is Android Notification Detail!");
        return mBuilder.build();
    }


}
