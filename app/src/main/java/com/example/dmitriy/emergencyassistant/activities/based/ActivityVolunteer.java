/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/18/19 12:43 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWarningTask;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerMain;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerSettings;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceOnCustomerSelected;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceVolunteerChangeFragments;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;

/*
Активность раздела соц. работника
 */
public class ActivityVolunteer extends AppCompatActivity implements
        InterfaceVolunteerChangeFragments,
        InterfaceOnCustomerSelected{

    //Фрагменты используемые в активности
    private FragmentVolunteerMain fragmentVolunteerMain;
    private FragmentVolunteerSettings fragmentVolunteerSettings;
    private FragmentVolunteerTaskList fragmentVolunteerTaskList;
    private FragmentTransaction fTran;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        initializeFragments();
        setFirstFragment();
    }





    //Инициализируем объекты фрагментов
    private void initializeFragments(){
        fragmentVolunteerMain = new FragmentVolunteerMain();
        fragmentVolunteerSettings = new FragmentVolunteerSettings();
    }

    //Метод устанавливает первый фрагмент
    private void setFirstFragment(){
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }






    //ON CUSTOMER SELECTED
    //После нажатия на таск, выполняется этот метод
    //Сам интерфейс исходит из адаптера со списком загруженных юзеров
    @Override
    public void onCustomerClick(EntityVolunteerAddedNeedy needy, String date) {
        setTasksList(needy, date);
    }


    //CHANGE FRAGMENTS
    /*
    Методы которые выполняют смену фрагментов на экране
    Выведены вниз для того что-бы не мешались в более
    важных частях кода
     */
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
    public void setTasksList(EntityVolunteerAddedNeedy needy, String date) {
        fragmentVolunteerTaskList = new FragmentVolunteerTaskList(
                "NEEDY_ID",
                date,
                ""+"SURNAME"+" "+"NAME"+" "+"MIDDLENAME");
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTaskList);
        fTran.commit();
    }










    private void startSignalsService(){
        //startService(new Intent(this, ServiceBackGround.class));
    }

    //Метод выводит нв экран уведомление о сигнале
    private void seeSignalWindow(String initials, int type){
        Intent i = new Intent(ActivityVolunteer.this, ActivityDialogWarningTask.class);
        i.putExtra("Initials", initials);
        i.putExtra("Type", type);
        startActivity(i);
    }


}
