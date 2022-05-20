package com.ntl.guidelinesapp.general.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.General;

import java.util.List;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder> {
    private List<General> mList;
    private IOnClickItemGeneral iOnClickItemGeneral;

    public interface IOnClickItemGeneral {
        void onClickItemGeneral(General general);
    }

    public GeneralAdapter(List<General> mList, IOnClickItemGeneral iOnClickItemGeneral) {
        this.mList = mList;
        this.iOnClickItemGeneral = iOnClickItemGeneral;
    }

    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_general, parent, false);
        return new GeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {
        General general = mList.get(position);
        if (general != null) {
            holder.tvGeneral.setText(general.getName());
            holder.cvGeneral.setOnClickListener(v -> iOnClickItemGeneral.onClickItemGeneral(general));
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class GeneralViewHolder extends RecyclerView.ViewHolder {
        private CardView cvGeneral;
        private TextView tvGeneral;

        public GeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            cvGeneral = itemView.findViewById(R.id.cv_general);
            tvGeneral = itemView.findViewById(R.id.tv_general);
        }
    }
}
