/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
Данный класс нужен для того, что бы определить какую активность запускать
    на основе загруженных данных
*/

public class ActivityMain extends AppCompatActivity implements InterfaceDataBaseWork {

    //Локальная БД
    private DataBaseAppDatabase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDataBase();
        checkUser();
        startVolunteer();
    }



    //Метод для инициализации БД
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }

    //Ненужный метод, он просто находится в интерфейсе для других случаев
    //ПОРАБОТАТЬ НАД ЭТИМ!
    @Override
    public void initializeList() { }


    /*
    Выполняется проверка текущего пользователя в системе
    Если не залогинен - переходит в раздел логина/регистрации
    Если залогинен то провыверяется уже тип пользоватеоля
    и открывается нужный раздел
     */
    private void checkUser(){
        //!Тут должен быть метод который позволяет проверить данные о юзере!
        startNextActivity();
    }



    /*
    Метод вызывается после проверки юзера (checkUser())
     */
    /*
   0 - Needy
   1 - Relative
   2 - Volunteer
    */
    private void startNextActivity(){ }


    //Временный метод для быстрого переключения в раздел соц. работника
    private void startVolunteer(){
        Intent i = new Intent(this, ActivityVolunteer.class);
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
