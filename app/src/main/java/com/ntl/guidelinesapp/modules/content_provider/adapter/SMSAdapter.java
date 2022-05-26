package com.ntl.guidelinesapp.modules.content_provider.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.content_provider.SMS;

import java.util.List;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.ItemViewHolder> {
    private List<SMS> mList;

    public SMSAdapter(List<SMS> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        SMS sms = mList.get(position);
        if (sms != null) {
            holder.tvAddress.setText(sms.getAddress());
            holder.tvDate.setText(sms.getDate());
            holder.tvBody.setText(sms.getBody());
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
        private TextView tvAddress;
        private TextView tvDate;
        private TextView tvBody;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }
}
