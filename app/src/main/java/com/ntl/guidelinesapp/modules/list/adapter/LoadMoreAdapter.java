package com.ntl.guidelinesapp.modules.list.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_ITEM = 1;
    private int TYPE_LOADING = 2;


    private List<User> mList;
    private boolean isLoadingAdd;

    public LoadMoreAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<User> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null && position == mList.size() - 1 && isLoadingAdd) {
            return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_user, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            User user = mList.get(position);
            if (user != null) {
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.ivAvatar.setImageResource(user.getImgResource());
                userViewHolder.tvName.setText(user.getName() + " => " + (position + 1));
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

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        private ProgressBar pbLoading;

        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            pbLoading = itemView.findViewById(R.id.pb_loading);
        }
    }

    public void addFooterLoading() {
        isLoadingAdd = true;
        User user = new User(R.drawable.dog_image, "");
    }

    public void removeFooterLoading() {
        isLoadingAdd = false;
        int position = mList.size() - 1;
        User user = mList.get(position);
        if (user != null) {
            mList.remove(position);
            notifyItemChanged(position);
        }
    }
}
