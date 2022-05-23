package com.ntl.guidelinesapp.modules.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.type.GridActivity;
import com.ntl.guidelinesapp.modules.list.type.LinearVerticalActivity;

public class ListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AppUtils.setTitleBar(this, ListActivity.class);

        findViewById(R.id.bt_grid_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, GridActivity.class);
        });

        findViewById(R.id.bt_linear_vertical_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, LinearVerticalActivity.class);
        });
    }
}