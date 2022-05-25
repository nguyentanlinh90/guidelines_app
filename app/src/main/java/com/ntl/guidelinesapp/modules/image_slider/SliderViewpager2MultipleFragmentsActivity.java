package com.ntl.guidelinesapp.modules.image_slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.image_slider.adapter.PhotoAdapter;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class SliderViewpager2MultipleFragmentsActivity extends AppCompatActivity {
    private ViewPager2 vp2Main;
    private CircleIndicator3 circleIndicator3;
    private PhotoAdapter adapter;
    private List<Photo> mList;

    private Handler handler = new Handler();
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
        setContentView(R.layout.activity_slider_viewpager2_multiple_fragments);
        AppUtils.setTitleBar(this, SliderViewpager2MultipleFragmentsActivity.class);

        vp2Main = findViewById(R.id.vp2_main);
        circleIndicator3 = findViewById(R.id.circle_center);

        mList = getList();
        adapter = new PhotoAdapter(this, mList);
        vp2Main.setAdapter(adapter);
        circleIndicator3.setViewPager(vp2Main);

        handler.postDelayed(runnable, 2000);

        vp2Main.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });
    }

    private List<Photo> getList() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_600_300_1));
        list.add(new Photo(R.drawable.img_600_300_2));
        list.add(new Photo(R.drawable.img_600_300_3));
        list.add(new Photo(R.drawable.img_600_300_4));
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}