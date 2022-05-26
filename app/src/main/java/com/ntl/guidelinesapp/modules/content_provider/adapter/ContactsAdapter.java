package com.ntl.guidelinesapp.modules.content_provider.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.content_provider.Contact;
import com.ntl.guidelinesapp.modules.content_provider.PhoneNumber;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ItemViewHolder> {
    private List<Contact> mList;

    public ContactsAdapter(List<Contact> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Contact contact = mList.get(position);
        if (contact != null) {
            holder.tvId.setText(contact.getId());
            holder.tvDisplayName.setText(contact.getDisplayName());

            StringBuilder strPhones = new StringBuilder();
            for (PhoneNumber number : contact.getPhoneNumbers()) {
                strPhones.append(number.getPhoneNumber()).append("\n");
            }
            holder.tvPhoneNumder.setText(strPhones.toString());
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
        private TextView tvId;
        private TextView tvDisplayName;
        private TextView tvPhoneNumder;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvDisplayName = itemView.findViewById(R.id.tv_display_name);
            tvPhoneNumder = itemView.findViewById(R.id.tv_phone_number);
        }
    }
}
