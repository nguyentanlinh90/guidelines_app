package com.ntl.guidelinesapp.modules.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreference {
    public static final String MY_SHARE_PREFERENCE = "my_share_preference";

    private Context mContext;

    public MySharePreference(Context mContext) {
        this.mContext = mContext;
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, 0);
        return sharedPreferences.getBoolean(key, false);
    }
}
