package com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.ChangePasswordFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.HistoryFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.HomeFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.MyProfileFragment;

public class NavigationDrawerToolbarFragmentActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    private int FRAGMENT_HOME = 1;
    private int FRAGMENT_FAVORITE = 2;
    private int FRAGMENT_HISTORY = 3;
    private int FRAGMENT_MY_PROFILE = 4;
    private int FRAGMENT_CHANGE_ACCOUNT = 5;
    private int mCurrentFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_toolbar_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change color title toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mDrawerLayout = findViewById(R.id.drawable_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.drawer_navigation_open, R.string.drawer_navigation_close);
        mDrawerLayout.addDrawerListener(toggle);

        //change color button toggle drawer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        } else {
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        }

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        ImageView ivProfile = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        ivProfile.setImageResource(R.drawable.dog_image);

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(R.id.fl_content, new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (mCurrentFragment != FRAGMENT_HOME) {
                    replaceFragment(R.id.fl_content, new HomeFragment());
                    mCurrentFragment = FRAGMENT_HOME;
                }
                break;
            case R.id.nav_favorite:
                if (mCurrentFragment != FRAGMENT_FAVORITE) {
                    replaceFragment(R.id.fl_content, new FavoriteFragment());
                    mCurrentFragment = FRAGMENT_FAVORITE;
                }
                break;
            case R.id.nav_history:
                if (mCurrentFragment != FRAGMENT_HISTORY) {
                    replaceFragment(R.id.fl_content, new HistoryFragment());
                    mCurrentFragment = FRAGMENT_HISTORY;
                }
                break;
            case R.id.nav_my_profile:
                if (mCurrentFragment != FRAGMENT_MY_PROFILE) {
                    replaceFragment(R.id.fl_content, new MyProfileFragment());
                    mCurrentFragment = FRAGMENT_MY_PROFILE;
                }
                break;
            case R.id.nav_change_password:
                if (mCurrentFragment != FRAGMENT_CHANGE_ACCOUNT) {
                    replaceFragment(R.id.fl_content, new ChangePasswordFragment());
                    mCurrentFragment = FRAGMENT_CHANGE_ACCOUNT;
                }
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}