package com.ntl.guidelinesapp.modules.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.databinding.ActivityMvvmactivityBinding;

public class MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mvvmactivity);

        getSupportActionBar().setTitle("MVVMActivity");

        ActivityMvvmactivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvmactivity);
        LoginViewModel loginViewModel = new LoginViewModel();
        binding.setLoginViewModel(loginViewModel);
    }
}