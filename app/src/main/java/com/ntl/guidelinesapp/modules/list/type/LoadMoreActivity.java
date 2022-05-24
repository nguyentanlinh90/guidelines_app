package com.ntl.guidelinesapp.modules.list.type;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.TranslateAnimationUtil;
import com.ntl.guidelinesapp.modules.list.adapter.LoadMoreAdapter;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;
import com.ntl.guidelinesapp.modules.list.listener.PaginationScrollListener;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.Collections;
import java.util.List;

public class LoadMoreActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private LoadMoreAdapter adapter;
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
        AppUtils.setTitleBar(this, LoadMoreActivity.class);

        pbLoading = findViewById(R.id.pb_loading);

        rcvUsers = findViewById(R.id.rcv_users);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new LoadMoreAdapter();
        rcvUsers.setAdapter(adapter);

        setFirstPage();

        rcvUsers.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
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

    private void setFirstPage() {
        mList = getListUser();
        adapter.setData(mList);

        if (currentPage < lastPage) {
            adapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    private void loadNextPage() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> list = getListUser();
                adapter.removeFooterLoading();
                mList.addAll(list);
                adapter.setData(mList);

                isLoading = false;
                if (currentPage < lastPage) {
                    adapter.addFooterLoading();
                } else {
                    isLastPage = true;
                }
            }
        }, 2000);
    }
}