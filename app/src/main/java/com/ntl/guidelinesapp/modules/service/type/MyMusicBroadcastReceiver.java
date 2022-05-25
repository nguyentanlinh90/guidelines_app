package com.ntl.guidelinesapp.modules.service.type;

import static com.ntl.guidelinesapp.Constants.ACTION_SERVICE_MUSIC;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ntl.guidelinesapp.Constants;
import com.ntl.guidelinesapp.modules.service.Song;

public class MyMusicBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra(Constants.ACTION_MUSIC, 0);

        Intent intentService = new Intent(context, MyMusicService.class);
        intentService.putExtra(ACTION_SERVICE_MUSIC, actionMusic);
        context.startService(intentService);
    }
}
