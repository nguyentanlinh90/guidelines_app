package com.ntl.guidelinesapp.modules.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class StickyHeadersAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<User> mList;

    public void setData(List<User> list) {
        this.mList = list;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        if (convertView == null) {
            headerViewHolder = new HeaderViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_user, parent, false);
            headerViewHolder.tvHeader = convertView.findViewById(R.id.tv_type_user);
            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        headerViewHolder.tvHeader.setText(mList.get(position).getName().substring(0, 1));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //to get first letter. EX: Linh => L
        return mList.get(position).getName().subSequence(0, 1).charAt(0);
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder;
        if (convertView == null) {
            itemViewHolder = new ItemViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_user, parent, false);
            itemViewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
            itemViewHolder.tvName.setText(mList.get(position).getName());
        }
        return convertView;
    }

    public class HeaderViewHolder {
        private TextView tvHeader;
    }

    public class ItemViewHolder {
        private TextView tvName;

    }
}
