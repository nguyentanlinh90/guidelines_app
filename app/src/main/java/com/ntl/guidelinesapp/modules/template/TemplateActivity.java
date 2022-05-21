package com.ntl.guidelinesapp.modules.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.NavigationDrawerToolbarFragmentActivity;

public class TemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        AppUtils.setTitleBar(this, TemplateActivity.class.getSimpleName());

        findViewById(R.id.bt_navigationdrawer_toolbar_fragment).setOnClickListener(v->{
            AppUtils.gotoScreen(this, NavigationDrawerToolbarFragmentActivity.class);
        });
    }
}