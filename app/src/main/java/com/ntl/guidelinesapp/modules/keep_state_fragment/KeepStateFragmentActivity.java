package com.ntl.guidelinesapp.modules.keep_state_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.General;

public class KeepStateFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_state_fragment);

        getSupportActionBar().setTitle("KeepStateFragmentActivity");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, new UserFragment());
        transaction.commit();
    }

    public void gotoDetailFragment(General general) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, DetailFragment.newInstance(general));
        transaction.addToBackStack(DetailFragment.TAG); //should have a TAG, to back to stack when click button Back on DetailFragment
        transaction.commit();
    }

    public void gotoNextFragment(General general) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, NextFragment.newInstance(general));
        transaction.addToBackStack(NextFragment.TAG); //should have a TAG, to back to stack when click button Back on NextFragment
        transaction.commit();
    }
}