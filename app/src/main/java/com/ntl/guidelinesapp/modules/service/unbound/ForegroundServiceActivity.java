package com.ntl.guidelinesapp.modules.service.unbound;

import static com.ntl.guidelinesapp.Constants.ACTION_SERVICE_MUSIC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.Constants;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.service.Song;

public class ForegroundServiceActivity extends AppCompatActivity {
    private Button btPlay, btStop;
    private RelativeLayout rlBottomMusic;
    private ImageView ivSong, ivPre, ivPauseLay, ivNext, ivClose;
    private TextView tvTitle, tvSingle;

    private Song mSong;
    private boolean isPlaying;

    private BroadcastReceiver receiverAction = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                mSong = (Song) bundle.getSerializable(Constants.SONG_OBJECT);
                isPlaying = bundle.getBoolean(Constants.MUSIC_IS_PLAYING);
                int action = bundle.getInt(Constants.ACTION_MUSIC);
                handleBottomMusic(action);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        AppUtils.setTitleBar(this, ForegroundServiceActivity.class);

        btPlay = findViewById(R.id.bt_play);
        btStop = findViewById(R.id.bt_stop);
        rlBottomMusic = findViewById(R.id.rl_bottom_music);
        ivSong = findViewById(R.id.iv_song);
        ivPauseLay = findViewById(R.id.iv_action_pause_play);
        ivClose = findViewById(R.id.iv_action_close);
        tvTitle = findViewById(R.id.tv_title);
        tvSingle = findViewById(R.id.tv_single);

        btPlay.setOnClickListener(v -> startPlayMusic());

        btStop.setOnClickListener(v -> startStopMusic());

        LocalBroadcastManager.getInstance(this).registerReceiver(receiverAction,
                new IntentFilter(Constants.SEND_DATA_ACTION_MUSIC_TO_MAIN));
    }

    private void startPlayMusic() {
        Song song = new Song("File MP3 Test", "Dragon Z", R.raw.mp3_test, R.drawable.img_400_600);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiverAction);
    }

    private void handleBottomMusic(int action) {
        switch (action) {
            case MyMusicService.ACTION_PAUSE:
            case MyMusicService.ACTION_RESUME:
                setStatusButtonPausePlay();
                break;
            case MyMusicService.ACTION_CLOSE:
                rlBottomMusic.setVisibility(View.GONE);
                break;
            case MyMusicService.ACTION_PLAY:
                rlBottomMusic.setVisibility(View.VISIBLE);
                showInfoSong();
                setStatusButtonPausePlay();
                break;
        }
    }

    private void showInfoSong() {
        if (mSong != null) {
            ivSong.setImageResource(mSong.getImage());
            tvTitle.setText(mSong.getTitle());
            tvSingle.setText(mSong.getSingle());
        }
    }

    private void setStatusButtonPausePlay() {
        ivPauseLay.setImageResource(isPlaying ? R.drawable.ic_baseline_pause_24 : R.drawable.ic_baseline_play_arrow_24);
        ivPauseLay.setOnClickListener(v -> senActionToService(isPlaying ? MyMusicService.ACTION_PAUSE : MyMusicService.ACTION_RESUME));
        ivClose.setOnClickListener(v -> senActionToService(MyMusicService.ACTION_CLOSE));
    }

    private void senActionToService(int action) {
        Intent intent = new Intent(this, MyMusicService.class);
        intent.putExtra(ACTION_SERVICE_MUSIC, action);
        startService(intent);
    }
}