package com.ntl.guidelinesapp.modules.room_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mList;
    private IClickUpdateItem iClickUpdateItem;

    public void setData(List<User> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface IClickUpdateItem {
        void updateItem(User user);

        void deleteItem(User user);
    }

    public UserAdapter(IClickUpdateItem iClickUpdateItem) {
        this.iClickUpdateItem = iClickUpdateItem;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_room_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mList.get(position);
        if (user != null) {
            holder.tvUsername.setText(user.getUsername());
            holder.tvAddress.setText(user.getAddress());
            holder.tvYear.setText(user.getYear());
            holder.tvPhone.setText(user.getPhone());
            holder.btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickUpdateItem.updateItem(user);
                }
            });
            holder.btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickUpdateItem.deleteItem(user);
                }
            });
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
        private TextView tvUsername, tvAddress, tvYear, tvPhone;
        private Button btUpdate, btDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvPhone = itemView.findViewById(R.id.tv_display_name);
            btUpdate = itemView.findViewById(R.id.bt_update);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
