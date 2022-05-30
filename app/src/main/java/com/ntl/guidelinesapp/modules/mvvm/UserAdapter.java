package com.ntl.guidelinesapp.modules.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemViewHolder> {
    private List<User> mList;

    public UserAdapter(List<User> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_user, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User user = mList.get(position);
        if (user != null) {
            holder.tvName.setText(user.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
