package com.ntl.guidelinesapp.modules.service.type;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.Constants;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.service.ServiceActivity;
import com.ntl.guidelinesapp.modules.service.Song;

public class ForegroundServiceActivity extends AppCompatActivity {
    private Button btPlay, btStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        AppUtils.setTitleBar(this, ForegroundServiceActivity.class);

        btPlay = findViewById(R.id.bt_play);
        btStop = findViewById(R.id.bt_stop);

        btPlay.setOnClickListener(v -> startPlayMusic());

        btStop.setOnClickListener(v -> startStopMusic());
    }

    private void startPlayMusic() {
        Song song = new Song("Big city boy", "Linh Nguyen", R.raw.bigcityboy_binz, R.drawable.img_400_600);
        Intent intent = new Intent(this, MyMusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.SONG_OBJECT, song);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void startStopMusic() {
        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);
    }
}