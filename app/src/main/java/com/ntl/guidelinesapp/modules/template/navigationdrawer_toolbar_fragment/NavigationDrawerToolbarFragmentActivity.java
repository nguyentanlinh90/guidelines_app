package com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.firebase.email_password.EmailPasswordActivity;
import com.ntl.guidelinesapp.modules.template.bottom_viewpager2_fragments.DepthPageTransformer;
import com.ntl.guidelinesapp.modules.template.fragment.MyAccountFragment;

import java.io.IOException;

public class NavigationDrawerToolbarFragmentActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = NavigationDrawerToolbarFragmentActivity.class.getSimpleName();
    DrawerLayout dlMain;
    private int FRAGMENT_HOME = 1;
    private int FRAGMENT_FAVORITE = 2;
    private int FRAGMENT_HISTORY = 3;
    private int FRAGMENT_MY_ACCOUNT = 4;
    private int FRAGMENT_CHANGE_ACCOUNT = 5;
    private int mCurrentFragment = 1;

    private NavigationView navMainLeft;

    private final MyAccountFragment mMyProfileFragment = new MyAccountFragment();

    public ProgressDialog progressDialog;

    private TabLayout tlMain;
    private ViewPager2 vp2Main;
    private MyViewPager2Adapter adapter;
    private BottomNavigationView bnvMain;

    public ActivityResultLauncher<String> mPermissionReadStorageResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    mMyProfileFragment.openGallery();
                } else {
                    Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                }
            });

    public ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                if (intent == null) {
                    return;
                }

                Uri uri = intent.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    mMyProfileFragment.setBitmap(bitmap);
                    mMyProfileFragment.setUriAvatar(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_toolbar_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tlMain = findViewById(R.id.tl_main);
        vp2Main = findViewById(R.id.vp2_main);
        bnvMain = findViewById(R.id.bnv_main);

        vp2Main.setPageTransformer(new DepthPageTransformer());
        adapter = new MyViewPager2Adapter(this);
        vp2Main.setAdapter(adapter);

        new TabLayoutMediator(tlMain, vp2Main, (tab, position) -> {
            switch (position) {
                case 1:
                    tab.setText("Favorite");
                    break;
                case 2:
                    tab.setText("History");
                    break;
                case 3:
                    tab.setText("Account");
                    break;
                default:
                    tab.setText("Home");
                    break;
            }
        }).attach();

        //change color title toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        dlMain = findViewById(R.id.dl_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlMain, toolbar, R.string.drawer_navigation_open, R.string.drawer_navigation_close);
        dlMain.addDrawerListener(toggle);

        //change color button toggle drawer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        } else {
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        }

        toggle.syncState();

        navMainLeft = findViewById(R.id.nav_main_left);

        navMainLeft.setNavigationItemSelectedListener(this);

