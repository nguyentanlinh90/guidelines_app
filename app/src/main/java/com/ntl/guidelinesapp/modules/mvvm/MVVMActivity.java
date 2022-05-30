package com.ntl.guidelinesapp.modules.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.databinding.ActivityMvvmactivityBinding;
import com.ntl.guidelinesapp.general.model.User;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {
    private RecyclerView rcvUsers;
    private UserAdapter adapter;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mvvmactivity);
        AppUtils.setTitleBar(this, MVVMActivity.class);
        ActivityMvvmactivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvmactivity);
        loginViewModel = new LoginViewModel();
        binding.setLoginViewModel(loginViewModel);

        rcvUsers = findViewById(R.id.rcv_users);


        loginViewModel.getMutableLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter = new UserAdapter(users);
                rcvUsers.setAdapter(adapter);
            }
        });
    }
}