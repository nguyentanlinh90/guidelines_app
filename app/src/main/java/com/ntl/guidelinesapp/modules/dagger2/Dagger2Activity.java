package com.ntl.guidelinesapp.modules.dagger2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.MyApplication;
import com.ntl.guidelinesapp.databinding.ActivityDagger2Binding;
import com.ntl.guidelinesapp.modules.dagger2.di.components.AppComponent;
import com.ntl.guidelinesapp.modules.dagger2.di.components.DaggerAppComponent;
import com.ntl.guidelinesapp.modules.dagger2.model.UserModel;
import com.ntl.guidelinesapp.modules.dagger2.viewmodel.UserViewModel;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {
    private AppComponent appComponent;

    private ActivityDagger2Binding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.setTitleBar(this, Dagger2Activity.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dagger2);

        appComponent = DaggerAppComponent.create();
        appComponent.inject(Dagger2Activity.this);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.getModelMutableLiveData().observeForever(new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                binding.setUser(userModel);
            }
        });
    }
}