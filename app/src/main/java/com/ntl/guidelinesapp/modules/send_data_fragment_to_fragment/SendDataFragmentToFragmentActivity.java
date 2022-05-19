package com.ntl.guidelinesapp.modules.send_data_fragment_to_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ntl.guidelinesapp.R;

public class SendDataFragmentToFragmentActivity extends AppCompatActivity
        implements Fragment1.ISendDataToFragment2, Fragment2.IUpdateDataToFragment1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_fragment_to_fragment);

        getSupportActionBar().setTitle("SendDataFragmentToFragmentActivity");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_1, new Fragment1());
        transaction.replace(R.id.fl_2, new Fragment2());
        transaction.commit();
    }

    @Override
    public void send(User user) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fl_2);
        if (fragment2 != null) {
            fragment2.receiverDataFromFragment1(user);
        }
    }

    @Override
    public void update(User user) {
        Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fl_1);

        if (fragment1 != null) {
            fragment1.receiverDataFromFragment2(user);
        }
    }
}