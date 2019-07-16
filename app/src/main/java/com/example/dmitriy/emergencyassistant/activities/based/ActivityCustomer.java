/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:16 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogSendSignal;
import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogStateCheck;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerCalls;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.services.ServiceAlarmState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
Данное активити используется для "пациента"
Имплементируется   FragmentCustomerMain.onSomeEventListener для того,
что-бы иметь связь с отображаемым фрагментом, и что-бы мы могли выполнять
системные методы из активности
*/


public class ActivityCustomer extends AppCompatActivity implements
        FragmentCustomerMain.onSomeEventListener,
        InterfaceDataBaseWork {

    //Локальная база данных приложения
    private DataBaseAppDatabase dataBase;

    //Фрагменты используемые в этой активности
    private FragmentCustomerMain fragmentMain;
    private FragmentCustomerCalls fragmentCalls;
    private FragmentTransaction fragmentTransaction;

    //Переменная для смены фрагмента
    //На данный момент хватает её
    private boolean main = true;

    //Переменная для проверки состояния
    private boolean checkState;



    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);
        //initializeDataBase();
        initializeFragments();

        //Вызываем этот метод здесь, что-бы сразу заполнить лист имеющимися данными
        initializeList();
        setFragment();
        getFromIntent();
    }



    //Метод инициализирующий объекты фрагментов
    private void initializeFragments(){
        fragmentMain = new FragmentCustomerMain();
        fragmentCalls = new FragmentCustomerCalls();
    }



    //Инициализация базы данных
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }



    /*
    Данный лист нужен для получения пользователей доступных
    для отправки им сигнала
     */
    @Override
    public void initializeList(){
        /*
        if(!(dataBase.dao_added_relatives().getAll() == null)){
            users=dataBase.dao_added_relatives().getByDoc(false);
        }
         */
    }



    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        /*
        Проверяем переменную main
        если true, то ставим гланый экран
         */
        if(main){
            fragmentTransaction.add(R.id.fragContNeedy, fragmentMain); }
        //Если false, то ставим экран звонков
        else{
            fragmentTransaction.add(R.id.fragContNeedy, fragmentCalls); }

        /*
        Добавляем в бэкстэк для того что бы можно
        было вернуться к фрагменту после нажатия
        физической кнопки "назад"
         */
        fragmentTransaction.addToBackStack(null);
        //Применяем транзакцию
        fragmentTransaction.commit();

    }



    /*
    Метод используемый для смены фрагмента во время
    работы самой активности
    Сначала он ставит противоположное значение переменной main
    и уже от этой переменной отталкивается в дальнейшем
    (Происходит почти тоже самое что в методе setFragment, только
    используется replace)
     */

    /*
    Методы интерфейса, нужны что бы можно было связаться с этой активностью из фрагментов
    Как только фрагмент использует эвент, то он переходит к этой активности
    и тут уже срабатывают нужные методы;
     */
    @Override
    public void changeFrag() {
        main = !main;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentCalls);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    //Метод для получения значения из интента, что бы открыть окно с выбором состояния
    private void getFromIntent(){

        //Получаем из интента передаваемое значение
        boolean extraCheckState = getIntent().getBooleanExtra("check_state",
                false);

        /*
        Т.к. напрямую к полученному значению мы обратиться не можем,
        инициализируем переменную которая находится в поле класса
        Такой подход так-же может помочь если нужно будет получить
        значение переменной из другой части кода
         */
        checkState = extraCheckState;

        /*
        Если значение равно true - значит надо проверить состояние пользователя
        => показываем окно с выбором состояния
         */
        if(checkState){
            startCheckState();
        }

    }




    /*
   Достаются загруженные данные из класса Needy
   Описание сигналов есть в этом классе
   В зависимости от выбранного значения, происходят разные события
    */
    @Override
    public void sendSos() {
        sendSignalSosToUsers();
    }


    //Перебираем список пользователей кому можно отправлять сигнал о помощи
    private void sendSignalSosToUsers(){}


    //Метод который отправляет сигнал о помощи соц. работникам
    @Override
    public void sendHelpSignal(int type) {}




    private void checkSignals(){
        int type = getIntent().getIntExtra("signal type", 100);
        if(!(type == 100)){
            sendHelpSignal(type);
        }
    }



   //Метод вызывыается для отображения окна выбора состояния
    @Override
    public void checkState() {
        Intent state = new Intent(this,
                ActivityDialogStateCheck.class);
        startActivity(state);
    }



    //Метод запускает сервис проверки состояния
    private void startCheckState(){
        Intent state = new Intent(this, ActivityDialogStateCheck.class);
        startActivity(state);
        checkState = false;
    }



    private void startService(){
        startService(new Intent(this,
                ServiceAlarmState.class));
    }





}
