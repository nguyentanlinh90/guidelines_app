package com.ntl.guidelinesapp.modules.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

public class UserMultipleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_1 = 1;
    private static int TYPE_2 = 2;
    private List<User> mList;

    public UserMultipleViewAdapter() {
    }

    public void setData(List<User> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_user, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_user, parent, false);
            return new UserViewHolder_1(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = mList.get(position);
        if (holder.getItemViewType() == TYPE_1) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.ivAvatar.setImageResource(user.getImgResource());
            userViewHolder.tvName.setText(user.getName());
        } else {
            UserViewHolder_1 userViewHolder = (UserViewHolder_1) holder;
            userViewHolder.ivAvatar.setImageResource(user.getImgResource());
            userViewHolder.tvName.setText(user.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getType() == TYPE_1) {
            return TYPE_1;
        } else {
            return TYPE_2;
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
            ivAvatar.getLayoutParams().height = 200;
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public class UserViewHolder_1 extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;

        public UserViewHolder_1(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
