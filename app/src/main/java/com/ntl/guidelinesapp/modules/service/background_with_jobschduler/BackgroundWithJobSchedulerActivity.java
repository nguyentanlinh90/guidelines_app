package com.ntl.guidelinesapp.modules.service.background_with_jobschduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

public class BackgroundWithJobSchedulerActivity extends AppCompatActivity {

    private static final int JOB_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_with_job_scheduler);
        AppUtils.setTitleBar(this, BackgroundWithJobSchedulerActivity.class);

        findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        findViewById(R.id.bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void start() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiresCharging(true)
                //run only wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                // device off and restart => continue run
                .setPersisted(true)
                //run 30' / 1 time, NOTE: MIN : 15'
                .setPeriodic(30 * 60 * 1000)
                .build();
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    private void cancel() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
    }
}