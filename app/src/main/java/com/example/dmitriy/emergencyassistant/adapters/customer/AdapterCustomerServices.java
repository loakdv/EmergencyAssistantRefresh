/*
 *
 *  Created by Dmitry Garmyshev on 27.11.19 21:20
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 27.11.19 21:20
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.customer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.SocialService;

import java.util.List;

public class AdapterCustomerServices extends RecyclerView.Adapter<AdapterCustomerServices.ViewHolder> {

    private LayoutInflater mInflater;
    private List<SocialService> mData;
    private OnSelectItem onSelectItem;

    public AdapterCustomerServices(Context context, List<SocialService> socialServices, OnSelectItem onSelectItem){
        this.mInflater = LayoutInflater.from(context);
        this.mData = socialServices;
        this.onSelectItem = onSelectItem;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.element_service, viewGroup, false);
        return new AdapterCustomerServices.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SocialService socialService = mData.get(i);
        if (socialService.getTitle() != null){
            viewHolder.textView.setText(socialService.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.tvElementServiceTask:

                            onSelectItem.onSelectItem(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };

            textView = itemView.findViewById(R.id.tvElementServiceTask);
            textView.setOnClickListener(oclBtn);
        }
    }

    public interface OnSelectItem{
        void onSelectItem(SocialService socialService);
    }
}
