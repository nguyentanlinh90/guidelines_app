package com.ntl.guidelinesapp.core;

import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public void replaceFragment(int id, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

    public FirebaseUser getUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public String getTag(Class<?> cls) {
        return cls.getSimpleName();
    }

    public int getWidthScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public List<User> getListUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            userList.add(new User(R.drawable.img_400_600, "User: " + i));
        }
        return userList;
    }
}
