/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:58 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.customer.EntityCustomerConnectedVolunteer;

/*
Активность которая показывает информацию о соц. обслуживании
 */

public class ActivityDialogSocialInfo extends AppCompatActivity implements
        InterfaceDataBaseWork,
        InterfaceInitialize {

    //Поля для отображения информации о соц. работнике
    private TextView tvName,
            tvSurname,
            tvMiddlename,
            tvOrganization,
            tvVolID;


    private Button
            btnBack,
            btnDisconnect;


    //Локальная база данных
    private DataBaseAppDatabase dataBase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy_social_info);
        initializeScreenElements();
        initializeDataBase();
        setInfo();
    }



    @Override
    public void initializeScreenElements() {

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_SeeSocialBack:
                        finish();
                        break;
                    case R.id.btn_SeeSocialDisconnect:
                        disconnectUser();
                        break;
                }
            }
        };

        tvName=findViewById(R.id.tv_VolunteerName);
        tvSurname=findViewById(R.id.tv_VolunteerSurname);
        tvMiddlename=findViewById(R.id.tv_VolunteerMiddlename);
        tvOrganization=findViewById(R.id.tv_VolunteerOrganization);
        tvVolID=findViewById(R.id.tv_Needy_VolunteerID);

        btnBack=findViewById(R.id.btn_SeeSocialBack);
        btnBack.setOnClickListener(oclBtn);

        btnDisconnect=findViewById(R.id.btn_SeeSocialDisconnect);
        btnDisconnect.setOnClickListener(oclBtn);

    }



    //Метод который инициализирует базу данных
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }

    @Override
    public void initializeList() {}


    /*
    Устанавливаем полям текста данные, полученные из БД
     */
    private void setInfo(){
        /*
        EntityCustomerConnectedVolunteer volunteer=dataBase.dao_needy_volunteer().getVolunteer();
        tvName.setText(volunteer.getName());
        tvSurname.setText(volunteer.getSurname());
        tvMiddlename.setText(volunteer.getMiddlename());
        tvOrganization.setText(volunteer.getOrganization());
        tvVolID.setText(volunteer.getId());
         */
    }

    //Метод который отключает пользователя
    private void disconnectUser(){
        /*
        dataBase.dao_needy_volunteer().delete(dataBase.dao_needy_volunteer().getVolunteer());
         */
        finish();
    }
}
