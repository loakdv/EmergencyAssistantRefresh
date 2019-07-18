/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 4:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.volunteer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.elements.ElementVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class AdapterVolunteerForSelect extends RecyclerView.Adapter<AdapterVolunteerForSelect.ViewHolder> {

    private ElementVolunteerForSelect volunteerForSelect;
    private CallbackButton callbackButton;

    private List<ElementVolunteerForSelect> mData;
    private LayoutInflater layoutInflater;



    public AdapterVolunteerForSelect(Context context,
                                     List<ElementVolunteerForSelect> data, CallbackButton callback) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callbackButton = callback;
    }

    @NonNull
    @Override
    public AdapterVolunteerForSelect.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.element_volunteer_for_select, viewGroup, false);
        return new AdapterVolunteerForSelect.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterVolunteerForSelect.ViewHolder viewHolder, int i) {
        volunteerForSelect = mData.get(i);
        viewHolder.tvAbout.setText(volunteerForSelect.getReview());
        viewHolder.tvInitials.setText(volunteerForSelect.getInitials());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvInitials;
        TextView tvAbout;
        Button btnSelect;

        ViewHolder(final View itemView){
            super(itemView);

            View.OnClickListener oclBtn = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_Select_Volunteer:
                            callbackButton.selectVolunteer(mData.get(getLayoutPosition()).getId());
                            break;
                    }
                }
            };
            tvInitials = itemView.findViewById(R.id.tv_Vol_Initials);
            tvAbout = itemView.findViewById(R.id.tv_Vol_Review);
            btnSelect = itemView.findViewById(R.id.btn_Select_Volunteer);
            btnSelect.setOnClickListener(oclBtn);
        }
    }


    public interface CallbackButton{
        void selectVolunteer(String id);
    }

}
