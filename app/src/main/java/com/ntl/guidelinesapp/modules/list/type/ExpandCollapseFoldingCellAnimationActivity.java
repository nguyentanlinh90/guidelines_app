package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.adapter.FoldingCellAdapter;
import com.ntl.guidelinesapp.modules.list.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ExpandCollapseFoldingCellAnimationActivity extends AppCompatActivity {
    private RecyclerView rcvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_collapse_folding_cell_animation);
        AppUtils.setTitleBar(this, ExpandCollapseFoldingCellAnimationActivity.class);

        rcvItems = findViewById(R.id.rcv_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvItems.setLayoutManager(linearLayoutManager);
        FoldingCellAdapter adapter = new FoldingCellAdapter();
        adapter.setData(getListItems());

        rcvItems.setAdapter(adapter);
    }

    private List<Item> getListItems() {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Item("Title " + i, "Content of Title " + i));
        }
        return list;
    }
}