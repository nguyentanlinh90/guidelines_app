package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.User;
import com.ntl.guidelinesapp.modules.list.UserGridAdapter;
import com.ntl.guidelinesapp.modules.list.UserLinearAdapter;

import java.util.ArrayList;
import java.util.List;

public class LinearVerticalActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private UserLinearAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_vertical);
        AppUtils.setTitleBar(this, LinearVerticalActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserLinearAdapter();
        adapter.setData(getListUser());

        rcvUsers.setAdapter(adapter);
    }
}