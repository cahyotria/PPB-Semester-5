package com.example.myalarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Aktif", Toast.LENGTH_SHORT).show();

        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, notif);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "NotificationMYAlarm", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification My Alarm");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intentNotif = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentMyAlarm = PendingIntent.getActivity(context, 0,
                intentNotif, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder build = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentIntent(pendingIntentMyAlarm)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        notificationManager.notify(0, build.build());
    }
}
