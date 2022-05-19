package com.ntl.guidelinesapp.modules.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.ntl.guidelinesapp.R;

public class SharePreferenceActivity extends AppCompatActivity {
    public static final String KEY_FIRST_INSTALL_APP = "key_first_install_app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        getSupportActionBar().setTitle("SharePreferenceActivity");
        MySharePreference mySharePreference = new MySharePreference(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mySharePreference.getBooleanValue(KEY_FIRST_INSTALL_APP)) {
                    //todo: ex - goto Main Screen
                } else {
                    //todo: show Welcome Screen
                    mySharePreference.putBooleanValue(KEY_FIRST_INSTALL_APP, true);
                }
            }
        }, 2000);

    }
}