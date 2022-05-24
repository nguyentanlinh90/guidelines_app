package com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntl.guidelinesapp.modules.template.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.modules.template.fragment.HistoryFragment;
import com.ntl.guidelinesapp.modules.template.fragment.HomeFragment;

public class MyViewPager2Adapter extends FragmentStateAdapter {
    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FavoriteFragment();
            case 2:
                return new HistoryFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
