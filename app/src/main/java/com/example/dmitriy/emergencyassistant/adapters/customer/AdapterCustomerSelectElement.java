/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.customer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.List;

public class AdapterCustomerSelectElement extends RecyclerView.Adapter<AdapterCustomerSelectElement.ViewHolder> {


    //Объект интерфейса
    private CallBackButtons callback;


    //Базовые элементы для работы адаптера
    private List<ElementStateSelect> mData;
    private LayoutInflater mInflater;




    // Данные для конструктора
    public AdapterCustomerSelectElement(
            Context context, List<ElementStateSelect> data,
            CallBackButtons callback) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback=callback;
    }




    // Поиск элемента который будет располагаться в списке
    @Override
    public AdapterCustomerSelectElement.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_statevariant, parent, false);
        return new AdapterCustomerSelectElement.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ElementStateSelect element = mData.get(i);
        viewHolder.text.setText(element.getText());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Информация о элементе который будет держаться в списке
    //Данные самого элемента
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout lout;

        ViewHolder(final View itemView) {
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.lin_StateZone:
                            callback.select(mData.get(getLayoutPosition()));
                            break;
                    }
                }
            };

            text=itemView.findViewById(R.id.tv_StateSelect);
            lout=itemView.findViewById(R.id.lin_StateZone);
            lout.setOnClickListener(oclBtn);

        }
    }



    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void select(ElementStateSelect relative);
    }

}
