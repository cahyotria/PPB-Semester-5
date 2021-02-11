package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button setAlarmButton;
    private TextView textAlarm;
    private TimePickerDialog timePickerDialog;
    final static int RQS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAlarm = (TextView) findViewById(R.id.alarm);
        setAlarmButton = (Button) findViewById(R.id.buttonSetAlarm);

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAlarm.setText("");
                Calendar cal = Calendar.getInstance();
                timePickerDialog =  new TimePickerDialog(MainActivity.this, onTimeSetListener,
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                timePickerDialog.setTitle("Set Alarm Time");
                timePickerDialog.show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.MILLISECOND, 0);
            if(calSet.compareTo(calNow) <= 0){
                calSet.add(Calendar.DATE, 1);
                Log.i("Hasil", "<= 0");
            }else if(calSet.compareTo(calNow) > 0 ){
                Log.i("Hasil", "> 0");
            }else{
                Log.i("Hasil", "Else");
            }
            textAlarm.setText("***\n" + "Alarm Set On" + calSet.getTime() + "\n***");
            Intent intentMyAlarm = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS, intentMyAlarm,0);
            AlarmManager alarmManager =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            long reapeatInterval =  alarmManager.ELAPSED_REALTIME_WAKEUP;

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), reapeatInterval, pendingIntent);
        }
    };
}