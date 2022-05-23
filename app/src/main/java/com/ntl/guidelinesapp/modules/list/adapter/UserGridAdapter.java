package com.ntl.guidelinesapp.modules.list.adapter;

import android.annotation.SuppressLint;
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

public class UserGridAdapter extends RecyclerView.Adapter<UserGridAdapter.UserViewHolder> {
    private List<User> mList;
    private final int widthScreen;

    public UserGridAdapter(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<User> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mList.get(position);
        if (user != null) {
            holder.ivAvatar.setImageResource(user.getImgResource());
            //use for StaggeredGridActivity
            if (position % 2 == 0) {
                holder.ivAvatar.getLayoutParams().height = widthScreen;
            } else {
                holder.ivAvatar.getLayoutParams().height = widthScreen / 3;
            }
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

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            //todo will comment bellow code when open StaggeredGridActivity
//            ivAvatar.getLayoutParams().height = widthScreen;
            ivAvatar.getLayoutParams().width = widthScreen;
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
