package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;
import com.ntl.guidelinesapp.modules.list.adapter.UserMultipleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultipleViewActivity extends AppCompatActivity {
    private RecyclerView rcvUsers;
    private UserMultipleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view);
        AppUtils.setTitleBar(this, MultipleViewActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new UserMultipleViewAdapter();
        adapter.setData(getListUser());

        rcvUsers.setAdapter(adapter);
    }

    public List<User> getListUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            userList.add(new User(R.drawable.dog_image, "User: " + i, i < 10 ? 1 : 2));
        }
        return userList;
    }
}