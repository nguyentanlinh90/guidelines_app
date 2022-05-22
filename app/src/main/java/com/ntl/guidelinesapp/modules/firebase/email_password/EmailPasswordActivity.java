package com.ntl.guidelinesapp.modules.firebase.email_password;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

public class EmailPasswordActivity extends AppCompatActivity {
    private final String TAG = EmailPasswordActivity.class.getSimpleName();

    private LinearLayout llLogin, llLoginSuccess, llRegister;
    private EditText edtLoginEmail, edtRegisterEmail, edtLoginPassword, edtRegisterPassword;
    private TextView tvUserEmail, tvRegister, tvLogin;
    private Button btLogin, btLogout, btRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        AppUtils.setTitleBar(this, EmailPasswordActivity.class.getSimpleName());

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Waiting...");

        llLogin = findViewById(R.id.ll_login);
        llLoginSuccess = findViewById(R.id.ll_login_success);
        llRegister = findViewById(R.id.ll_register);
        edtLoginEmail = findViewById(R.id.edt_login_email);
        edtLoginPassword = findViewById(R.id.edt_login_password);
        edtRegisterEmail = findViewById(R.id.edt_register_email);
        edtRegisterPassword = findViewById(R.id.edt_register_password);
        tvUserEmail = findViewById(R.id.tv_user_email);
        tvRegister = findViewById(R.id.tv_register_user);
        tvLogin = findViewById(R.id.tv_login);
        btLogin = findViewById(R.id.bt_login);
        btLogout = findViewById(R.id.bt_logout);
        btRegister = findViewById(R.id.bt_register);

        initEvent();

        checkLogin();

    }

    private void initEvent() {
        tvLogin.setOnClickListener(v -> {
            llLogin.setVisibility(View.VISIBLE);
            llRegister.setVisibility(View.GONE);
        });

        tvRegister.setOnClickListener(v -> {
            llLogin.setVisibility(View.GONE);
            llRegister.setVisibility(View.VISIBLE);
        });

        btLogin.setOnClickListener(v -> {
            startLogin();
        });

        btRegister.setOnClickListener(v -> {
            startRegister();
        });

        btLogout.setOnClickListener(v -> {
            startLogout();
        });
    }

    private void startLogin() {
        progressDialog.show();
        String email = edtLoginEmail.getText().toString().trim();
        String password = edtLoginPassword.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void startRegister() {
        progressDialog.show();
        String email = edtRegisterEmail.getText().toString().trim();
        String password = edtRegisterPassword.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void checkLogin() {
        progressDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            llLoginSuccess.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
            llRegister.setVisibility(View.GONE);
            tvUserEmail.setText(user.getEmail());
        } else  {
            llLogin.setVisibility(View.VISIBLE);
            llRegister.setVisibility(View.GONE);
            llLoginSuccess.setVisibility(View.GONE);
        }
        progressDialog.dismiss();
    }

    private void startLogout() {
        FirebaseAuth.getInstance().signOut();
        updateUI(null);
    }
}