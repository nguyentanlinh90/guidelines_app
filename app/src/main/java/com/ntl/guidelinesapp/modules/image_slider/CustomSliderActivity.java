package com.ntl.guidelinesapp.modules.image_slider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.image_slider.adapter.CustomSliderAdapter;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class CustomSliderActivity extends AppCompatActivity {
    private ViewPager2 vp2Main;
    private CircleIndicator3 circleIndicator3;
    private List<Photo> mList;
    private CustomSliderAdapter adapter;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (vp2Main.getCurrentItem() == mList.size() - 1) {
                vp2Main.setCurrentItem(0);
            } else {
                vp2Main.setCurrentItem(vp2Main.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_slider);
        AppUtils.setTitleBar(this, CustomSliderActivity.class);

        vp2Main = findViewById(R.id.vp2_main);
        circleIndicator3 = findViewById(R.id.circle_center);

        mList = getList();
        adapter = new CustomSliderAdapter(mList);
        vp2Main.setAdapter(adapter);
        circleIndicator3.setViewPager(vp2Main);

        setTingViewPager();

        vp2Main.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });

        handler.postDelayed(runnable, 2000);
    }

    private List<Photo> getList() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_600_300_1));
        list.add(new Photo(R.drawable.img_600_300_2));
        list.add(new Photo(R.drawable.img_600_300_3));
        list.add(new Photo(R.drawable.img_600_300_4));
        list.add(new Photo(R.drawable.img_600_300_1));
        list.add(new Photo(R.drawable.img_600_300_2));
        list.add(new Photo(R.drawable.img_600_300_3));
        list.add(new Photo(R.drawable.img_600_300_4));
        return list;
    }

    private void setTingViewPager() {
        vp2Main.setOffscreenPageLimit(3);
        vp2Main.setClipToPadding(false);
        vp2Main.setClipChildren(false);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));

        //need add transformer bellow if want to item between bigger 2 by side
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        vp2Main.setPageTransformer(transformer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}