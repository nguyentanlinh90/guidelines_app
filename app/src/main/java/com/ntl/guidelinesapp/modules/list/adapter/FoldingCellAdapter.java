package com.ntl.guidelinesapp.modules.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.Item;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

public class FoldingCellAdapter extends RecyclerView.Adapter<FoldingCellAdapter.ItemViewHolder> {

    private List<Item> listItems;

    public void setData(List<Item> list) {
        this.listItems = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folding_cell_animation, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = listItems.get(position);
        if (item == null) {
            return;
        }
        holder.tvTitle.setText(item.getName());
        holder.tvContent.setText(item.getContent());

        // attach click listener to folding cell
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
            }
        });

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), item.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listItems != null && listItems.size() > 0) {
            return listItems.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private FoldingCell foldingCell;
        private TextView tvTitle;
        private TextView tvContent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foldingCell = itemView.findViewById(R.id.folding_cell);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
