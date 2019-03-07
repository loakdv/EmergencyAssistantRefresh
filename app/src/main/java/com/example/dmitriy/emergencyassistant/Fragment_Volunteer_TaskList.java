package com.example.dmitriy.emergencyassistant;

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
import android.widget.Adapter;
import android.widget.Button;

import java.util.List;
import java.util.Random;

@SuppressLint("ValidFragment")
public class Fragment_Volunteer_TaskList extends Fragment {

    public interface OnTasksClick{
        void goBack();
    }

    OnTasksClick onTasksClick;

    Adapter_Volunteer_TaskList adapterTasks;
    RecyclerView recyclerViewTask;
    List<Entity_Volunteer_AddedNeedy_Task> listTasks;

    DataBase_AppDatabase dataBase;

    Button btnBack;

    Entity_Volunteer_AddedNeedy addedNeedy;


    @SuppressLint("ValidFragment")
    public Fragment_Volunteer_TaskList(Entity_Volunteer_AddedNeedy needy){
        this.addedNeedy=needy;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTasksClick=(OnTasksClick)context;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_tasklist, container, false);

        recyclerViewTask=v.findViewById(R.id.rv_VolunteerTasks);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackTask:
                        onTasksClick.goBack();
                        break;
                }
            }
        };

        initializeDataBase();
        fillTaskList();
        initializeList();
        initializeRecycleView();

        btnBack=v.findViewById(R.id.btn_BackTask);
        btnBack.setOnClickListener(oclBtn);
        return v;
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }




    private void initializeList(){
        if(!(dataBase.dao_volunteer_addedNeedy_task().getAll()==null)){
            listTasks=dataBase.dao_volunteer_addedNeedy_task().getAllById(addedNeedy.getId());
        }
    }




    private void initializeRecycleView(){
        adapterTasks=new Adapter_Volunteer_TaskList(getActivity(), listTasks);
        recyclerViewTask.setAdapter(adapterTasks);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    private void fillTaskList(){
        if(dataBase.dao_volunteer_addedNeedy_task().getAllById(addedNeedy.getId())==null||
        dataBase.dao_volunteer_addedNeedy_task().getAllById(addedNeedy.getId()).isEmpty()){

            for(int i=0; i<7; i++){
                dataBase.dao_volunteer_addedNeedy_task().insert(new Entity_Volunteer_AddedNeedy_Task(
                        "Задание пользователя"+addedNeedy.getId()+" "+i,
                        "Описание задания", "00.00.0000", addedNeedy.getId()));
            }
        }
    }




}
