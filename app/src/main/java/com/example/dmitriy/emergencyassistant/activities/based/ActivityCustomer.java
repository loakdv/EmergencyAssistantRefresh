/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/30/19 3:26 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogStateCheck;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerCalls;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerServiceSelect;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialServiceIds;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.services.ServiceAlarmState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Данное активити используется для "пациента"
Имплементируется   FragmentCustomerMain.onSomeEventListener для того,
что-бы иметь связь с отображаемым фрагментом, и что-бы мы могли выполнять
системные методы из активности
*/


public class ActivityCustomer extends AppCompatActivity implements
        InterfaceDataBaseWork {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    //Кусок вырван из активити логина т.к. нужны будут данные из настроек этого раздела
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    private final static String TASK_TAG = "TASK_TAG";

    //Локальная база данных приложения
    private DataBaseAppDatabase dataBase;

    //Фрагменты используемые в этой активности
    private FragmentCustomerMain fragmentMain;
    private FragmentCustomerCalls fragmentCalls;
    private FragmentCustomerServiceSelect fragmentCustomerServiceSelect;
    private FragmentTransaction fragmentTransaction;

    private EntityUser activeUser;

    //Переменная для проверки состояния
    private boolean checkState;



    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);

        initializeDataBase();
        initializeActiveUser();
        initializeFragments();

        showMainFragment();
    }

    private void initializeActiveUser(){
        String nickname = getMainNickname();
        if(!nickname.equals("null")){
            activeUser = dataBase.daoUser().getByNickname(nickname);
        }

    }


    private String getMainNickname(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString("mainUserNickname", "null");
       return mainNickname;
    }




    private void initializeFragments(){
        fragmentMain = new FragmentCustomerMain();
        fragmentCalls = new FragmentCustomerCalls();
        fragmentCustomerServiceSelect = new FragmentCustomerServiceSelect();
    }



    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }





    public void showMainFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContNeedy, fragmentMain);
        fragmentTransaction.commit();
    }


    public void showCallsFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContNeedy, fragmentCalls);
        fragmentTransaction.commit();
    }


    public void showServiceFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContNeedy, fragmentCustomerServiceSelect);
        fragmentTransaction.commit();
    }





    public void sendSos(SocialService socialService) {
        Toast.makeText(this, "Sending sos", Toast.LENGTH_SHORT).show();
        showMainFragment();
        NetworkService.getInstance().getTaskApi()
                .addTask(new TaskSocialServiceIds((Long) activeUser.getId(),57L, socialService.getId()))
                .enqueue(new Callback<TaskSocialServiceIds>() {
                    @Override
                    public void onResponse(Call<TaskSocialServiceIds> call, Response<TaskSocialServiceIds> response) {
                        Log.d(TASK_TAG, "Response: " + response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<TaskSocialServiceIds> call, Throwable t) {
                        Log.d(TASK_TAG, "task doesnt insert, " + t.getMessage());

                    }
                });
    }




    public void sendHelpSignal(int type) {

    }



    private void checkSignals(){
        int type = getIntent().
                getIntExtra("signal type", 100);

        if(!(type == 100)){
            sendHelpSignal(type);
        }
    }



   //Метод вызывыается для отображения окна выбора состояния
    public void checkState() {
        Intent state = new Intent(this,
                ActivityDialogStateCheck.class);
        startActivity(state);
    }



    private void showCheckStateWindow(){
        Intent state = new Intent(this, ActivityDialogStateCheck.class);
        startActivity(state);
        checkState = false;
    }



    private void startService(){
        startService(new Intent(this,
                ServiceAlarmState.class));
    }





}
