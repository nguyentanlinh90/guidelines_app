package com.ntl.guidelinesapp.modules.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;

import java.util.List;

public class TypeUserAdapter extends RecyclerView.Adapter<TypeUserAdapter.UserViewHolder> {
    private List<TypeUser> mList;
    private Context context;
    private final int widthScreen;

    public TypeUserAdapter(Context context, int widthScreen) {
        this.context = context;
        this.widthScreen = widthScreen;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<TypeUser> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TypeUser typeUser = mList.get(position);
        if (typeUser != null) {
            holder.tvType.setText(typeUser.getType());

            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.rcvTypeUsers.setLayoutManager(manager);

            UserGridAdapter adapter = new UserGridAdapter(widthScreen);
            adapter.setData(typeUser.getList());
            holder.rcvTypeUsers.setAdapter(adapter);
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
        private TextView tvType;
        private RecyclerView rcvTypeUsers;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type_user);
            rcvTypeUsers = itemView.findViewById(R.id.rcv_users_of_type);
        }
    }
}
