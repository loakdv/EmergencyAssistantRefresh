/*
 *
 *  Created by Dmitry Garmyshev on 7/17/19 4:29 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 11:59 AM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceOnCustomerSelected;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент который отображает у соц. работника список Needy
 */

@SuppressLint("ValidFragment")
public class FragmentVolunteerCustomersList extends Fragment implements
        AdapterVolunteerNeedyList.CallBackButtons,
        InterfaceDataBaseWork {

    //Элементы нужные для списка
    private RecyclerView rvNeedyList;
    private AdapterVolunteerNeedyList adapterVolunteerNeedyList;
    private List<User> needyList;

    //Локальная БД
    private DataBaseAppDatabase dataBase;

    //Получаем дату из интента, нужна для образения к серверу
    private String calendarDate;

    //Интерфейс для связи с основной активностью
    private InterfaceOnCustomerSelected OnCustomerSelected;


    /*
    Переменная нужна для того, что-бы не было лишних конфликтов
    Во времена использования Firebase, при любом изменении информации о тасках
    на сервере, сразу шёл сигнал на устройство юзера, и если юзер не находился
    в разделе тасков, то приложение вылетало, т.к. оно пыталось обновить
    несуществующий объект RV
    Поэтому, при открытии раздела со списком тасков, этой переменной присваивается
    значение true (экран открыт), а значит можно спокойно обновлять объект RV, и наоборот
     */
    private boolean isTasksOpened;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        OnCustomerSelected =(InterfaceOnCustomerSelected) context;
    }

    @SuppressLint("ValidFragment")
    public FragmentVolunteerCustomersList(String date){
        this.calendarDate=date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_needy_list, container, false);

        rvNeedyList=v.findViewById(R.id.rv_Volunteer_Needys);

        //Откно открыто, значит можно обновлять список
        isTasksOpened=true;

        //initializeDataBase();
        initializeList();

        //needysThred=new NeedysThred();
        //needysThred.start();



        return v;
    }

    //Инициализируем локальную БД
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }


    //Инициализируем список для отображения
    //Сразу же обновляем объект самого списка (RV)
    @Override
    public void initializeList(/*String date, String needyId*/){

        NetworkService.getInstance().
                getUserApi().
                getUsers().
                enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        Log.d("CONNECT TO SERVER: ","RESPONSE: "+response.message());
                        List<User> users = response.body();
                        needyList = response.body();
                        Log.d("CONNECT TO SERVER: ","RESPONSE: "+response.body().toString());
                        initializeRecycleView();
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("CONNECT TO SERVER: ","FAILURE: "+t.getMessage());

                    }
                });





        /*
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            needyList=dataBase.dao_volunteer_addedNeedy().getListByDate(date);
            initializeRecycleView();
        }
         */


    }


    /*
    Обновляем объект самого списка на экране
    Значение переменной  isTasksOpened описано выше
    Если юзер будет находиться не в этом разделе, и не будет производиться данная проверка,
    приложение будет вылетать
     */
    private void initializeRecycleView(){

        if(isTasksOpened){
            adapterVolunteerNeedyList=new AdapterVolunteerNeedyList(getContext(), needyList,
                    this);

            rvNeedyList.setAdapter(adapterVolunteerNeedyList);
            rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }

    @Override
    public void setTask(User needy) {
        //После входа в другой раздел, устанавливаем переменную на false, что-бы не было вылетов
        isTasksOpened=false;
       // OnCustomerSelected.onCustomerClick(needy, calendarDate);
    }





}
