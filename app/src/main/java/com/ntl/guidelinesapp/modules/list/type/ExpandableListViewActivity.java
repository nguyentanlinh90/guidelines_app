package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.ExpandableListViewAdapter;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;
import com.ntl.guidelinesapp.modules.list.model.GroupObject;
import com.ntl.guidelinesapp.modules.list.model.ItemObject;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListViewActivity extends BaseActivity {
    private ExpandableListView elUsers;
    private ExpandableListViewAdapter adapter;
    private List<GroupObject> mListGroup;
    private Map<GroupObject, List<ItemObject>> mListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        AppUtils.setTitleBar(this, ExpandableListViewActivity.class);

        elUsers = findViewById(R.id.el_users);

        mListItems = getListMap();
        mListGroup = new ArrayList<>(mListItems.keySet());

        adapter = new ExpandableListViewAdapter(mListGroup, mListItems);
        elUsers.setAdapter(adapter);

        elUsers.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        elUsers.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        elUsers.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(ExpandableListViewActivity.this, mListGroup.get(groupPosition).getName() + " Expand", Toast.LENGTH_SHORT).show();
            }
        });

        elUsers.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(ExpandableListViewActivity.this, mListGroup.get(groupPosition).getName() + " Collapse", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private Map<GroupObject, List<ItemObject>> getListMap() {
        Map<GroupObject, List<ItemObject>> listMap = new HashMap<>();
        GroupObject groupObject1 = new GroupObject(1, "Group 1");
        GroupObject groupObject2 = new GroupObject(2, "Group 2");
        GroupObject groupObject3 = new GroupObject(3, "Group 3");

        List<ItemObject> itemObjects1 = new ArrayList<>();
        itemObjects1.add(new ItemObject(1, "Item 1"));
        itemObjects1.add(new ItemObject(2, "Item 2"));
        itemObjects1.add(new ItemObject(3, "Item 3"));

        List<ItemObject> itemObjects2 = new ArrayList<>();
        itemObjects2.add(new ItemObject(4, "Item 4"));
        itemObjects2.add(new ItemObject(5, "Item 5"));
        itemObjects2.add(new ItemObject(6, "Item 6"));

        List<ItemObject> itemObjects3 = new ArrayList<>();
        itemObjects3.add(new ItemObject(7, "Item 7"));
        itemObjects3.add(new ItemObject(8, "Item 8"));
        itemObjects3.add(new ItemObject(9, "Item 9"));

        listMap.put(groupObject1, itemObjects1);
        listMap.put(groupObject2, itemObjects2);
        listMap.put(groupObject3, itemObjects3);
        return listMap;
    }
}