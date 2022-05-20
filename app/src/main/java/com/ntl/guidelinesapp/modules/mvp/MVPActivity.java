package com.ntl.guidelinesapp.modules.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;

public class MVPActivity extends AppCompatActivity implements ILoginListener {
    private EditText edtEmail, edtPass;
    private Button btLogin;
    private TextView tvStatus;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpactivity);

        getSupportActionBar().setTitle("MVPActivity");

        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);
        btLogin = findViewById(R.id.bt_login);
        tvStatus = findViewById(R.id.tv_status);

        loginPresenter = new LoginPresenter(this);
        btLogin.setOnClickListener(v -> {
            String strEmail = edtEmail.getText().toString().trim();
            String strPass = edtPass.getText().toString().trim();
            User user = new User(strEmail, strPass);
            loginPresenter.receiverData(user);

        });
    }

    @Override
    public void onSuccess() {
        tvStatus.setText("Login Success");
    }

    @Override
    public void onFail() {
        tvStatus.setText("Login Fail");
    }
}