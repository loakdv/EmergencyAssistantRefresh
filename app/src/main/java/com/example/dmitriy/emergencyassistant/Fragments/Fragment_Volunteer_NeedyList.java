package com.example.dmitriy.emergencyassistant.Fragments;

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

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Volunteer_NeedyList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Volunteer_AddedNeedy;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Volunteer_NeedyList extends Fragment implements Adapter_Volunteer_NeedyList.CallBackButtons{


    public interface onTaskClick{
        void onTaskClick(Entity_Volunteer_AddedNeedy needy);
    }


    private  RecyclerView rvNeedyList;
    private Adapter_Volunteer_NeedyList adapterVolunteerNeedyList;
    private List<Entity_Volunteer_AddedNeedy> needyList=new ArrayList<Entity_Volunteer_AddedNeedy>();

    private DataBase_AppDatabase dataBase;


    private onTaskClick onTaskClick;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTaskClick=(onTaskClick)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_needy_list, container, false);

        rvNeedyList=v.findViewById(R.id.rv_Volunteer_Needys);

        initializeDataBase();


        initializeList();
        initializeRecycleView();

        return v;
    }

    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }



    private void initializeList(){
        if(!(dataBase.dao_volunteer_addedNeedy().getAll()==null)){
            needyList=dataBase.dao_volunteer_addedNeedy().getAll();
        }

    }

    private void initializeRecycleView(){
        adapterVolunteerNeedyList=new Adapter_Volunteer_NeedyList(getActivity(), needyList,this);
        rvNeedyList.setAdapter(adapterVolunteerNeedyList);
        rvNeedyList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setTask(Entity_Volunteer_AddedNeedy needy) {
        onTaskClick.onTaskClick(needy);
    }

    private void fillList(){

        if(dataBase.dao_volunteer_addedNeedy().getAll()==null||dataBase.dao_volunteer_addedNeedy().getAll().isEmpty()) {
            for (int i = 0; i < 4; i++) {
                dataBase.dao_volunteer_addedNeedy().insert(new Entity_Volunteer_AddedNeedy(
                        1, 1, 1, "Фамилия", "Имя",
                        "Отчество", dataBase.dao_volunteer().get_Volunteer().getId()));
                Log.i("LOG_TAG", "ADDED NEEDY: " + i);

            }
        }

    }


    /*
    private void fillTasks(){
        Log.i("LOG_TAG", "FILL TASKS");
        Entity_Volunteer_AddedNeedy needy;


        for (int c = 0; c<needyList.size(); c++){
            Log.i("LOG_TAG", "ITERATION"+c);
            needy=needyList.get(c);
            for (int j = 0; j<5; j++){
                dataBase.dao_volunteer_addedNeedy_task().insert(new Entity_Volunteer_AddedNeedy_Task("Task name "+j,
                        "About task "+j, "00.00.0000",  needy.getId()));
                Log.i("LOG_TAG", "CREATED TASK FOR NEEDY "+c+" NUMBER "+j);
            }
        }

    }
   
    */




}
