package com.ntl.guidelinesapp.modules.list.type;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.UserGridAdapter;

public class StaggeredGridActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private UserGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);
        AppUtils.setTitleBar(this, StaggeredGridActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserGridAdapter(getWidthScreen() / 2);
        adapter.setData(getListUser());

        rcvUsers.setAdapter(adapter);
    }
}