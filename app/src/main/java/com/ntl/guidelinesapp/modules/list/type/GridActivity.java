package com.ntl.guidelinesapp.modules.list.type;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.UserGridAdapter;

public class GridActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private UserGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        AppUtils.setTitleBar(this, GridActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserGridAdapter(getWidthScreen() / 3);
        adapter.setData(getListUser());

        rcvUsers.setAdapter(adapter);
    }
}