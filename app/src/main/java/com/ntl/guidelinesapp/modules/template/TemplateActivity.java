package com.ntl.guidelinesapp.modules.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.firebase.email_password.EmailPasswordActivity;
import com.ntl.guidelinesapp.modules.template.bottom_viewpager2_fragments.BottomNavigationViewPager2FragmentsActivity;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.NavigationDrawerToolbarFragmentActivity;

public class TemplateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        AppUtils.setTitleBar(this, TemplateActivity.class);

        findViewById(R.id.bt_navigationdrawer_toolbar_fragment).setOnClickListener(v -> {
            if (getUser() != null) {
                AppUtils.gotoScreen(this, NavigationDrawerToolbarFragmentActivity.class);
            } else {
                AppUtils.gotoScreen(this, EmailPasswordActivity.class);
            }
        });

        findViewById(R.id.bt_bottomnavigation_viewpager2_fragments).setOnClickListener(v -> AppUtils.gotoScreen(v.getContext(), BottomNavigationViewPager2FragmentsActivity.class));
    }
}