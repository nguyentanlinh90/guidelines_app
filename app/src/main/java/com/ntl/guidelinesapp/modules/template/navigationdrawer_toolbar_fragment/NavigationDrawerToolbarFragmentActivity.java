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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.firebase.email_password.EmailPasswordActivity;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.ChangePasswordFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.FavoriteFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.HistoryFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.HomeFragment;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.fragment.MyProfileFragment;

import java.io.IOException;

public class NavigationDrawerToolbarFragmentActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = NavigationDrawerToolbarFragmentActivity.class.getSimpleName();
    DrawerLayout mDrawerLayout;
    private int FRAGMENT_HOME = 1;
    private int FRAGMENT_FAVORITE = 2;
    private int FRAGMENT_HISTORY = 3;
    private int FRAGMENT_MY_PROFILE = 4;
    private int FRAGMENT_CHANGE_ACCOUNT = 5;
    private int mCurrentFragment = 1;

    private NavigationView mNavigationView;

    private final MyProfileFragment mMyProfileFragment = new MyProfileFragment();

    public ProgressDialog progressDialog;

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

        mNavigationView = findViewById(R.id.navigation_view);

        mNavigationView.setNavigationItemSelectedListener(this);

        replaceFragment(R.id.fl_content, new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Waiting...");

        updateUI();
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
                    replaceFragment(R.id.fl_content, mMyProfileFragment);
                    mCurrentFragment = FRAGMENT_MY_PROFILE;
                }
                break;
            case R.id.nav_change_password:
                if (mCurrentFragment != FRAGMENT_CHANGE_ACCOUNT) {
                    replaceFragment(R.id.fl_content, new ChangePasswordFragment());
                    mCurrentFragment = FRAGMENT_CHANGE_ACCOUNT;
                }
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                AppUtils.gotoScreen(this, EmailPasswordActivity.class);
                finish();
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

    public void updateUI() {
        FirebaseUser user = getUser();
        if (user != null) {
            ImageView ivProfile = mNavigationView.getHeaderView(0).findViewById(R.id.profile_image);
            TextView tvName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
            TextView tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);

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