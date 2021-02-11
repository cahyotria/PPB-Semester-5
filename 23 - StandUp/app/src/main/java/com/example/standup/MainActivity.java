package com.example.standup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static int NOTIFICATION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Ketika Android yang digunakan itu lebih dari Android OREO
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID
                    , "Alarm Notification",  NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Stand Up Alarm");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        ToggleButton alarmToggle = (ToggleButton)  findViewById(R.id.alarmToggle);

        Intent notifyIntent = new Intent(this,AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this,0 ,notifyIntent,
                PendingIntent.FLAG_NO_CREATE)  != null);

        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if(isChecked){
                    int waktuCepat = 10000;
                    long triggerTime = SystemClock.currentThreadTimeMillis() + waktuCepat;

                    long reapeatInterval = waktuCepat;

                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime,
                            reapeatInterval, notifyPendingIntent);

                    toastMessage = "Bangun Alarm ON";
                }else{
                    alarmManager.cancel(notifyPendingIntent);
                    mNotificationManager.cancelAll();
                    toastMessage = "Bangun Alarm OFF";
                }

                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}