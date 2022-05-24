package com.ntl.guidelinesapp.modules.list;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.modules.list.adapter.SwipeAdapter;
import com.ntl.guidelinesapp.modules.list.listener.ItemTouchHelperListener;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemTouchHelperListener listener;

    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListener itemTouchHelperListener) {
        super(dragDirs, swipeDirs);
        this.listener = itemTouchHelperListener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            listener.onSwiped(viewHolder);
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            View viewDelete = ((SwipeAdapter.SwipeViewHolder) viewHolder).llForeground;
            getDefaultUIUtil().onSelected(viewDelete);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View viewDelete = ((SwipeAdapter.SwipeViewHolder) viewHolder).llForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, viewDelete, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View viewDelete = ((SwipeAdapter.SwipeViewHolder) viewHolder).llForeground;
        getDefaultUIUtil().onDraw(c, recyclerView, viewDelete, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View viewDelete = ((SwipeAdapter.SwipeViewHolder) viewHolder).llForeground;
        getDefaultUIUtil().clearView(viewDelete);
    }
}
