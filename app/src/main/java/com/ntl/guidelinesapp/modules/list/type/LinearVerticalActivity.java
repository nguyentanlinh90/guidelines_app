package com.ntl.guidelinesapp.modules.list.type;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.TranslateAnimationUtil;
import com.ntl.guidelinesapp.modules.list.listener.PaginationScrollListener;
import com.ntl.guidelinesapp.modules.list.model.User;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;

import java.util.Collections;
import java.util.List;

public class LinearVerticalActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private UserLinearAdapter adapter;
    private List<User> mList;
    private FloatingActionButton fabMenu;

    private LinearLayoutManager manager;
    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int lastPage = 5;

    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_vertical);
        AppUtils.setTitleBar(this, LinearVerticalActivity.class);

        pbLoading = findViewById(R.id.pb_loading);

        rcvUsers = findViewById(R.id.rcv_users);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserLinearAdapter();
        mList = getListData();
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

        handleDeleteItemWhenTouch();

        handleLoadMorePage();
    }

    private List<User> getListData() {
        Toast.makeText(this, "Load page: " + currentPage, Toast.LENGTH_SHORT).show();
        return getListUser();
    }

    private void handleDeleteItemWhenTouch() {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        helper.attachToRecyclerView(rcvUsers);
    }

    private void handleLoadMorePage() {
        rcvUsers.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                pbLoading.setVisibility(View.VISIBLE);
                loadMore();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void loadMore() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> list = getListData();
                mList.addAll(list);
                adapter.notifyDataSetChanged();

                isLoading = false;
                pbLoading.setVisibility(View.GONE);
                if (currentPage == lastPage) {
                    isLastPage = true;
                }
            }
        }, 2000);
    }
}