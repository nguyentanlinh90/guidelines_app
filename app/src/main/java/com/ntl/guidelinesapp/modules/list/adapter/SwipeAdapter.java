package com.ntl.guidelinesapp.modules.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder> {
    private List<User> mList;

    public void setData(List<User> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe, parent, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        User user = mList.get(position);
        if (user != null) {
            holder.ivAvatar.setImageResource(user.getImgResource());
            holder.tvName.setText(user.getName());
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class SwipeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
        public LinearLayout llForeground;

        public SwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            llForeground = itemView.findViewById(R.id.ll_foreground);
        }
    }

    public void deleteUser(int index) {
        mList.remove(index);
        notifyItemChanged(index);
    }

    public void undo(User user, int index) {
        mList.add(index, user);
        notifyItemInserted(index);
    }
}
