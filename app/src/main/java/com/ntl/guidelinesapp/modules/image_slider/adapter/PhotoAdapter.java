package com.ntl.guidelinesapp.modules.image_slider.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntl.guidelinesapp.modules.image_slider.fragment.PhotoFragment;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.List;

public class PhotoAdapter extends FragmentStateAdapter {

    private List<Photo> mList;

    public PhotoAdapter(@NonNull FragmentActivity fragmentActivity, List<Photo> list) {
        super(fragmentActivity);
        this.mList = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo", photo);

        return PhotoFragment.newInstance(photo);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }
}
