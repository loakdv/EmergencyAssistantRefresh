/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.elements.ElementOrganization;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.organization.Organization;

import java.util.List;

public class AdapterLoginOrganizations extends RecyclerView.Adapter<AdapterLoginOrganizations.ViewHolder> {



    private List<Organization> mData;
    private LayoutInflater mInflater;


    //Конструктор для адаптера
    public AdapterLoginOrganizations(Context context, List<Organization> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }




    //Получаем элемент содержимого
    @NonNull
    @Override
    public AdapterLoginOrganizations.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.element_organization,
                viewGroup, false );

        return new AdapterLoginOrganizations.ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull AdapterLoginOrganizations.ViewHolder viewHolder, int position) {

        Organization organization = mData.get(position);

        String phoneNumber = "";
        String otherContacts = "";

        StringBuilder sbPhone = new StringBuilder(phoneNumber);
        StringBuilder sbOtherContacts = new StringBuilder(otherContacts);

        for(int i = 0; i < organization.getPhone().size(); i++){
            sbPhone.append(organization.getPhone().get(i) + "\n");
        }

        for(int i = 0; i < organization.getOtherContacts().size(); i++){
            sbOtherContacts.append(organization.getOtherContacts().get(i)+"\n");
        }


        viewHolder.tvNumber.setText(sbPhone.toString());
        viewHolder.tvSite.setText(sbOtherContacts.toString());
        viewHolder.tvName.setText(organization.getName());
    }




    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvName;
        TextView tvSite;
        TextView tvNumber;

        ViewHolder(final View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_OrganizationName);
            tvSite = itemView.findViewById(R.id.tv_OrganizationSite);
            tvNumber = itemView.findViewById(R.id.tv_OrganizationPhone);
        }
    }
}
