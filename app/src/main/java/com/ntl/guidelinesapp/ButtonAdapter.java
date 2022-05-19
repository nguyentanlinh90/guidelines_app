package com.ntl.guidelinesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {
    private List<ButtonModel> mList;

    private IClickButton iClickButton;

    public interface IClickButton {
        void onClickButton(ButtonModel buttonModel);
    }

    public void setData(List<ButtonModel> list, IClickButton iClickButton) {
        this.mList = list;
        this.iClickButton = iClickButton;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_button, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        ButtonModel buttonModel = mList.get(position);
        if (buttonModel != null) {
            holder.tvButton.setText(buttonModel.getName());
            holder.cvButton.setOnClickListener(v -> iClickButton.onClickButton(buttonModel));
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {
        private final CardView cvButton;
        private final TextView tvButton;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            cvButton = itemView.findViewById(R.id.cv_button);
            tvButton = itemView.findViewById(R.id.tv_button);
        }
    }
}
