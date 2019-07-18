/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 12:47 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceOnCustomerSelected;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент который отображает у соц. работника список Needy
 */

@SuppressLint("ValidFragment")
public class FragmentVolunteerCustomersList extends Fragment implements
        AdapterVolunteerNeedyList.CallBackButtons,
        InterfaceDataBaseWork,
        InterfaceInitialize{

    //Элементы нужные для списка
    private RecyclerView rvNeedyList;
    private AdapterVolunteerNeedyList adapterVolunteerNeedyList;
    private List<User> needyList;
    private ProgressBar pbLoading;
    private  View v;

    private String calendarDate;
    //Если фрагмент на экране - true, и наоборот
    private boolean isTasksOpened;

    private InterfaceOnCustomerSelected OnCustomerSelected;
    private DataBaseAppDatabase dataBase;






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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_volunteer_needy_list, container, false);
        initializeScreenElements();
        initializeDataBase();
        initializeList();
        return v;
    }

    @Override
    public void initializeScreenElements() {
        isTasksOpened = true;
        rvNeedyList=v.findViewById(R.id.rv_Volunteer_Needys);
        pbLoading = v.findViewById(R.id.pbVNL);
    }

    //Инициализируем локальную БД
    @Override
    public void initializeDataBase(){
        /*
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
         */
    }

    //Инициализируем список для отображения
    //Сразу же обновляем объект самого списка (RV)
    @Override
    public void initializeList(/*String date, String needyId*/){
        needyList = new ArrayList<>();
        needyList.add(new User());
        needyList.add(new User());
        needyList.add(new User());

        initializeRecycleView();
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();
    }





    private void initializeRecycleView(){
        //Переменная нужна для того, что-бы не вызывалось обновление если нету элемента на экране
        if(isTasksOpened){
            adapterVolunteerNeedyList=new AdapterVolunteerNeedyList(getContext(), needyList, this);
            rvNeedyList.setAdapter(adapterVolunteerNeedyList);
            rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }









    @Override
    public void setTask(User needy) {
        isTasksOpened=false;
        OnCustomerSelected.onCustomerClick(new EntityVolunteerAddedNeedy(), "Date");
    }





    //Async для загрузки юзеров с сервера
    class LoadingAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkService.getInstance().
                    getUserApi().
                    getUsers().
                    enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            needyList = response.body();
                            initializeRecycleView();
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Не удалось загрузить данные", Toast.LENGTH_SHORT);
                        }
                    });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbLoading.setVisibility(View.GONE);

        }
    }






}
