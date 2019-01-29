package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Activity_Volunteer extends AppCompatActivity implements Fragment_Volunteer_Main.onChangeVolunFrag {

    //Фрагменты для переключения
    Fragment_Volunteer_Main fMain;
    Fragment_Volunteer_Settings fSettings;
    Fragment_Volunteer_Map fMap;

    //Транзакция
    FragmentTransaction fTran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        //Инициализируем фрагменты
        fMain=new Fragment_Volunteer_Main();
        fSettings=new Fragment_Volunteer_Settings();
        fMap=new Fragment_Volunteer_Map();
        setFragment();
    }

    private void setFragment(){
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fMain);
        fTran.commit();
    }

    @Override
    public void setMain() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fMain);
        fTran.commit();
    }

    @Override
    public void setMap() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fMap);
        fTran.commit();
    }

    @Override
    public void setSettings() {
        fTran=getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fSettings);
        fTran.commit();
    }
}
