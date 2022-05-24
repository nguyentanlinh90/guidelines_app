package com.ntl.guidelinesapp.modules.template.bottom_viewpager2_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntl.guidelinesapp.modules.template.fragment.AccountFragment;
import com.ntl.guidelinesapp.modules.template.fragment.DiscoveryFragment;
import com.ntl.guidelinesapp.modules.template.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.modules.template.fragment.HomeFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FavoriteFragment();
            case 2:
                return new DiscoveryFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
