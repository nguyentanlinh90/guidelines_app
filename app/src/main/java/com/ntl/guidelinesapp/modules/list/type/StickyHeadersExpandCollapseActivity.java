package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.adapter.StickyHeadersAdapter;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class StickyHeadersExpandCollapseActivity extends AppCompatActivity {
    private ExpandableStickyListHeadersListView eslhlvUsers;
    private StickyHeadersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_headers_expand_collapse);
        AppUtils.setTitleBar(this, StickyHeadersExpandCollapseActivity.class);
        eslhlvUsers = findViewById(R.id.slhlv_users);
        adapter = new StickyHeadersAdapter();
        adapter.setData(getList());
        eslhlvUsers.setAdapter(adapter);

        eslhlvUsers.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if (eslhlvUsers.isHeaderCollapsed(headerId)) {
                    eslhlvUsers.expand(headerId);
                } else {
                    eslhlvUsers.collapse(headerId);
                }
            }
        });
    }

    private List<User> getList() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.dog_image, "A"));
        list.add(new User(R.drawable.dog_image, "Adsd"));
        list.add(new User(R.drawable.dog_image, "Adq"));
        list.add(new User(R.drawable.dog_image, "Aggh"));
        list.add(new User(R.drawable.dog_image, "Afwerwr"));
        list.add(new User(R.drawable.dog_image, "Arervc"));
        list.add(new User(R.drawable.dog_image, "Adfsfsdfsd"));

        list.add(new User(R.drawable.dog_image, "B"));
        list.add(new User(R.drawable.dog_image, "Bdsd"));
        list.add(new User(R.drawable.dog_image, "Bdq"));
        list.add(new User(R.drawable.dog_image, "Bggh"));
        list.add(new User(R.drawable.dog_image, "Bfwerwr"));
        list.add(new User(R.drawable.dog_image, "Brervc"));
        list.add(new User(R.drawable.dog_image, "Bdfsfsdfsd"));

        list.add(new User(R.drawable.dog_image, "C"));
        list.add(new User(R.drawable.dog_image, "Cdsd"));
        list.add(new User(R.drawable.dog_image, "Cdq"));
        list.add(new User(R.drawable.dog_image, "Cggh"));
        list.add(new User(R.drawable.dog_image, "Cfwerwr"));
        list.add(new User(R.drawable.dog_image, "Crervc"));
        list.add(new User(R.drawable.dog_image, "Cdfsfsdfsd"));
        return list;
    }
}