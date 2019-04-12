package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_Main;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_NeedyList;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_Settings;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_TaskList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_Volunteer extends AppCompatActivity implements Fragment_Volunteer_Main.onChangeVolunFrag, Fragment_Volunteer_NeedyList.onTaskClick,
        Fragment_Volunteer_TaskList.OnTasksClick {

    //Фрагменты для переключения
    private Fragment_Volunteer_Main fragmentVolunteerMain;
    private Fragment_Volunteer_Settings fragmentVolunteerSettings;
    private Fragment_Volunteer_TaskList fragmentVolunteerTaskList;

    //Транзакция
    private FragmentTransaction fTran;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        initializeFragments();
        setFragment();
    }




    private void initializeFragments(){
        //Инициализируем фрагменты
        fragmentVolunteerMain = new Fragment_Volunteer_Main();
        fragmentVolunteerSettings = new Fragment_Volunteer_Settings();

    }



    private void setFragment(){
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }



    @Override
    public void setMain() {
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }



    @Override
    public void setSettings() {
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerSettings);
        fTran.commit();
    }



    @Override
    public void setTasks(Entity_Volunteer_AddedNeedy needy, String date) {

        fragmentVolunteerTaskList = new Fragment_Volunteer_TaskList(needy.getNeedyId(), date);
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTaskList);
        fTran.commit();
    }



    @Override
    public void onTaskClick(Entity_Volunteer_AddedNeedy needy, String date) {
        setTasks(needy, date);
    }



    @Override
    public void goBack() {
        setMain();
    }


}
