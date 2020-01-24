/*
 *
 *  Created by Dmitry Garmyshev on 18.11.19 16:48
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 18.11.19 16:48
 *
 */

package com.example.dmitriy.emergencyassistant.core;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityUser;

public class LoginShell {

    private Context context;

    private static final String MAIN_USER_NICKNAME = "mainUserNickname";

    //Переменные которые нужны для доступа к файлам настроек раздела авторизации
    //Кусок вырван из активити логина т.к. нужны будут данные из настроек этого раздела
    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    //База данных из которой будут браться данные
    private DataBaseAppDatabase dataBase;

    /*
    Переменная нужна для защиты, при использовании этого модуля
    Если не все объекты инициализированны, то мы не сможем выполнять важные задачи
    Это в какой-то мере спасёт приложение от случайных вылетов
     */
    private boolean isCanMakeDoSmth = false;



    public LoginShell(Context context){
        this.context = context;
        initializeDataBase(context);
    }

    private void checkModules(Context context){

    }


    //Метод инициализирующий базу данных, и возвращающий значение об успешности инициализации
    private boolean initializeDataBase(Context context){

        //Проверяем базу данных на момент инициализации
        if (dataBase == null){
            dataBase = Room.databaseBuilder(context, DataBaseAppDatabase.class, "app_database").
                    allowMainThreadQueries().
                    build();
        }

        //Проверяем базу данных после процесса инициализации
        if(dataBase != null) return true;
        else return false;
    }




    //Проверяем на то, имеется ли активный юзер, или нет
    public boolean checkCurrentUser(){
        loginPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString(MAIN_USER_NICKNAME, "null");

        if (!mainNickname.equals("null")){
            EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
            if(entityUser != null){
                return true;
            }
            return false;
        }
        else return false;
    }



    public String getCurrentUserNickname(){
        loginPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString(MAIN_USER_NICKNAME, "null");

        if (!mainNickname.equals("null")){
            EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
            if(entityUser != null){
                return mainNickname;
            }
            return null;
        }
        else return null;
    }




    public boolean checkUserByNickname(String nickname){
        if(nickname != null && !nickname.isEmpty()){
            EntityUser entityUser = dataBase.daoUser().getByNickname(nickname);
            if(entityUser != null){
                return true;
            }
            else return false;
        }
        else return false;
    }



    public EntityUser getCurrentUser(){
        loginPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString(MAIN_USER_NICKNAME, "null");

        if (!mainNickname.equals("null")){
            EntityUser entityUser = dataBase.daoUser().getByNickname(mainNickname);
            if(entityUser != null){
                return entityUser;
            }
            return null;
        }
        else return null;
    }




    public void putNewCurrentUserNickname(String nickname){
        if(dataBase.daoUser().getByNickname(nickname) != null){
            loginPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.putString(MAIN_USER_NICKNAME, nickname);
            editor.apply();
        }

    }



    public void putNewUserInDataBase(User user){

        if(dataBase.daoUser().getByNickname(user.getNickname()) == null){
            EntityUser entityUser = new EntityUser.Builder(user.getNickname(), user.getPassword())
                    .setId(user.getId())
                    .setFirstname(user.getFirstname())
                    .setMiddlename(user.getMiddlename())
                    .setLastname(user.getLastname())
                    .setUserRole(user.getRole())
                    .setAddress(user.getAddress())
                    .setEmail(user.getEmail())
                    .setMobile(user.getMobile())
                    .setPhone(user.getPhone())
                    .build();

            dataBase.daoUser().insert(entityUser);
        }

    }



}

