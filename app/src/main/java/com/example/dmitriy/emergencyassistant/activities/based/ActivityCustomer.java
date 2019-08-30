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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogStateCheck;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerCalls;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialServiceIds;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
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

    private final static String TASK_TAG = "TASK_TAG";
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





    private void initializeFragments(){
        fragmentMain = new FragmentCustomerMain();
        fragmentCalls = new FragmentCustomerCalls();
    }


    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }


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






    public void sendSos() {
        NetworkService.getInstance().getTaskApi()
                .addTaskId(new TaskSocialServiceIds("vasya",8L))
                .enqueue(new Callback<TaskSocialServiceIds>() {
                    @Override
                    public void onResponse(Call<TaskSocialServiceIds> call, Response<TaskSocialServiceIds> response) {
                        Log.d(TASK_TAG, "Response: " + response.isSuccessful());
                        Log.d(TASK_TAG, "task wrote! " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<TaskSocialServiceIds> call, Throwable t) {
                        Log.d(TASK_TAG, "task doesnt insert, " + t.getMessage().toString());

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
