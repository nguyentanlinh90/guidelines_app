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
        list.add(new User(R.drawable.img_400_600, "A"));
        list.add(new User(R.drawable.img_400_600, "Adsd"));
        list.add(new User(R.drawable.img_400_600, "Adq"));
        list.add(new User(R.drawable.img_400_600, "Aggh"));
        list.add(new User(R.drawable.img_400_600, "Afwerwr"));
        list.add(new User(R.drawable.img_400_600, "Arervc"));
        list.add(new User(R.drawable.img_400_600, "Adfsfsdfsd"));

        list.add(new User(R.drawable.img_400_600, "B"));
        list.add(new User(R.drawable.img_400_600, "Bdsd"));
        list.add(new User(R.drawable.img_400_600, "Bdq"));
        list.add(new User(R.drawable.img_400_600, "Bggh"));
        list.add(new User(R.drawable.img_400_600, "Bfwerwr"));
        list.add(new User(R.drawable.img_400_600, "Brervc"));
        list.add(new User(R.drawable.img_400_600, "Bdfsfsdfsd"));

        list.add(new User(R.drawable.img_400_600, "C"));
        list.add(new User(R.drawable.img_400_600, "Cdsd"));
        list.add(new User(R.drawable.img_400_600, "Cdq"));
        list.add(new User(R.drawable.img_400_600, "Cggh"));
        list.add(new User(R.drawable.img_400_600, "Cfwerwr"));
        list.add(new User(R.drawable.img_400_600, "Crervc"));
        list.add(new User(R.drawable.img_400_600, "Cdfsfsdfsd"));
        return list;
    }
}