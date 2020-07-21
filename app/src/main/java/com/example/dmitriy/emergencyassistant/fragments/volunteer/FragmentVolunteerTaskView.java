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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterExpandableListTasks;
import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentInfoAboutNeedy;
import com.example.dmitriy.emergencyassistant.fragments.navigation.FragmentVolunteerTaskCategorySelector;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.service.SocialServiceCatalog;
import com.example.dmitriy.emergencyassistant.model.service.TaskSocialService;
import com.example.dmitriy.emergencyassistant.model.service.TaskStatus;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;

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



    //Элементы необходимые для отображения списка
    private AdapterVolunteerTaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<TaskSocialService> listTasks = new ArrayList<>();

    private String selectedDate;
    private User selectedUser;

    private TextView tvName;
    private ProgressBar pbLoading;


    private ExpandableListView expandableListView;
    private List<SocialServiceCatalog> socialServiceCatalogs;

    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;


    //Фрагмент информации о пользователе
    private FragmentInfoAboutNeedy fNeedyInfo;
    private FragmentTransaction fChildTranInfo;
    private FragmentManager fChildManInfo;

    private FragmentVolunteerTaskCategorySelector fragmentSelector;
    private FragmentTransaction fChildTranCategory;
    private FragmentManager fChildManCategory;

    private EntityUser currentOwnerUser;




    @SuppressLint("ValidFragment")
    public FragmentVolunteerTaskView(EntityUser currentUser, User selectedUser, String selectedDate){
        this.selectedUser = selectedUser;
        this.selectedDate = selectedDate;
        this.currentOwnerUser = currentUser;
    }

    public FragmentVolunteerTaskView(){}




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);
        initializeScreenElements();
        checkCurrentUserAndShowMenus();
        getSocialServiceCatalogs();
        return v;
    }


    private void checkCurrentUserAndShowMenus(){
        if(!(selectedUser == null)){
            showInfo(selectedUser);
            initializeList(null); //null - all tasks
            showFiltersBar();
        }
        else {
            tvName.setText(getResources().getString(R.string.no_selected_user));
        }
    }





    @Override
    public void initializeScreenElements() {
        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);
        tvName = v.findViewById(R.id.tvListTasksName);
        pbLoading = v.findViewById(R.id.pbTasksLoading);
        expandableListView = v.findViewById(R.id.expListView);
    }

    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
    }



    public void initializeList(TaskStatus taskStatus){
        LoadTaskAsync loadTaskAsync = new LoadTaskAsync(taskStatus);
        loadTaskAsync.execute();
    }






    private void initializeRecycleView(){
        adapterTasks=new AdapterVolunteerTaskList(getContext(), listTasks, this);
        recyclerViewTask.setAdapter(adapterTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTask.setLayoutManager(layoutManager);
    }


    private void initializeExpandableListView(){
        AdapterExpandableListTasks expandableListAdapter = new AdapterExpandableListTasks(getContext(), socialServiceCatalogs);
        expandableListView.setAdapter(expandableListAdapter);
    }


    private void showInfo(User user){
        fNeedyInfo=new FragmentInfoAboutNeedy(user);
        fChildManInfo=getChildFragmentManager();
        fChildTranInfo=fChildManInfo.beginTransaction();
        fChildTranInfo.replace(R.id.frameCustomerInfo, fNeedyInfo);
        fChildTranInfo.commit();
    }


    private void showFiltersBar(){
        fragmentSelector = new FragmentVolunteerTaskCategorySelector();
        fChildManCategory = getChildFragmentManager();
        fChildTranCategory = fChildManCategory.beginTransaction();
        fChildTranCategory.replace(R.id.frameTasksFilter, fragmentSelector);
        fChildTranCategory.commit();
    }


    private void getSocialServiceCatalogs(){
        NetworkService.getInstance()
                .getServiceApi()
                .getCatalogs()
                .enqueue(new Callback<List<SocialServiceCatalog>>() {
                    @Override
                    public void onResponse(Call<List<SocialServiceCatalog>> call, Response<List<SocialServiceCatalog>> response) {

                        if(response.body() != null){
                            socialServiceCatalogs = response.body();
                            initializeExpandableListView();
                        }
                        else Log.i("LOGGGGG", "NULL");

                    }

                    @Override
                    public void onFailure(Call<List<SocialServiceCatalog>> call, Throwable t) {
                        Log.i("LOGGGGG", t.getMessage());
                    }
                });
    }


    @Override
    public void updateTask(TaskSocialService task, int position){
        UpdateTaskAsync updateTaskAsync = new UpdateTaskAsync(task);
        updateTaskAsync.execute();
    }




    //Async для загрузки тасков с сервера
    class UpdateTaskAsync extends AsyncTask<Void, Void, Void> {

        private TaskSocialService updatingTask;

        public UpdateTaskAsync(TaskSocialService updatingTask){
            this.updatingTask = updatingTask;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            NetworkService.getInstance()
                    .getTaskApi()
                    .update(updatingTask.getId(), updatingTask.getTaskStatus(), updatingTask.isEnable())
                    .enqueue(new Callback<TaskSocialService>() {
                        @Override
                        public void onResponse(Call<TaskSocialService> call, Response<TaskSocialService> response) {
                            initializeList(null);
                            fragmentSelector.selectAll();
                        }

                        @Override
                        public void onFailure(Call<TaskSocialService> call, Throwable t) {

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
    class LoadTaskAsync extends AsyncTask<Void, Void, Void> {

        private TaskStatus selectedTaskStatus;

        public LoadTaskAsync(TaskStatus selectedTaskStatus){
            this.selectedTaskStatus = selectedTaskStatus;
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
                    getTaskSocialServices().
                    enqueue(new Callback<List<TaskSocialService>>() {
                @Override
                public void onResponse(Call<List<TaskSocialService>> call, Response<List<TaskSocialService>> response) {

                    if(response.body() != null){
                        List<TaskSocialService> loadedList = response.body();
                        List<TaskSocialService> sortedList = new ArrayList<>();

                        for(int i = 0; i < loadedList.size(); i++){
                            TaskSocialService lTask = loadedList.get(i);

                            if(selectedTaskStatus != null){
                                if(isSelectedNicknameAndSelectedTaskStatus(loadedList.get(i)))
                                    sortedList.add(lTask);
                            }
                            else {
                                if(isSelectedNickname(lTask))
                                    sortedList.add(lTask);
                            }
                        }

                        listTasks = sortedList;
                        initializeRecycleView();
                    }

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


        private boolean isSelectedNicknameAndSelectedTaskStatus(TaskSocialService taskSocialService){
            if (taskSocialService.getNeedy().getNickname().equals(selectedUser.getNickname())){
                if(taskSocialService.getTaskStatus() == selectedTaskStatus){
                    return true;
                }
                else return false;
            }
            else return false;
        }



        private boolean isSelectedNickname(TaskSocialService taskSocialService){
            if (taskSocialService.getNeedy().getNickname().equals(selectedUser.getNickname()))
                return true;

            else return false;
        }


    }







}
