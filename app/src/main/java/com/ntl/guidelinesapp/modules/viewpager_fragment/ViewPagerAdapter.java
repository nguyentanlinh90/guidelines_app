package com.ntl.guidelinesapp.modules.viewpager_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntl.guidelinesapp.modules.viewpager_fragment.fragment.FragmentTab1;
import com.ntl.guidelinesapp.modules.viewpager_fragment.fragment.FragmentTab2;
import com.ntl.guidelinesapp.modules.viewpager_fragment.fragment.FragmentTab3;
import com.ntl.guidelinesapp.modules.viewpager_fragment.fragment.FragmentTab4;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentTab2();
            case 2:
                return new FragmentTab3();
            case 3:
                return new FragmentTab4();
            default:
                return new FragmentTab1();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
