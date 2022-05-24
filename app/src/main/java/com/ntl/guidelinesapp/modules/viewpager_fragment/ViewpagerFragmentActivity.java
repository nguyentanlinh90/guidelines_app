package com.ntl.guidelinesapp.modules.viewpager_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.general.fragment.HomeFragment;

public class ViewpagerFragmentActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment);
        getSupportActionBar().setTitle("ViewpagerFragmentActivity");

        bottomNavigationView = findViewById(R.id.bnv_menu);
        viewPager = findViewById(R.id.vp_main);

        //Default setOffscreenPageLimit = 1: load before 1 fragment ex: open 1 will load 2, open 2 will load 3
        //setOffscreenPageLimit = 2: load before 2 fragment ex: open 1 will load 2,3, open 2 will load 3,4
        //...
        viewPager.setOffscreenPageLimit(2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_bottom_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_bottom_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_bottom_history).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_bottom_account).setChecked(true);
                        break;
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        viewPager.setCurrentItem(0);

                        //load fragment when click bottom menu
                        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().getFragments().get(0);
                        fragment.reloadData();
                        break;
                    case R.id.menu_bottom_favorite:
                        viewPager.setCurrentItem(1);

                        //load fragment when click bottom menu
                        FavoriteFragment fragment2 = (FavoriteFragment) getSupportFragmentManager().getFragments().get(1);
                        fragment2.reloadData();
                        break;
                    case R.id.menu_bottom_history:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_bottom_account:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}