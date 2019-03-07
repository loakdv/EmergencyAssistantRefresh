package com.example.dmitriy.emergencyassistant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Activity_Volunteer extends AppCompatActivity implements Fragment_Volunteer_Main.onChangeVolunFrag, Fragment_Volunteer_NeedyList.onTaskClick,
Fragment_Volunteer_TaskList.OnTasksClick{

    //Фрагменты для переключения
    Fragment_Volunteer_Main fragmentVolunteerMain;
    Fragment_Volunteer_Settings fragmentVolunteerSettings;
    Fragment_Volunteer_TaskList fragmentVolunteerTaskList;


    FragmentTransaction fTran;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        //Инициализируем фрагменты
        fragmentVolunteerMain=new Fragment_Volunteer_Main();
        fragmentVolunteerSettings=new Fragment_Volunteer_Settings();
        setFragment();
    }




    private void setFragment(){
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }




    @Override
    public void setMain() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }



    @Override
    public void setSettings() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerSettings);
        fTran.commit();
    }


    @Override
    public void setTasks(Entity_Volunteer_AddedNeedy needy) {

        fragmentVolunteerTaskList=new Fragment_Volunteer_TaskList(needy);
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTaskList);
        fTran.commit();


    }




    @Override
    public void onTaskClick(Entity_Volunteer_AddedNeedy needy) {
        setTasks(needy);
    }




    @Override
    public void goBack() {
        setMain();
    }




}
