package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.GridAdapter;
import com.ntl.guidelinesapp.modules.list.adapter.ListDataAdapter;
import com.ntl.guidelinesapp.modules.list.adapter.VerticalAdapter;
import com.ntl.guidelinesapp.modules.list.model.TypeUser;

import java.util.ArrayList;
import java.util.List;

public class RCVInsideRCVActivity extends BaseActivity {
    private RecyclerView rcvListData;
    ListDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcvinside_rcvactivity);
        AppUtils.setTitleBar(this, RCVInsideRCVActivity.class);

        rcvListData = findViewById(R.id.rcv_users_list);
        rcvListData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ListDataAdapter();
        rcvListData.setAdapter(adapter);
        adapter.setData(this, getListData());

    }

    private List<TypeUser> getListData() {
        List<TypeUser> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TypeUser typeUser = new TypeUser(i % 2 == 0 ? "1" : "2", getListUser());
            list.add(typeUser);
        }

        return list;
    }
}