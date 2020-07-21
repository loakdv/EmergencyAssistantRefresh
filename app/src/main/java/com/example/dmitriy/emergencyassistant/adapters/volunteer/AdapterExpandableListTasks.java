/*
 *
 *  Created by Dmitry Garmyshev on 20.07.20 12:16
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 20.07.20 12:16
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.volunteer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.SocialServiceCatalog;

import java.util.ArrayList;
import java.util.List;

public class AdapterExpandableListTasks extends BaseExpandableListAdapter {

    private List<SocialServiceCatalog> mGroups;
    private Context mContext;


    public AdapterExpandableListTasks(Context context, List<SocialServiceCatalog> list){
        this.mContext = context;
        this.mGroups = list;
    }


    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int i){
       return mGroups.get(i).getSocialService().size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mGroups.get(i).getSocialService().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.element_volunteer_task_parent, null);

        TextView tvTitle = view.findViewById(R.id.tv_Task_Group_Parent_Name);
        tvTitle.setText(mGroups.get(i).getTitle());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.element_volunteer_task, null);

        TextView tvTitle = view.findViewById(R.id.tv_TaskName);
        tvTitle.setText(mGroups.get(i).getSocialService().get(i1).getTitle());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
