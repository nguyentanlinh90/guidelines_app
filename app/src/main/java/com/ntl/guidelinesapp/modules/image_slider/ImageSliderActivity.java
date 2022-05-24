package com.ntl.guidelinesapp.modules.image_slider;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.image_slider.fragment.ImageSliderViewPagerCircleIndicatorFragment;

public class ImageSliderActivity extends BaseActivity {
    private FrameLayout flMain;
    private LinearLayout llButton;
    private boolean isShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        AppUtils.setTitleBar(this, ImageSliderActivity.class);

        flMain = findViewById(R.id.fl_main);

        llButton = findViewById(R.id.ll_button);

        findViewById(R.id.bt_1).setOnClickListener(v -> {
            openFragment(new ImageSliderViewPagerCircleIndicatorFragment());
        });
    }

    private void openFragment(Fragment fragment) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        isShowFragment = true;
        updateView(true);
        replaceFragment(R.id.fl_main, fragment);
    }

    public void updateView(boolean isShowFragment) {
        if (!isShowFragment) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        }
        flMain.setVisibility(isShowFragment ? View.VISIBLE : View.GONE);
        llButton.setVisibility(!isShowFragment ? View.VISIBLE : View.GONE);
    }
}