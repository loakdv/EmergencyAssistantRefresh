/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/22/19 11:01 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

/*
Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
*/

public class ActivityMain extends AppCompatActivity implements
        InterfaceDataBaseWork,
        InterfaceInitialize {

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    //Кусок вырван из активити логина т.к. нужны будут данные из настроек этого раздела
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;


    private DataBaseAppDatabase dataBase;
    private Button
            btnLogin,
            btnNeedy,
            btnVolunteer;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDataBase();
        initializeScreenElements();
        checkUser();
    }





    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnOpenLogin:
                        startLogin();
                        break;
                    case R.id.btnOpenNeedy:
                        startNeedy();
                        break;
                    case R.id.btnOpenVolunteer:
                        startVolunteer();
                        break;
                }
            }
        };

        btnLogin = findViewById(R.id.btnOpenLogin);
        btnLogin.setOnClickListener(oclBtn);

        btnNeedy = findViewById(R.id.btnOpenNeedy);
        btnNeedy.setOnClickListener(oclBtn);

        btnVolunteer = findViewById(R.id.btnOpenVolunteer);
        btnVolunteer.setOnClickListener(oclBtn);
    }





    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(), DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().
                build();
    }






    /*
    Выполняется проверка текущего пользователя в системе
    Если не залогинен - переходит в раздел логина/регистрации
    Если залогинен то провыверяется уже тип пользоватеоля
    и открывается нужный раздел
     */
    private void checkUser(){
        getPreferences();
    }


    /*
 Этот метод выполняет свою работу при первом запуске приложения
 Если приложение запущено в первый раз - открываем окно приветствия
  */
    private void getPreferences(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString("mainUserNickname", "null");

        EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
        if(entityUser != null){
            if(entityUser.getUserRole() == UserRole.HARDUP){
                Intent i = new Intent(this, ActivityCustomer.class);
                startActivity(i);
            }
            else if(entityUser.getUserRole() == UserRole.EMPLOYEE){
                Intent i = new Intent(this, ActivityVolunteer.class);
                startActivity(i);
            }
        }

    }





    //Временный метод для быстрого переключения в раздел соц. работника
    private void startVolunteer(){
        Intent i = new Intent(this, ActivityVolunteer.class);
        startActivity(i);
    }

    private void startNeedy(){
        Intent i = new Intent(this, ActivityCustomer.class);
        startActivity(i);
    }

    private void startLogin(){
        Intent i = new Intent(this, ActivityLogin.class);
        startActivity(i);
    }





    //Эти методы нужны для повторного подключения к активности после создания профиля / входа в профиль
    @Override
    protected void onResume() {
        super.onResume();
        onStart();
    }


    @Override
    protected void onStart() {
        super.onStart();
        checkUser();
    }





}
