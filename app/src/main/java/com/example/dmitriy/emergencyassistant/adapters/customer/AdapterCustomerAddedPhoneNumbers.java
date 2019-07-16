/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:58 PM
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
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerAddedPhoneNumbers;

import java.util.List;

public class AdapterCustomerAddedPhoneNumbers extends RecyclerView.Adapter<AdapterCustomerAddedPhoneNumbers.ViewHolder> {


    /*
  Энтити из которого мы будем доставать нужные нам данные и объект БД
   */
    private EntityCustomerAddedPhoneNumbers number;
    private DataBaseAppDatabase dataBase;

    //Объект интерфейса(Сам интерфейс внизу класса)
    private CallBackButtons callback;



    //Базовые элементы для работы адаптера
    private List<EntityCustomerAddedPhoneNumbers> mData;
    private LayoutInflater mInflater;



    //Конструктор для адаптера
    public AdapterCustomerAddedPhoneNumbers(Context context,
                                            List<EntityCustomerAddedPhoneNumbers> data,
                                            CallBackButtons callback){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.callback = callback;
        initializeDataBase(context);
    }


    /*
    Инициализируем базу данных
     */
    private void initializeDataBase(Context context){
        dataBase = Room.databaseBuilder(context,
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Получаем элемент содержимого
    @NonNull
    @Override
    public AdapterCustomerAddedPhoneNumbers.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.element_phonenumber,
                viewGroup, false );
        return new ViewHolder(view);
    }




    //Устанавливаем значения элементу при присоединении
    @Override
    public void onBindViewHolder(@NonNull AdapterCustomerAddedPhoneNumbers.ViewHolder viewHolder,
                                 int position) {
        number = mData.get(position);

        //Получение данных из класса БД
        /*
        viewHolder.tvName.setText(number.getName());
        viewHolder.tvNumber.setText(number.getNumber());

        try {
            byte[] image = number.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            viewHolder.imgImageView.setImageBitmap(bmp);
        }
        catch (Exception e){}
         */
    }




    //Получить число элементов
    @Override
    public int getItemCount() {
        return mData.size();
    }




    //Класс холдера
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Элементы нужного элемента
        Button btnDeleteNumber;
        TextView tvName, tvId, tvNumber;
        ImageView imgImageView;

        ViewHolder(final View itemView){
            super(itemView);
            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteNumber:
                            callback.deleteNumber(mData.get(getLayoutPosition()));
                            break;

                    }
                }
            };


            btnDeleteNumber=itemView.findViewById(R.id.btn_DeleteNumber);
            btnDeleteNumber.setOnClickListener(oclBtn);

            tvName=itemView.findViewById(R.id.tv_NumberName);
            tvNumber=itemView.findViewById(R.id.tv_Number);

            tvId=itemView.findViewById(R.id.tv_NumberId);
            imgImageView=itemView.findViewById(R.id.imgv_PhoneNumberImage);
        }
    }


    //Интерфейс для связки этого адаптера и активности
    public interface CallBackButtons{
        //Методы удаления и изменения объекта
        void deleteNumber (EntityCustomerAddedPhoneNumbers number);
    }




}
