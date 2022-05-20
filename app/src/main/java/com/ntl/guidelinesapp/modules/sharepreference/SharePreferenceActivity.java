package com.ntl.guidelinesapp.modules.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.General;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharePreferenceActivity extends AppCompatActivity {
    public static final String KEY_FIRST_INSTALL_APP = "key_first_install_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        getSupportActionBar().setTitle("SharePreferenceActivity");
    }

    private void checkFirstAppInstall() {
        // TODO: 20/05/2022 case 1
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

        // TODO: 20/05/2022 case 2
        if (DataLocalManager.getFirstInstallApp()) {
            Toast.makeText(this, "first install app", Toast.LENGTH_SHORT).show();
            DataLocalManager.setFirstInstallApp(true);
        }
    }

    // TODO: 20/05/2022
    private void setDataStringSet() {
        Set<String> userNames = new HashSet<>();
        userNames.add("name 1");
        userNames.add("name 2");
        userNames.add("name 3");
        DataLocalManager.setUserNames(userNames);
    }

    // TODO: 20/05/2022
    private Set<String> getDataStringSet() {
        return DataLocalManager.getUserNames();
    }

    // TODO: 20/05/2022
    private void setGeneralObject() {
        General general = new General("This is Linh");
        DataLocalManager.setGeneralObject(general);
    }

    // TODO: 20/05/2022
    private General getGeneralObject() {
        return DataLocalManager.getGeneralObject();
    }

    // TODO: 20/05/2022
    private void setListGeneralObject() {
        List<General> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new General("This is: " + i));
        }
        DataLocalManager.setListGeneralObject(list);
    }

    // TODO: 20/05/2022
    private List<General> getListGeneralObject() {
        return DataLocalManager.getListGeneralObject();
    }
}