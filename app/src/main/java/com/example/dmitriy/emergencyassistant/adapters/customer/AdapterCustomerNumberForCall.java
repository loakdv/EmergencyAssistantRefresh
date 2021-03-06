/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 4:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.adapters.customer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityPhoneNumber;

import java.util.List;

public class AdapterCustomerNumberForCall extends RecyclerView.Adapter<AdapterCustomerNumberForCall.ViewHolder> {


    /*
   Энтити из которого мы будем доставать нужные нам данные и объект БД
    */
    private DataBaseAppDatabase dataBase;

    //Объект интерфейса(Сам интерфейс внизу)
    private CallBackButtons callback;


    //Базовые элементы для работы адаптера
    private List<EntityPhoneNumber> mData;
    private LayoutInflater mInflater;



    //Конструктор для адаптера
    public AdapterCustomerNumberForCall(
            Context context, List<EntityPhoneNumber> data,
            CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.mData=data;
        this.callback=callback;
        initializeDatabase(context);

    }

    /*
    Инициализируем базу данных
    */
    private void initializeDatabase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }




    //Получаем элемент содержимого
    @NonNull
    @Override
    public AdapterCustomerNumberForCall.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                      int i) {
        View view=mInflater.inflate(R.layout.element_callnumber, viewGroup, false );
        return new AdapterCustomerNumberForCall.ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull AdapterCustomerNumberForCall.ViewHolder viewHolder,
                                 int position) {
        EntityPhoneNumber number = mData.get(position);

        //Получение данных из класса БД
        viewHolder.tvName.setText(number.getName());

    }




    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Элементы нужного элемента
        Button btnCall;
        ImageView imgCall;
        TextView tvName;


        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_CallNumber:
                            callback.call(mData.get(getLayoutPosition()));
                            break;

                    }
                }
            };

            btnCall=itemView.findViewById(R.id.btn_CallNumber);
            btnCall.setOnClickListener(oclBtn);

            imgCall=itemView.findViewById(R.id.img_CallImage);
            tvName=itemView.findViewById(R.id.tv_CallName);

        }
    }


    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        void call(EntityPhoneNumber number);
    }


}
