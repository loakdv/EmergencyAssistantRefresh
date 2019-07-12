/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
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

import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class AdapterVolunteerAddedNeedy extends RecyclerView.Adapter<AdapterVolunteerAddedNeedy.ViewHolder> {


    //private EntityRelativeAddedNeedy needy;

    //Объект интерфейса
    private CallBackButtons callback;

    private List<String> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public AdapterVolunteerAddedNeedy(
            Context context, List<String> data,
            CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public AdapterVolunteerAddedNeedy.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.element_needy, parent, false);

        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(
            @NonNull AdapterVolunteerAddedNeedy.ViewHolder viewHolder,
            int position){

    }




    // Общее количество элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, surname, middlename;
        Button btnSelect;
        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_SelectNeedy:

                            break;
                    }
                }
            };
            id=itemView.findViewById(R.id.tv_NeedyListId);
            name=itemView.findViewById(R.id.tv_SeeNedy_Name);
            surname=itemView.findViewById(R.id.tv_SeeNeedy_Surname);
            middlename=itemView.findViewById(R.id.tv_SeeNedy_Middlename);
            btnSelect=itemView.findViewById(R.id.btn_SelectNeedy);
            btnSelect.setOnClickListener(oclBtn);
        }
    }



    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{

    }




}
