/*
 *
 *  Created by Dmitry Garmyshev on 7/21/19 8:23 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/21/19 8:23 PM
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

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogStateCheck;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerCalls;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.customer.OnSomeEventListener;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.services.ServiceAlarmState;

/*
Данное активити используется для "пациента"
Имплементируется   FragmentCustomerMain.onSomeEventListener для того,
что-бы иметь связь с отображаемым фрагментом, и что-бы мы могли выполнять
системные методы из активности
*/


public class ActivityCustomer extends AppCompatActivity implements
        OnSomeEventListener,
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
        setFirstFragment();
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
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }
    @Override
    public void initializeList(){
    }






    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFirstFragment(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.add(R.id.fragContNeedy, fragmentMain);
        }
        else{
            fragmentTransaction.add(R.id.fragContNeedy, fragmentCalls);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }





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
        boolean extraCheckState = getIntent().
                getBooleanExtra("check_state", false);

        checkState = extraCheckState;
        if(checkState){
            showCheckStateWindow();
        }

    }







    @Override
    public void sendSos() {}
    @Override
    public void sendHelpSignal(int type) {}






    private void checkSignals(){
        int type = getIntent().
                getIntExtra("signal type", 100);

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
