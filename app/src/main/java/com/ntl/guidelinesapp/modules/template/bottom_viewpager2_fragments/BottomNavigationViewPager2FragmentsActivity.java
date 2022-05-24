package com.ntl.guidelinesapp.modules.template.bottom_viewpager2_fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

public class BottomNavigationViewPager2FragmentsActivity extends AppCompatActivity {
    private ViewPager2 vpMain;
    private BottomNavigationView bnvMain;
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view_pager2_fragments);
        AppUtils.setTitleBar(this, BottomNavigationViewPager2FragmentsActivity.class);

        vpMain = findViewById(R.id.vp2_main);
        bnvMain = findViewById(R.id.bnv_main);
        adapter = new MyViewPagerAdapter(this);
        vpMain.setAdapter(adapter);

        bnvMain.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_bottom_home) {
                vpMain.setCurrentItem(0);
            } else if (id == R.id.menu_bottom_favorite) {
                vpMain.setCurrentItem(1);
            } else if (id == R.id.menu_bottom_history) {
                vpMain.setCurrentItem(2);
            } else {
                vpMain.setCurrentItem(3);
            }
            return true;
        });

        vpMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 1:
                        bnvMain.getMenu().findItem(R.id.menu_bottom_favorite).setChecked(true);
                        break;
                    case 2:
                        bnvMain.getMenu().findItem(R.id.menu_bottom_history).setChecked(true);
                        break;
                    case 3:
                        bnvMain.getMenu().findItem(R.id.menu_bottom_account).setChecked(true);
                        break;
                    default:
                        bnvMain.getMenu().findItem(R.id.menu_bottom_home).setChecked(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transformer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_zoon) {
            vpMain.setPageTransformer(new ZoomOutPageTransformer());
        } else if (id == R.id.menu_dept) {
            vpMain.setPageTransformer(new DepthPageTransformer());
        }
        return super.onOptionsItemSelected(item);
    }
}