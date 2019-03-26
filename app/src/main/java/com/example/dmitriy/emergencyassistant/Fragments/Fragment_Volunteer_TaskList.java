package com.example.dmitriy.emergencyassistant.Fragments;

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
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Volunteer_TaskList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Volunteer_AddedNeedy_Task;

import java.util.List;

public class Fragment_Volunteer_TaskList extends Fragment {

    public interface OnTasksClick{
        void goBack();
    }

    private OnTasksClick onTasksClick;

    private Adapter_Volunteer_TaskList adapterTasks;
    private RecyclerView recyclerViewTask;
    private List<Entity_Volunteer_AddedNeedy_Task> listTasks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onTasksClick=(OnTasksClick)context;
    }

    private DataBase_AppDatabase dataBase;

    private Button btnBack;

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
            listTasks=dataBase.dao_volunteer_addedNeedy_task().getAll();
        }
    }

    private void initializeRecycleView(){
        adapterTasks=new Adapter_Volunteer_TaskList(getActivity(), listTasks);
        recyclerViewTask.setAdapter(adapterTasks);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
