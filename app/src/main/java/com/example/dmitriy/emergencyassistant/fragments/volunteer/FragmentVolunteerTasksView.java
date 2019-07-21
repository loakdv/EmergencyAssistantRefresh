/*
 *
 *  Created by Dmitry Garmyshev on 7/21/19 8:23 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/20/19 9:48 AM
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

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoAboutNeedy;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentNotes;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoState;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceVolunteerChangeFragments;
import com.example.dmitriy.emergencyassistant.model.service.SocialServiceTask;
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
public class FragmentVolunteerTasksView extends Fragment implements
        AdapterVolunteerTaskList.CallBackButtons,
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Интерфейс для связи с основной активностью
    private InterfaceVolunteerChangeFragments onTasksClick;

    //Элементы необходимые для отображения списка
    private AdapterVolunteerTaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<SocialServiceTask> listTasks = new ArrayList<>();

    private String date;
    private User user;

    private Button btnBack;
    private ProgressBar pbLoading;
    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;

    //Работа с фрагментом состояния
    private FragmentInfoState fSeeState;
    private FragmentTransaction fChildTranState;
    private FragmentManager fChildManState;

    //Фрагменты заметок
    private FragmentNotes fSeeNotes;
    private FragmentTransaction fChildTranNotes;
    private FragmentManager fChildManNotes;

    //Фрагмент информации о пользователе
    private FragmentInfoAboutNeedy fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;








    @SuppressLint("ValidFragment")
    public FragmentVolunteerTasksView(User user, String date){
        this.user = user;
        this.date = date;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTasksClick=(InterfaceVolunteerChangeFragments) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);
        initializeScreenElements();
        initializeList();
        showInfo(user);
        showState();
        showNotes();
        return v;
    }






    @Override
    public void initializeScreenElements() {
        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackTask:
                        onTasksClick.setMain();
                        break;
                }
            }
        };


        btnBack=v.findViewById(R.id.btn_BackTask);
        btnBack.setOnClickListener(oclBtn);

        pbLoading = v.findViewById(R.id.pbLoadingTasks);
    }

    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
    }



    @Override
    public void initializeList(){
        LoadingAsync loadingAsync = new LoadingAsync();
        loadingAsync.execute();
        /*
        if(!(dataBase.dao_volunteer_addedNeedy_task().getAll()==null)){
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
    public void deleteTask(SocialServiceTask task) {
        NetworkService.
                getInstance().getServiceApi().delete(task.getSocialService());
    }





    private void showState(){
        fSeeState=new FragmentInfoState(user);
        fChildManState=getChildFragmentManager();
        fChildTranState=fChildManState.beginTransaction();
        fChildTranState.add(R.id.frameCustomerState, fSeeState);
        fChildTranState.commit();
    }

    private void showNotes(){
        fSeeNotes=new FragmentNotes(user);
        fChildManNotes=getChildFragmentManager();
        fChildTranNotes=fChildManNotes.beginTransaction();
        fChildTranNotes.replace(R.id.frameCustomerNotes , fSeeNotes);
        fChildTranNotes.commit();
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
                    getTaskSocialServices().enqueue(new Callback<List<SocialServiceTask>>() {
                @Override
                public void onResponse(Call<List<SocialServiceTask>> call, Response<List<SocialServiceTask>> response) {
                    listTasks = response.body();
                    Log.d("TASKS LIST", ""+listTasks.size());


                    List<SocialServiceTask> sortedList = new ArrayList<>();


                    for(int i = 0; i < listTasks.size(); i++){
                        if (listTasks.get(i).getNeedy().getNickname().equals(user.getNickname())){
                            sortedList.add(listTasks.get(i));
                        }
                    }

                    listTasks = sortedList;
                    Log.d("TASKS LIST", ""+sortedList.size());
                    initializeRecycleView();

                }

                @Override
                public void onFailure(Call<List<SocialServiceTask>> call, Throwable t) {

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
