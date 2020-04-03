/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/30/19 3:27 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.fragments.customer.settings.BlockInitials;
import com.example.dmitriy.emergencyassistant.fragments.customer.settings.BlockLists;
import com.example.dmitriy.emergencyassistant.fragments.customer.settings.BlockNavigation;
import com.example.dmitriy.emergencyassistant.fragments.customer.settings.BlockNone;
import com.example.dmitriy.emergencyassistant.fragments.customer.settings.BlockState;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

/*
Активность для настроек Customer
Имплементируется  FragmentCustomerSettings.InterfaceNeedySettings для того,
что-бы иметь связь с отображаемым фрагментом
 */

public class ActivityCustomerSettings extends AppCompatActivity implements
        InterfaceDataBaseWork {



    //Локальная база данных приложения
    private DataBaseAppDatabase dataBase;


    //Fragments
    private BlockInitials blockInitials;
    private BlockLists blockLists;
    private BlockNavigation blockNavigation;
    private BlockState blockState;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needysettings);
        initializeDataBase();
        initializeFragments();
        setInitials();
        setLists();
        setState();
        setNavigation();
    }




    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(), DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().
                build();
    }





    //В этом отдельном методе инициализируются фрагменты
    private void initializeFragments(){
        blockInitials = new BlockInitials();
        blockLists = new BlockLists();
        blockNavigation = new BlockNavigation();
        blockState = new BlockState();

    }



    //Метод для установки фрагмента в зависимости от загруженных данных
    private void setInitials(){
        blockInitials = new BlockInitials();
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_cSettings_initials, blockInitials);
        fTran.commit();
    }

    private void setLists(){
        blockLists = new BlockLists();
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_cSettings_lists, blockLists);
        fTran.commit();
    }

    private void setNavigation(){
        blockNavigation = new BlockNavigation();
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_cSettings_navigation, blockNavigation);
        fTran.commit();
    }


    private void setState(){
        blockState = new BlockState();
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_cSettings_state, blockState);
        fTran.commit();
    }




    //Метод для запуска сервиса опроса состояния
    public void startService() {
        //startService(new Intent(this, ServiceAlarmState.class));

    }



}
