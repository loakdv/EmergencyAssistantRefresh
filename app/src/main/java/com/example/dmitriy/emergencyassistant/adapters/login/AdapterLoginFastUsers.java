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
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.elements.ElementFastUser;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class AdapterLoginFastUsers extends RecyclerView.Adapter<AdapterLoginFastUsers.ViewHolder> {

    /*
    Базовые элементы для работы адаптера
     */
    private List<ElementFastUser> data;
    private LayoutInflater mLayoutInflater;

    /*
    Интерфейс необходимый для связи фрагмента/активности и адаптера
     */
    public interface CallBack{
        void onUserSelected(ElementFastUser elementFastUser);
    }

    private CallBack callBack;


    public AdapterLoginFastUsers(Context context, List<ElementFastUser> data, CallBack callBack){
        this.mLayoutInflater=LayoutInflater.from(context);
        this.data = data;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public AdapterLoginFastUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.element_fastuser, viewGroup, false);
        return new AdapterLoginFastUsers.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterLoginFastUsers.ViewHolder viewHolder, int i) {
        ElementFastUser user = data.get(i);
        viewHolder.tvInitials.setText(user.getInitials());
        viewHolder.tvType.setText(user.getType());
        viewHolder.tvPassword.setText(user.getPassword());
        viewHolder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvInitials, tvEmail, tvPassword, tvType;
        Button btnSelect;

        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_ConfirmFastUser:
                            callBack.onUserSelected(data.get(getLayoutPosition()));
                            break;

                    }
                }
            };

            tvInitials = itemView.findViewById(R.id.tv_FastUserInitials);
            tvEmail = itemView.findViewById(R.id.tv_FastUserEmail);
            tvPassword = itemView.findViewById(R.id.tv_FastUserPassword);
            tvType = itemView.findViewById(R.id.tv_FastUserType);

            btnSelect = itemView.findViewById(R.id.btn_ConfirmFastUser);
            btnSelect.setOnClickListener(oclBtn);


        }
    }
}
