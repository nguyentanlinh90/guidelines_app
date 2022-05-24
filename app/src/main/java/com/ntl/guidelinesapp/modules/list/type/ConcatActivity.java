package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.UserGridAdapter;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;

public class ConcatActivity extends BaseActivity {
    private UserGridAdapter userGridAdapter;
    private UserLinearAdapter userLinearAdapter;

    private RecyclerView rcvConcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat);
        AppUtils.setTitleBar(this, ConcatActivity.class);

        rcvConcat = findViewById(R.id.rcv_concat);
        rcvConcat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        userGridAdapter = new UserGridAdapter(getWidthScreen());
        userGridAdapter.setData(getListUser());

        userLinearAdapter = new UserLinearAdapter();
        userLinearAdapter.setData(getListUser());

        ConcatAdapter concatAdapter = new ConcatAdapter(userGridAdapter, userLinearAdapter);
        rcvConcat.setAdapter(concatAdapter);

    }
}