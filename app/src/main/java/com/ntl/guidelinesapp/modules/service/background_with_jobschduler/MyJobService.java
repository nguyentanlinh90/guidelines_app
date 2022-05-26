package com.ntl.guidelinesapp.modules.service.background_with_jobschduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    private final String TAG = JobService.class.getSimpleName();
    private boolean isJobCancel;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob");
        doBackground(params);
        return true;
    }

    private void doBackground(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    if (isJobCancel){
                        return;
                    }
                    Log.e(TAG, " run : " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.e(TAG, "jobFinished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "onStopJob");
        isJobCancel = true;
        return true;
    }
}
