package com.example.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private Button btnUpdate;
    private Button btnCancel;
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = (Button) findViewById(R.id.btnNotify);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

    }

    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Welcome!!")
                .setContentText("Selamat Datan di Aplikasi NotifyMe!")
                .setSmallIcon(R.drawable.ic_notify_icon);

        notify();

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.


    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           