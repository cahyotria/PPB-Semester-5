package com.example.notificationschedular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch mDeviceIdleSwitch, mDeviceChargingSwitch;
    private SeekBar mSeekBar;
    private Switch mPeriodicSwitch;

    private JobScheduler mScheduler;
    private static final int JOB_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeviceIdleSwitch = (Switch) findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = (Switch) findViewById(R.id.chargingSwitch);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mPeriodicSwitch = (Switch) findViewById(R.id.periodicSwitch);

        final TextView mLabel = (TextView) findViewById(R.id.seekBarLabel);
        final TextView mSeekBarProgress = (TextView) findViewById(R.id.seekBarProgress);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 0){
                    String progressLabel = getString(R.string.seekbar_label, progress);
                    mSeekBarProgress.setText(progressLabel);
                }else{
                    mSeekBarProgress.setText(R.string.not_set);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mPeriodicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mLabel.setText(R.string.periodic_interval);
                }else {
                    mLabel.setText(R.string.override_deadline);
                }
            }
        });



    }

    public void scheduleJob(View view) {
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        RadioGroup networkOptions = (RadioGroup) findViewById(R.id.networkOptions);
        int selectNetworkID = networkOptions.getCheckedRadioButtonId();

        int selectNetworkOptions = JobInfo.NETWORK_TYPE_NONE;

        switch (selectNetworkID){
            case R.id.noNetwork:
                selectNetworkOptions = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectNetworkOptions = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectNetworkOptions = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectNetworkOptions)
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());

        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        if(mPeriodicSwitch.isChecked()){
            if(seekBarSet){
                builder.setOverrideDeadline(seekBarInteger * 1000);
            }else{
                Toast.makeText(MainActivity.this, R.string.no_interval_toast,
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            if (seekBarSet) {
                builder.setOverrideDeadline(seekBarInteger * 1000);
            }
        }


        boolean constraintSet = (selectNetworkOptions != JobInfo.NETWORK_TYPE_NONE )
                || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked()
                || seekBarSet;

        if(constraintSet){
            mScheduler.schedule(builder.build());
            Toast.makeText(this, R.string.job_scheduled, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.no_constraint_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJob(View view) {
        if(mScheduler != null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}