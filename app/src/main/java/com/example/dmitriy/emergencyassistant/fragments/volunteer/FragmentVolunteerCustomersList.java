/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 6:55 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
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

import com.example.dmitriy.emergencyassistant.activities.based.ActivityVolunteer;
import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.model.user.UserRole;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент который отображает у соц. работника список Customer'ов
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
    private List<TaskSocialService> listTasks;
    private ProgressBar pbLoading;
    private  View v;

    private String calendarDate;
    //Если фрагмент на экране - true, и наоборот
    private boolean isTasksOpened;

    private DataBaseAppDatabase dataBase;






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
//        initializeDataBase();
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

        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();

    }


    public void initializeList(/*String date, String needyId*/){
        needyList = new ArrayList<>();
        initializeRecycleView();
        startLoading();
    }





    private void initializeRecycleView(){
        //Переменная нужна для того, что-бы не вызывалось обновление если нету элемента на экране
        if(isTasksOpened){
            adapterVolunteerNeedyList=new AdapterVolunteerNeedyList(getContext(), needyList, this);
            rvNeedyList.setAdapter(adapterVolunteerNeedyList);
            rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }




    private void startLoading(){
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();
    }

    @Override
    public void selectUser(User user) {
        //Обращаемся к основной активности для смены фрагмента
        isTasksOpened=false;
        ((ActivityVolunteer)getActivity()).onCustomerClick(user, "Date");
    }





    //Async для загрузки тасков с сервера
    class LoadingAsync extends AsyncTask<Void, Void, Void> {

        List<User> users = new ArrayList<>();
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

                            List<User> sortedList = new ArrayList<>();
                            for(int i = 0; i < needyList.size(); i++){
                                if (needyList.get(i).getRole() == UserRole.HARDUP){
                                    sortedList.add(needyList.get(i));
                                    LoadingTasksAsync loadingTasksAsync = new LoadingTasksAsync(needyList.get(i));

                                }
                            }

                            needyList = sortedList;

                            Log.d("USERS LIST", ""+needyList.size());
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





    //Async для загрузки тасков с сервера
    class LoadingTasksAsync extends AsyncTask<Void, Void, Void> {

        private User currentUser;

        public LoadingTasksAsync(User user){
            currentUser = user;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkService.getInstance().
                    getTaskApi().
                    getTaskSocialServices().enqueue(new Callback<List<TaskSocialService>>() {
                @Override
                public void onResponse(Call<List<TaskSocialService>> call, Response<List<TaskSocialService>> response) {
                    listTasks = response.body();

                    List<TaskSocialService> sortedList = new ArrayList<>();

                    for(int i = 0; i < listTasks.size(); i++){
                        if (listTasks.get(i).getNeedy().getNickname().equals(currentUser.getNickname())){
                            sortedList.add(listTasks.get(i));
                        }
                    }

                    listTasks = sortedList;
                    initializeRecycleView();

                }

                @Override
                public void onFailure(Call<List<TaskSocialService>> call, Throwable t) {

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