//        replaceFragment(R.id.fl_content, new HomeFragment());
        navMainLeft.getMenu().findItem(R.id.nav_home).setChecked(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Waiting...");

        updateUI();

        vp2Main.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 1:
                        mCurrentFragment = FRAGMENT_FAVORITE;
                        navMainLeft.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                        bnvMain.getMenu().findItem(R.id.menu_bottom_favorite).setChecked(true);
                        break;
                    case 2:
                        mCurrentFragment = FRAGMENT_HISTORY;
                        navMainLeft.getMenu().findItem(R.id.nav_history).setChecked(true);
                        bnvMain.getMenu().findItem(R.id.menu_bottom_history).setChecked(true);
                        break;
                    case 3:
                        mCurrentFragment = FRAGMENT_MY_ACCOUNT;
                        navMainLeft.getMenu().findItem(R.id.nav_my_account).setChecked(true);
                        bnvMain.getMenu().findItem(R.id.menu_bottom_account).setChecked(true);
                        break;
                    default:
                        mCurrentFragment = FRAGMENT_HOME;
                        navMainLeft.getMenu().findItem(R.id.nav_home).setChecked(true);
                        bnvMain.getMenu().findItem(R.id.menu_bottom_home).setChecked(true);
                        break;
                }
            }
        });

        bnvMain.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_bottom_home) {
                vp2Main.setCurrentItem(0);
                mCurrentFragment = FRAGMENT_HOME;
                navMainLeft.getMenu().findItem(R.id.nav_home).setChecked(true);
            } else if (id == R.id.menu_bottom_favorite) {
                vp2Main.setCurrentItem(1);
                mCurrentFragment = FRAGMENT_FAVORITE;
                navMainLeft.getMenu().findItem(R.id.nav_favorite).setChecked(true);
            } else if (id == R.id.menu_bottom_history) {
                vp2Main.setCurrentItem(2);
                mCurrentFragment = FRAGMENT_HISTORY;
                navMainLeft.getMenu().findItem(R.id.nav_history).setChecked(true);
            } else {
                vp2Main.setCurrentItem(3);
                mCurrentFragment = FRAGMENT_MY_ACCOUNT;
                navMainLeft.getMenu().findItem(R.id.nav_my_account).setChecked(true);
            }
            return true;
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (mCurrentFragment != FRAGMENT_HOME) {
//                    replaceFragment(R.id.fl_content, new HomeFragment());
                    vp2Main.setCurrentItem(0);
                    mCurrentFragment = FRAGMENT_HOME;
                    bnvMain.getMenu().findItem(R.id.menu_bottom_home).setChecked(true);
                }
                break;
            case R.id.nav_favorite:
                if (mCurrentFragment != FRAGMENT_FAVORITE) {
//                    replaceFragment(R.id.fl_content, new FavoriteFragment());
                    vp2Main.setCurrentItem(1);
                    mCurrentFragment = FRAGMENT_FAVORITE;
                    bnvMain.getMenu().findItem(R.id.menu_bottom_favorite).setChecked(true);
                }
                break;
            case R.id.nav_history:
                if (mCurrentFragment != FRAGMENT_HISTORY) {
//                    replaceFragment(R.id.fl_content, new HistoryFragment());
                    vp2Main.setCurrentItem(2);
                    mCurrentFragment = FRAGMENT_HISTORY;
                    bnvMain.getMenu().findItem(R.id.menu_bottom_history).setChecked(true);
                }
                break;
            case R.id.nav_my_account:
                if (mCurrentFragment != FRAGMENT_MY_ACCOUNT) {
//                    replaceFragment(R.id.fl_content, mMyProfileFragment);
                    vp2Main.setCurrentItem(3);
                    mCurrentFragment = FRAGMENT_MY_ACCOUNT;
                    bnvMain.getMenu().findItem(R.id.menu_bottom_account).setChecked(true);
                }
                break;
            case R.id.nav_change_password:
                if (mCurrentFragment != FRAGMENT_CHANGE_ACCOUNT) {
//                    replaceFragment(R.id.fl_content, new ChangePasswordFragment());
                    mCurrentFragment = FRAGMENT_CHANGE_ACCOUNT;
                }
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                AppUtils.gotoScreen(this, EmailPasswordActivity.class);
                finish();
                break;
        }
        dlMain.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void updateUI() {
        FirebaseUser user = getUser();
        if (user != null) {
            ImageView ivProfile = navMainLeft.getHeaderView(0).findViewById(R.id.profile_image);
            TextView tvName = navMainLeft.getHeaderView(0).findViewById(R.id.tv_name);
            TextView tvEmail = navMainLeft.getHeaderView(0).findViewById(R.id.tv_email);

            Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.ic_baseline_person_24).into(ivProfile);
            if (user.getDisplayName() != null) {
                tvName.setText(user.getDisplayName());
            } else {
                tvName.setVisibility(View.GONE);
            }
            tvEmail.setText(user.getEmail());
        }
    }

    public void showProgress() {
        progressDialog.show();
    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }
}