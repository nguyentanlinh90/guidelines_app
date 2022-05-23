package com.ntl.guidelinesapp.modules.list.type;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.TranslateAnimationUtil;
import com.ntl.guidelinesapp.modules.list.model.User;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;

import java.util.Collections;
import java.util.List;

public class LinearVerticalActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private UserLinearAdapter adapter;
    private List<User> mList;
    private FloatingActionButton fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_vertical);
        AppUtils.setTitleBar(this, LinearVerticalActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserLinearAdapter();
        mList = getListUser();
        adapter.setData(mList);

        rcvUsers.setAdapter(adapter);

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUsers.addItemDecoration(decoration);

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                        int posDragged = dragged.getAdapterPosition();
                        int posTarget = target.getAdapterPosition();
                        Collections.swap(mList, posDragged, posTarget);
                        adapter.notifyItemMoved(posDragged, posTarget);
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                    }
                });
        helper.attachToRecyclerView(rcvUsers);

        fabMenu = findViewById(R.id.fab_menu);
        rcvUsers.setOnTouchListener(new TranslateAnimationUtil(this, fabMenu));

        /*rcvUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fabMenu.setVisibility(View.GONE);
                } else {
                    fabMenu.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }
}