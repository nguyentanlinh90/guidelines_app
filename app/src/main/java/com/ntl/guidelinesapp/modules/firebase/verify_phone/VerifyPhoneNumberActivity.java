package com.ntl.guidelinesapp.modules.firebase.verify_phone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumberActivity extends AppCompatActivity {
    private static final String TAG = VerifyPhoneNumberActivity.class.getSimpleName();

    private LinearLayout llInputPhone, llInputOTP;
    private EditText edtPhone, edtOTP;
    private String mPhone = "";
    private FirebaseAuth mAuth;
    private String mVerificationID = "";
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;
    private boolean isResendOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        AppUtils.setTitleBar(this, VerifyPhoneNumberActivity.class);

        mAuth = FirebaseAuth.getInstance();
        llInputPhone = findViewById(R.id.ll_input_phone);
        llInputOTP = findViewById(R.id.ll_input_otp);
        edtPhone = findViewById(R.id.edt_phone);
        edtOTP = findViewById(R.id.edt_otp);

        findViewById(R.id.bt_verify_phone).setOnClickListener(v -> {
            mPhone = edtPhone.getText().toString().trim();
            startVerifyPhone();
        });

        findViewById(R.id.tv_not_receiver_otp).setOnClickListener(v -> {
            sentOTPAgain();
        });

        clickVerifyOTP();

        edtPhone.requestFocus();
        AppUtils.showSoftKeyBoard(edtPhone);
    }

    private void startVerifyPhone() {
        Log.e(TAG, "startVerifyPhone with phone: " + mPhone);
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)       // Activity (for callback binding)
                        .setForceResendingToken(mForceResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Log.e(TAG, "onVerificationCompleted:" + phoneAuthCredential);

                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.e(TAG, "onVerificationFailed");
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
                                mVerificationID = verificationID;
                                mForceResendingToken = forceResendingToken;
                                if (!isResendOTP) {
                                    llInputPhone.setVisibility(View.INVISIBLE);
                                    llInputOTP.setVisibility(View.VISIBLE);
                                }
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void clickVerifyOTP() {
        findViewById(R.id.bt_verify_otp).setOnClickListener(v -> {
            String strOTP = edtOTP.getText().toString().trim();
            Log.e(TAG, "clickVerifyOTP with otp: " + strOTP + " and verificationID: " + mVerificationID);
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID, strOTP);
            signInWithPhoneAuthCredential(credential);
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            gotoMain(user);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Log.e(TAG, "The verification code entered was invalid");
                            }
                        }
                    }
                });
    }

    private void gotoMain(FirebaseUser user) {
        // TODO: 21/05/2022
        llInputPhone.setVisibility(View.GONE);
        llInputOTP.setVisibility(View.GONE);
        findViewById(R.id.tv_login_sucess).setVisibility(View.VISIBLE);
        AppUtils.hideSoftKeyBoard(getWindow().getDecorView().findViewById(android.R.id.content));
    }

    private void sentOTPAgain() {
        Log.e(TAG, "sentOTPAgain with phone: " + mPhone);
        isResendOTP = true;
        llInputPhone.setVisibility(View.VISIBLE);
        llInputOTP.setVisibility(View.INVISIBLE);
        startVerifyPhone();
    }
}