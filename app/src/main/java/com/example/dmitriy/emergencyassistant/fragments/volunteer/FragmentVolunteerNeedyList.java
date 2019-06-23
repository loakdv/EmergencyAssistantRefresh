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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.login.POJOProfile;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOTask;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.volunteer.EntityVolunteerAddedNeedy;

import java.util.ArrayList;
import java.util.List;

/*
Фрагмент который отображает у соц. работника список Needy
 */

@SuppressLint("ValidFragment")
public class FragmentVolunteerNeedyList extends Fragment implements AdapterVolunteerNeedyList.CallBackButtons{


    public interface onTaskClick{
        void onTaskClick(EntityVolunteerAddedNeedy needy, String date);
    }


    private RecyclerView rvNeedyList;
    private AdapterVolunteerNeedyList adapterVolunteerNeedyList;
    private List<EntityVolunteerAddedNeedy> needyList=new ArrayList<EntityVolunteerAddedNeedy>();

    private DataBaseAppDatabase dataBase;

    private String calendarDate;

    private onTaskClick onTaskClick;

    private List<POJOProfile> profiles;
    private List<POJOTask> tasks;


    private NeedysThred needysThred;

    private boolean isTasksOpened;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTaskClick=(onTaskClick)context;
    }

    @SuppressLint("ValidFragment")
    public FragmentVolunteerNeedyList(String date){
        this.calendarDate=date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_needy_list, container, false);

        rvNeedyList=v.findViewById(R.id.rv_Volunteer_Needys);

        isTasksOpened=true;

        initializeDataBase();
        initializeList();

        //needysThred=new NeedysThred();
        //needysThred.start();



        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }




    private void initializeList(/*String date, String needyId*/){
        needyList.add(new EntityVolunteerAddedNeedy());
        initializeRecycleView();


        /*
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            needyList=dataBase.dao_volunteer_addedNeedy().getListByDate(date);
            initializeRecycleView();
        }
         */


    }


    private void loadUsers(){

    }


    private void initializeRecycleView(){
        if(isTasksOpened){
            adapterVolunteerNeedyList=new AdapterVolunteerNeedyList(getContext(), needyList,this);
            rvNeedyList.setAdapter(adapterVolunteerNeedyList);
            rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }

    @Override
    public void setTask(EntityVolunteerAddedNeedy needy) {
        isTasksOpened=false;
        onTaskClick.onTaskClick(needy, calendarDate);
    }




    //Класс не будет использоваться после перехода на Retrofit
    private class NeedysThred extends Thread{

        @Override
        public void run() {
            super.run();

        }
    }

}
