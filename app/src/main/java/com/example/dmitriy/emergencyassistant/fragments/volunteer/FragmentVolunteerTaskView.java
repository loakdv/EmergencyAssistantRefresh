/*
 *
 *  Created by Dmitry Garmyshev on 10/28/19 6:15 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/5/19 12:05 PM
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoAboutNeedy;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.SocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;
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
    private Button btnNewTasks
            ,btnClosedTasks
            ,btnProcessingTasks
            ,btnAllTasks;

    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;


    //Фрагмент информации о пользователе
    private FragmentInfoAboutNeedy fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;

    private int lastUpdatedPosition = 0;



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
            initializeList(TaskStatus.NEW);
            showInfo(user);
        }
        else {
            v.findViewById(R.id.lnTasksNavigate).setVisibility(View.GONE);
            tvName.setText(getResources().getString(R.string.no_selected_user));
        }

        return v;
    }






    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskStatus lStatus = TaskStatus.NEW;
                switch (view.getId()){
                    case R.id.btnAllTasks:
                        initializeList(null);
                        break;
                    case R.id.btnNewTasksList:
                        initializeList(TaskStatus.NEW);
                        break;
                    case R.id.btnCommonTasks:
                        initializeList(TaskStatus.PROCESSING);
                        break;
                    case R.id.btnClosedTasks:
                        initializeList(TaskStatus.CLOSED);
                        break;

                }
            }
        };
        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);
        tvName = v.findViewById(R.id.tvListTasksName);
        pbLoading = v.findViewById(R.id.pbTasksLoading);

        btnAllTasks = v.findViewById(R.id.btnAllTasks);
        btnAllTasks.setOnClickListener(oclBtn);

        btnClosedTasks = v.findViewById(R.id.btnClosedTasks);
        btnClosedTasks.setOnClickListener(oclBtn);

        btnProcessingTasks = v.findViewById(R.id.btnCommonTasks);
        btnProcessingTasks.setOnClickListener(oclBtn);

        btnNewTasks = v.findViewById(R.id.btnNewTasksList);
        btnNewTasks.setOnClickListener(oclBtn);

    }

    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
    }



    public void initializeList(TaskStatus taskStatus){
        LoadingAsync loadingAsync = new LoadingAsync(taskStatus);
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
    public void updateTask(TaskSocialService task, int position){
        TaskSocialService init = task;
        TaskSocialService updated = task;
        updated.setTaskStatus(TaskStatus.CLOSED);
        UpdateTaskAsync updateTaskAsync = new UpdateTaskAsync(init, updated);
        updateTaskAsync.execute();
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

        private TaskStatus taskStatus;

        public LoadingAsync(TaskStatus taskStatus){
            this.taskStatus = taskStatus;
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
                        if (listTasks.get(i).getNeedy().getNickname().equals(user.getNickname())
                                && listTasks.get(i).getTaskStatus() == taskStatus){
                            sortedList.add(listTasks.get(i));
                        }
                        if(taskStatus == null
                        &&listTasks.get(i).getNeedy().getNickname().equals(user.getNickname())){
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


    //Async для загрузки тасков с сервера
    class UpdateTaskAsync extends AsyncTask<Void, Void, Void> {

        private TaskSocialService taskSocialServiceInit, taskSocialService;
        private Long id;

        public UpdateTaskAsync(TaskSocialService taskSocialServiceInit, TaskSocialService taskSocialService){
            this.taskSocialServiceInit = taskSocialServiceInit;
            this.taskSocialService = taskSocialService;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            NetworkService.getInstance()
//                    .getTaskApi()
//                    .update(taskSocialServiceInit, taskSocialService)
//                    .enqueue(new Callback<TaskSocialService>() {
//                        @Override
//                        public void onResponse(Call<TaskSocialService> call, Response<TaskSocialService> response) {
//
//                            if (response.body() != null){
//                                Log.d("AAA", response.body().toString());
//                            }
//                            else {Log.d("AAA", "NULL BODY");}
//                            Log.d("AAA", call.request().toString());
//                            initializeList();
//
//                            Toast.makeText(getContext(), "SUCCESSFUL!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<TaskSocialService> call, Throwable t) {
//                            Toast.makeText(getContext(), "FAILURE!", Toast.LENGTH_SHORT).show();
//                        }
//                    });

            NetworkService.getInstance()
                    .getTaskApi()
                    .update(taskSocialServiceInit.getId(), taskSocialService)
                    .enqueue(new Callback<TaskSocialService>() {
                        @Override
                        public void onResponse(Call<TaskSocialService> call, Response<TaskSocialService> response) {

                            if (response.body() != null){
                                Log.d("AAA", response.body().toString());
                            }
                            else {Log.d("AAA", "NULL BODY");}
                            Log.d("AAA", call.request().toString());
                            initializeList(TaskStatus.NEW);

                            Toast.makeText(getContext(), "SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<TaskSocialService> call, Throwable t) {

                        }
                    });
            return null;
        }

//        private int get

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            pbLoading.setVisibility(View.GONE);

        }
    }




}
