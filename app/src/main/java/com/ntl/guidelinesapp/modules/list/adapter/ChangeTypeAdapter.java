package com.ntl.guidelinesapp.modules.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.Photo;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

public class ChangeTypeAdapter extends RecyclerView.Adapter<ChangeTypeAdapter.ChangeTypeViewHolder> {
    private Context context;
    private List<Photo> mList;

    public ChangeTypeAdapter(Context context, List<Photo> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @NonNull
    @Override
    public ChangeTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Photo.TYPE_GRID) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_1, parent, false);
        } else if (viewType == Photo.TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered, parent, false);
        }
        return new ChangeTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeTypeViewHolder holder, int position) {
        Photo photo = mList.get(position);
        if (photo != null) {
            Glide.with(context).load(photo.getUrl()).placeholder(R.drawable.ic_baseline_image_24).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public static class ChangeTypeViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ChangeTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
