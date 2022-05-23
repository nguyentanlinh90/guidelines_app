package com.ntl.guidelinesapp.modules.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.GroupObject;
import com.ntl.guidelinesapp.modules.list.model.ItemObject;

import java.util.List;
import java.util.Map;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<GroupObject> mListGroup;
    private Map<GroupObject, List<ItemObject>> mListItems;

    public ExpandableListViewAdapter(List<GroupObject> groupObjectList, Map<GroupObject, List<ItemObject>> listMap) {
        this.mListGroup = groupObjectList;
        this.mListItems = listMap;
    }

    @Override
    public int getGroupCount() {
        if (mListGroup != null) {
            return mListGroup.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mListGroup != null && mListItems != null) {
            List<ItemObject> list = mListItems.get(mListGroup.get(groupPosition));
            if (list != null) {
                return list.size();
            }
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ItemObject> list = mListItems.get(mListGroup.get(groupPosition));
        if (list != null) {
            return list.get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        GroupObject groupObject = mListGroup.get(groupPosition);
        return groupObject.getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        List<ItemObject> objects = mListItems.get(mListGroup.get(groupPosition));
        if (objects != null) {
            ItemObject itemObject = objects.get(childPosition);
            return itemObject.getId();
        }
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        }
        TextView tvGroup = convertView.findViewById(R.id.tv_group);
        GroupObject groupObject = mListGroup.get(groupPosition);
        tvGroup.setText(groupObject.getName().toUpperCase());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        }
        TextView tvText = convertView.findViewById(R.id.tv_text);
        ItemObject itemObject = mListItems.get(mListGroup.get(groupPosition)).get(childPosition);
        tvText.setText(itemObject.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
