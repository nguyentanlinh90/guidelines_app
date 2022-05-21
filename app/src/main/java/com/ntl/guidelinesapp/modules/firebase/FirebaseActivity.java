package com.ntl.guidelinesapp.modules.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.firebase.email_password.EmailPasswordActivity;
import com.ntl.guidelinesapp.modules.firebase.verify_phone.VerifyPhoneNumberActivity;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        AppUtils.setTitleBar(this, FirebaseActivity.class.getSimpleName());

        findViewById(R.id.bt_verify_phone).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, VerifyPhoneNumberActivity.class);
        });

        findViewById(R.id.bt_email_password).setOnClickListener(v->{
            AppUtils.gotoScreen(this, EmailPasswordActivity.class);
        });
    }
}