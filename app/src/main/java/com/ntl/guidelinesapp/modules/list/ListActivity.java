package com.ntl.guidelinesapp.modules.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.ChangeTypeAdapter;
import com.ntl.guidelinesapp.modules.list.type.ChangeTypeListActivity;
import com.ntl.guidelinesapp.modules.list.type.ChatActivity;
import com.ntl.guidelinesapp.modules.list.type.ConcatActivity;
import com.ntl.guidelinesapp.modules.list.type.ExpandCollapseFoldingCellAnimationActivity;
import com.ntl.guidelinesapp.modules.list.type.ExpandableListViewActivity;
import com.ntl.guidelinesapp.modules.list.type.GridActivity;
import com.ntl.guidelinesapp.modules.list.type.InstagramActivity;
import com.ntl.guidelinesapp.modules.list.type.LinearHorizontalActivity;
import com.ntl.guidelinesapp.modules.list.type.LinearVerticalActivity;
import com.ntl.guidelinesapp.modules.list.type.LoadMoreActivity;
import com.ntl.guidelinesapp.modules.list.type.MultipleViewActivity;
import com.ntl.guidelinesapp.modules.list.type.RCVInsideRCVActivity;
import com.ntl.guidelinesapp.modules.list.type.StaggeredGridActivity;
import com.ntl.guidelinesapp.modules.list.type.StickyHeadersActivity;
import com.ntl.guidelinesapp.modules.list.type.StickyHeadersExpandCollapseActivity;
import com.ntl.guidelinesapp.modules.list.type.SwipeActivity;

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

        findViewById(R.id.bt_sticky_headers_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, StickyHeadersActivity.class);
        });

        findViewById(R.id.bt_sticky_headers_expand_collapse_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, StickyHeadersExpandCollapseActivity.class);
        });

        findViewById(R.id.bt_instagram_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, InstagramActivity.class);
        });

        findViewById(R.id.bt_expandable_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, ExpandableListViewActivity.class);
        });

        findViewById(R.id.bt_expand_collapse_folding_cell_animation_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, ExpandCollapseFoldingCellAnimationActivity.class);
        });

        findViewById(R.id.bt_load_more_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, LoadMoreActivity.class);
        });

        findViewById(R.id.bt_rcv_inside_rcv_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, RCVInsideRCVActivity.class);
        });

        findViewById(R.id.bt_chat_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, ChatActivity.class);
        });

        findViewById(R.id.bt_concat_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, ConcatActivity.class);
        });

        findViewById(R.id.bt_swipe_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, SwipeActivity.class);
        });

        findViewById(R.id.bt_change_type_list).setOnClickListener(v -> {
            AppUtils.gotoScreen(this, ChangeTypeListActivity.class);
        });

    }
}