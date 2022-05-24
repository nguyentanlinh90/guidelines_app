package com.ntl.guidelinesapp.modules.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.TypeUser;

import java.util.List;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ListDataViewHolder> {
    private Context mContext;
    private List<TypeUser> mTypeUserList;

    public void setData(Context context, List<TypeUser> list) {
        this.mContext = context;
        this.mTypeUserList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_data, parent, false);
        return new ListDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDataViewHolder holder, int position) {
        TypeUser typeUser = mTypeUserList.get(position);
        if (typeUser != null) {
            if (holder.getItemViewType() == 1) {
                holder.rcvListUsers.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                GridAdapter adapter = new GridAdapter();
                holder.rcvListUsers.setAdapter(adapter);
                adapter.setData(typeUser.getList());

            } else {
                holder.rcvListUsers.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                VerticalAdapter adapter = new VerticalAdapter();
                holder.rcvListUsers.setAdapter(adapter);
                adapter.setData(typeUser.getList());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mTypeUserList != null) {
            return mTypeUserList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(mTypeUserList.get(position).getType());
    }

    public class ListDataViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcvListUsers;

        public ListDataViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvListUsers = itemView.findViewById(R.id.rcv_list_users);
        }
    }

}
