/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
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
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
Активность для настроек Customer
 */

public class ActivityCustomerSettings extends AppCompatActivity implements
        FragmentCustomerSettings.InterfaceNeedySettings,
        InterfaceDataBaseWork {

    /*
    Локальная база данных приложения
     */
    private DataBaseAppDatabase dataBase;


    /*
    Фрагменты используемые в этой активности
     */
    private FragmentCustomerSettings fragmentCustomerSettings;
    private FragmentTransaction fragmentTransaction;
    private FragmentCustomerSettingsNone fragmentNone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needysettings);

        initializeDataBase();
        setFragment();
    }


    /*
    Метод инициализации БД
     */
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    @Override
    public void initializeList() {
    }

    /*
    Метод для установки фрагмента в зависимости от загруженных данных
    */
    private void setFragment(){

        fragmentCustomerSettings = new FragmentCustomerSettings();
        fragmentNone = new FragmentCustomerSettingsNone();

        if(dataBase.dao_user() != null){

            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentCustomerSettings);
        }
        else {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameNeedySettings, fragmentNone);
        }
        fragmentTransaction.commit();
    }




    /*
    Метод для запуска сервиса опроса состояния
     */
    @Override
    public void startService() {
        /*
        startService(new Intent(this, ServiceAlarmState.class));
         */
    }



}
