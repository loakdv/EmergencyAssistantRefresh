/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:32 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:26 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerSettings;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerSettingsNone;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

/*
Активность для настроек Customer
Имплементируется  FragmentCustomerSettings.InterfaceNeedySettings для того,
что-бы иметь связь с отображаемым фрагментом
 */

public class
ActivityCustomerSettings extends AppCompatActivity implements
        FragmentCustomerSettings.InterfaceNeedySettings,
        InterfaceDataBaseWork {

    //Локальная база данных приложения
    private DataBaseAppDatabase dataBase;

    //Фрагменты используемые в этой активности
    private FragmentCustomerSettings fragmentCustomerSettings;
    private FragmentCustomerSettingsNone fragmentNone;
    private FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needysettings);
        initializeDataBase();
        initializeFragments();
        setFragment();
    }



    //Метод инициализации БД
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }

    //Ненужный метод, он просто находится в интерфейсе
    //Необходим для случаев со списками
    @Override
    public void initializeList() {
    }



    //В этом отдельном методе инициализируются фрагменты
    private void initializeFragments(){
        fragmentCustomerSettings = new FragmentCustomerSettings();
        fragmentNone = new FragmentCustomerSettingsNone(); }


    //Метод для установки фрагмента в зависимости от загруженных данных
    private void setFragment(){
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameNeedySettings, fragmentCustomerSettings);

        /*
        if(dataBase.dao_user() != null){
        }
        else {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentNone);
        }
         */

        fragmentTransaction.commit();
    }



    //Метод для запуска сервиса опроса состояния
    @Override
    public void startService() {
        //startService(new Intent(this, ServiceAlarmState.class));

    }



}
