package com.ntl.guidelinesapp.modules.room_db;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.room_db.database.UserDatabase;

public class RoomUpdateActivity extends AppCompatActivity {
    private EditText edtUsername, edtAddress;
    private Button btUpdate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_update);
        AppUtils.setTitleBar(this, RoomUpdateActivity.class);

        edtUsername = findViewById(R.id.edt_user_name);
        edtAddress = findViewById(R.id.etd_address);
        btUpdate = findViewById(R.id.bt_update);

        user = (User) getIntent().getExtras().get("user_object");
        if (user != null) {
            edtUsername.setText(user.getUsername());
            edtAddress.setText(user.getAddress());

            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startUpdate();
                }
            });
        }
    }

    private void startUpdate() {
        String username = edtUsername.getText().toString().trim();
        String address = edtUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address)) {
            return;
        }
        user.setUsername(username);
        user.setAddress(address);

        UserDatabase.getInstance(this).userDAO().updateUser(user);
        Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}