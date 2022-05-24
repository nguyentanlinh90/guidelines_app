package com.ntl.guidelinesapp.modules.image_slider.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.List;

public class ImageSliderViewPagerCircleIndicatorAdapter extends PagerAdapter {
    private List<Photo> mList;

    public ImageSliderViewPagerCircleIndicatorAdapter(List<Photo> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        Photo photo = mList.get(position);
        if (photo != null) {
            ImageView imageView = view.findViewById(R.id.iv_photo);
            imageView.setImageResource(photo.getResource());
        }

        //add view
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //need delete code below if not app crash when slide to last
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
