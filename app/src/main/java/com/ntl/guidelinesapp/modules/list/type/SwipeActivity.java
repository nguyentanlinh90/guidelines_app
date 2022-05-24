package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.RecyclerViewItemTouchHelper;
import com.ntl.guidelinesapp.modules.list.adapter.SwipeAdapter;
import com.ntl.guidelinesapp.modules.list.listener.ItemTouchHelperListener;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

public class SwipeActivity extends BaseActivity implements ItemTouchHelperListener {
    private RelativeLayout rlRootView;
    private RecyclerView rcvUsers;
    private SwipeAdapter adapter;
    private List<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        AppUtils.setTitleBar(this, SwipeActivity.class);

        rlRootView = findViewById(R.id.rl_root_view);
        rcvUsers = findViewById(R.id.rcv_users);
        rcvUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new SwipeAdapter();
        rcvUsers.setAdapter(adapter);

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUsers.addItemDecoration(decoration);

        mList = getListUser();
        adapter.setData(mList);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcvUsers);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SwipeAdapter.SwipeViewHolder) {
            String strUserDelete = mList.get(viewHolder.getAbsoluteAdapterPosition()).getName();
            User userDelete = mList.get(viewHolder.getAbsoluteAdapterPosition());
            int indexDelete = viewHolder.getAbsoluteAdapterPosition();

            //delete item
            adapter.deleteUser(indexDelete);

            Snackbar snackbar = Snackbar.make(rlRootView, strUserDelete + " delete", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.undo(userDelete, indexDelete);
                    if (indexDelete == 0 || indexDelete == mList.size() - 1) {
                        rcvUsers.scrollToPosition(indexDelete);
                    }
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}