package com.ntl.guidelinesapp.modules.list.type;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.model.TypeUser;
import com.ntl.guidelinesapp.modules.list.adapter.TypeUserAdapter;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.ArrayList;
import java.util.List;

public class LinearHorizontalActivity extends BaseActivity {
    private RecyclerView rcvUsers;
    private TypeUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_horizontal);
        AppUtils.setTitleBar(this, LinearHorizontalActivity.class);

        rcvUsers = findViewById(R.id.rcv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUsers.setLayoutManager(manager);

        adapter = new TypeUserAdapter(this, getWidthScreen() / 3);
        adapter.setData(getListTypeUser());

        rcvUsers.setAdapter(adapter);
    }

    private List<TypeUser> getListTypeUser() {

        List<TypeUser> typeUserList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<User> userList = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                userList.add(new User(R.drawable.dog_image, "User: " + j));
            }
            typeUserList.add(new TypeUser("Type User: " + i, userList));
        }
        return typeUserList;
    }
}