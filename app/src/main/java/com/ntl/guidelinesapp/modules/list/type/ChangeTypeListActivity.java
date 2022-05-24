package com.ntl.guidelinesapp.modules.list.type;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.list.adapter.ChangeTypeAdapter;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class ChangeTypeListActivity extends BaseActivity {
    private RecyclerView rcvImages;
    private ChangeTypeAdapter adapter;
    private int currentType = Photo.TYPE_GRID;
    private List<Photo> mList;

    private Menu mMenu;

    private int mCurrentItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_type_list);
        AppUtils.setTitleBar(this, ChangeTypeListActivity.class);

        rcvImages = findViewById(R.id.rcv_images);
        rcvImages.setLayoutManager(new GridLayoutManager(this, 2));

        mList = getListPhoto();
        setTypeDisplay(Photo.TYPE_GRID);
        adapter = new ChangeTypeAdapter(this, mList);
        rcvImages.setAdapter(adapter);

    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo("https://picsum.photos/200/300"));
        list.add(new Photo("https://picsum.photos/200/400"));
        list.add(new Photo("https://picsum.photos/200/300"));
        list.add(new Photo("https://picsum.photos/200/400"));
        list.add(new Photo("https://picsum.photos/200/400"));
        list.add(new Photo("https://picsum.photos/200/300"));
        list.add(new Photo("https://picsum.photos/200/400"));
        list.add(new Photo("https://picsum.photos/200/400"));
        list.add(new Photo("https://picsum.photos/200/300"));
        list.add(new Photo("https://picsum.photos/200/300"));
        list.add(new Photo("https://picsum.photos/200/400"));

        return list;
    }

    private void setTypeDisplay(int type) {
        if (mList == null || mList.isEmpty()) {
            return;
        }
        currentType = type;
        for (Photo photo : mList) {
            photo.setType(type);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_list_type, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu_list_type) {
            setCurrentItemPosition();
            onclickChangeTypeDisplay();
        } else {
            super.onBackPressed();
        }
        return true;
    }

    private void onclickChangeTypeDisplay() {
        if (currentType == Photo.TYPE_GRID) {
            rcvImages.setLayoutManager(new LinearLayoutManager(this));
            setTypeDisplay(Photo.TYPE_LIST);
        } else if (currentType == Photo.TYPE_LIST) {
            rcvImages.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            setTypeDisplay(Photo.TYPE_STAGGERED);

        } else {
            rcvImages.setLayoutManager(new GridLayoutManager(this, 2));
            setTypeDisplay(Photo.TYPE_GRID);
        }

        adapter.notifyDataSetChanged();
        setIconMenu();
        rcvImages.scrollToPosition(mCurrentItemPosition);
    }

    private void setIconMenu() {
        if (currentType == Photo.TYPE_GRID) {
            mMenu.getItem(0).setIcon(R.drawable.ic_baseline_grid_on_24);
        } else if (currentType == Photo.TYPE_LIST) {
            mMenu.getItem(0).setIcon(R.drawable.ic_baseline_view_list_24);
        } else {
            mMenu.getItem(0).setIcon(R.drawable.ic_baseline_list_alt_24);
        }
    }

    public void setCurrentItemPosition() {
        RecyclerView.LayoutManager manager = rcvImages.getLayoutManager();
        if (currentType == Photo.TYPE_GRID) {
            if (((GridLayoutManager) manager) != null) {
                // findFirstVisibleItemPosition: item top/left on screen, not item first of list
                mCurrentItemPosition = ((GridLayoutManager) manager).findFirstVisibleItemPosition();
            }
        } else if (currentType == Photo.TYPE_LIST) {
            if (((LinearLayoutManager) manager) != null) {
                // findFirstVisibleItemPosition: item top/left on screen, not item first of list
                mCurrentItemPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
            }
        } else {
            if (((StaggeredGridLayoutManager) manager) != null) {
                int[] tempPosition = null;
                tempPosition = ((StaggeredGridLayoutManager) manager).findFirstVisibleItemPositions(tempPosition);
                mCurrentItemPosition = tempPosition[0];
            }
        }
    }
}