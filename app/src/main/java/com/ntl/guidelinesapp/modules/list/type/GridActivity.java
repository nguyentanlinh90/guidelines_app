package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.User;
import com.ntl.guidelinesapp.modules.list.UserGridAdapter;

import java.util.ArrayList;
import java.util.List;

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

    private List<User> getListUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            userList.add(new User(R.drawable.dog_image, "User: " + i));
        }
        return userList;
    }
}