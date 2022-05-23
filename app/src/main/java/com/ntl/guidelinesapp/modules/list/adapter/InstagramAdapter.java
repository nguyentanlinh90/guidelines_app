package com.ntl.guidelinesapp.modules.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.ListPhoto;

import java.util.List;

public class InstagramAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_GRID = 2;
    private int TYPE_LARGE_LEFT = 1;
    private int TYPE_LARGE_RIGHT = 3;
    private List<ListPhoto> mList;
    private Context context;
    private String URL = "https://picsum.photos/2000/3000";

    public void setData(Context context, List<ListPhoto> list) {
        this.mList = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_GRID) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
            return new ItemGridViewHolder(view);
        } else if (viewType == TYPE_LARGE_LEFT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_large_left, parent, false);
            return new ItemLargeLeftViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_large_right, parent, false);
            return new ItemLargeRightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListPhoto listPhoto = mList.get(position);
        if (listPhoto != null) {
            if (listPhoto.getType() == TYPE_GRID) {
                ItemGridViewHolder gridViewHolder = (ItemGridViewHolder) holder;
                /*gridViewHolder.iv1.setImageResource(listPhoto.getPhotoList().get(0).getResource());
                gridViewHolder.iv2.setImageResource(listPhoto.getPhotoList().get(1).getResource());
                gridViewHolder.iv3.setImageResource(listPhoto.getPhotoList().get(2).getResource());*/
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(gridViewHolder.iv1);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(gridViewHolder.iv2);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(gridViewHolder.iv3);
            } else if (listPhoto.getType() == TYPE_LARGE_LEFT) {
                ItemLargeLeftViewHolder largeLeftViewHolder = (ItemLargeLeftViewHolder) holder;
                /*largeLeftViewHolder.iv1.setImageResource(listPhoto.getPhotoList().get(0).getResource());
                largeLeftViewHolder.iv2.setImageResource(listPhoto.getPhotoList().get(1).getResource());
                largeLeftViewHolder.iv3.setImageResource(listPhoto.getPhotoList().get(2).getResource());*/
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeLeftViewHolder.iv1);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeLeftViewHolder.iv2);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeLeftViewHolder.iv3);
            } else {
                ItemLargeRightViewHolder largeRightViewHolder = (ItemLargeRightViewHolder) holder;
                /*largeRightViewHolder.iv1.setImageResource(listPhoto.getPhotoList().get(0).getResource());
                largeRightViewHolder.iv2.setImageResource(listPhoto.getPhotoList().get(1).getResource());
                largeRightViewHolder.iv3.setImageResource(listPhoto.getPhotoList().get(2).getResource());*/
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeRightViewHolder.iv1);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeRightViewHolder.iv2);
                Glide.with(context).load(URL).placeholder(R.drawable.ic_baseline_image_24).into(largeRightViewHolder.iv3);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        ListPhoto listPhoto = mList.get(position);
        return listPhoto.getType();
    }

    public class ItemLargeLeftViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv1, iv2, iv3;

        public ItemLargeLeftViewHolder(@NonNull View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);
        }
    }

    public class ItemLargeRightViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv1, iv2, iv3;

        public ItemLargeRightViewHolder(@NonNull View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);
        }
    }

    public class ItemGridViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv1, iv2, iv3;

        public ItemGridViewHolder(@NonNull View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            iv3 = itemView.findViewById(R.id.iv_3);
        }
    }
}
