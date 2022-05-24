package com.ntl.guidelinesapp.modules.viewpager_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntl.guidelinesapp.general.fragment.AccountFragment;
import com.ntl.guidelinesapp.general.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.general.fragment.HistoryFragment;
import com.ntl.guidelinesapp.general.fragment.HomeFragment;
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
                return new FavoriteFragment();
            case 2:
                return new HistoryFragment();
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
