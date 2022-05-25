package com.ntl.guidelinesapp.modules.image_slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_viewpager2_multiple_fragments);
        AppUtils.setTitleBar(this, SliderViewpager2MultipleFragmentsActivity.class);

        vp2Main = findViewById(R.id.vp2_main);
        circleIndicator3 = findViewById(R.id.circle_center);

        adapter = new PhotoAdapter(this, getList());
        vp2Main.setAdapter(adapter);
        circleIndicator3.setViewPager(vp2Main);

    }

    private List<Photo> getList() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_600_300_1));
        list.add(new Photo(R.drawable.img_600_300_2));
        list.add(new Photo(R.drawable.img_600_300_3));
        list.add(new Photo(R.drawable.img_600_300_4));
        return list;
    }

}