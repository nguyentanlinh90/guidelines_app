package com.ntl.guidelinesapp.modules.send_data_activity_to_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;

public class SendDataActivityToFragmentActivity extends AppCompatActivity implements ISendDataToActivity{
    private EditText edtEmail;
    private Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_fragment);

        getSupportActionBar().setTitle("SendDataActivityToFragmentActivity");

        edtEmail = findViewById(R.id.edt_email);
        edtEmail.setImeOptions(EditorInfo.IME_ACTION_DONE);
        btSend = findViewById(R.id.bt_send_data_to_fragment);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }

    private void sendData() {
        String strEmail = edtEmail.getText().toString().trim();
        User user = new User(strEmail);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_home, HomeFragment.newInstance(user, strEmail));
        transaction.commit();
        AppUtils.hideSoftKeyBoard(getWindow().getDecorView().findViewById(android.R.id.content));
    }


    public EditText getEdtEmail() {
        return edtEmail;
    }

    @Override
    public void sendData(User user) {
        edtEmail.setText(user.getEmail());
    }
}