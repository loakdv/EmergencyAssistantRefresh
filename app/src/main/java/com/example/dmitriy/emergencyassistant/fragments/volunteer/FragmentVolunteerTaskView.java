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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoAboutNeedy;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент который отображает список тасков на пользователя у соц. работника
 */

@SuppressLint("ValidFragment")
public class FragmentVolunteerTaskView extends Fragment implements
        AdapterVolunteerTaskList.CallBackButtons,
        InterfaceInitialize,
        InterfaceDataBaseWork{

    //Интерфейс для связи с основной активностью


    //Элементы необходимые для отображения списка
    private AdapterVolunteerTaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<TaskSocialService> listTasks = new ArrayList<>();

    private String date;
    private User user;

    private TextView tvName;
    private ProgressBar pbLoading;
    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;


    //Фрагмент информации о пользователе
    private FragmentInfoAboutNeedy fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;








    @SuppressLint("ValidFragment")
    public FragmentVolunteerTaskView(User user, String date){
        this.user = user;
        this.date = date;
    }

    public FragmentVolunteerTaskView(){}




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);
        initializeScreenElements();

        if(!(user == null)){
            initializeList();
            showInfo(user);
        }
        else {
            tvName.setText("Пользователь не выбран!");
        }

        return v;
    }






    @Override
    public void initializeScreenElements() {
        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);
        tvName = v.findViewById(R.id.tvListTasksName);
        pbLoading = v.findViewById(R.id.pbTasksLoading);

    }

    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
    }



    public void initializeList(){
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();
        /*
        if(!(dataBase.dao_volunteer_addedNeedy_task().getAllUsers()==null)){
            listTasks=dataBase.dao_volunteer_addedNeedy_task().getByABC(date, id);
            initializeRecycleView();
        }
         */
    }







    private void initializeRecycleView(){
        adapterTasks=new AdapterVolunteerTaskList(getContext(), listTasks, this);
        recyclerViewTask.setAdapter(adapterTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTask.setLayoutManager(layoutManager);
    }



    @Override
    public void deleteTask(TaskSocialService task) {


        NetworkService.
                getInstance().getServiceApi().delete(task).enqueue(new Callback<TaskSocialService>() {
            @Override
            public void onResponse(Call<TaskSocialService> call, Response<TaskSocialService> response) {
                Toast.makeText(getContext(), "SUCCESSFUL!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TaskSocialService> call, Throwable t) {
                Toast.makeText(getContext(), "FAILED!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showInfo(User user){
        fNeedyInfo=new FragmentInfoAboutNeedy(user);
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.replace(R.id.frameCustomerInfo, fNeedyInfo);
        fChildTranInfo.commit();
    }






    //Async для загрузки тасков с сервера
    class LoadingAsync extends AsyncTask<Void, Void, Void> {

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
                        if (listTasks.get(i).getNeedy().getNickname().equals(user.getNickname())){
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
