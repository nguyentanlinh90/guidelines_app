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
import com.ntl.guidelinesapp.modules.list.type.LinearHorizontalActivity;
import com.ntl.guidelinesapp.modules.list.type.LinearVerticalActivity;
import com.ntl.guidelinesapp.modules.list.type.MultipleViewActivity;
import com.ntl.guidelinesapp.modules.list.type.StaggeredGridActivity;

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

        findViewById(R.id.bt_linear_horizontal_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, LinearHorizontalActivity.class);
        });

        findViewById(R.id.bt_staggered_grid_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, StaggeredGridActivity.class);
        });

        findViewById(R.id.bt_multiple_view_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, MultipleViewActivity.class);
        });
    }
}