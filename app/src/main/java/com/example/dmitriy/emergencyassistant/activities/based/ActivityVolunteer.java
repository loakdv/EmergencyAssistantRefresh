/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 2:49 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWarningTask;
import com.example.dmitriy.emergencyassistant.fragments.navigation.FragmentVolunteerNavigation;
import com.example.dmitriy.emergencyassistant.fragments.navigation.SelectorVariant;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerMain;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerProfile;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerSettings;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerTaskView;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.navigation.InterfaceVolunteerNavigation;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceOnCustomerSelected;
import com.example.dmitriy.emergencyassistant.interfaces.volunteer.InterfaceVolunteerChangeFragments;
import com.example.dmitriy.emergencyassistant.model.user.User;

import java.util.Date;

/*
Активность раздела соц. работника
 */
public class ActivityVolunteer extends AppCompatActivity {

    //Фрагменты используемые в активности
    private FragmentVolunteerMain fragmentVolunteerMain;
    private FragmentVolunteerSettings fragmentVolunteerSettings;
    private FragmentVolunteerTaskView fragmentVolunteerTasksView;
    private FragmentVolunteerProfile fragmentVolunteerProfile;
    private FragmentTransaction fTran;


    private FragmentVolunteerNavigation fragmentVolunteerNavigation;
    private FragmentTransaction fragmentTransactionNavigation;


    //Используется в том случае, если не был выбран юзер но был совершён переъод в раздел тасков
    private User lastSelectedUser;
    private String lastSelectedDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        setNavigationPanel(SelectorVariant.USERS_LIST);

        initializeFragments();
        setUsers();
    }


    //Инициализируем объекты фрагментов
    private void initializeFragments(){
        fragmentVolunteerMain = new FragmentVolunteerMain();
        fragmentVolunteerSettings = new FragmentVolunteerSettings();
        fragmentVolunteerProfile = new FragmentVolunteerProfile();
        fragmentVolunteerNavigation = new FragmentVolunteerNavigation();
    }




    //ON CUSTOMER SELECTED
    //После нажатия на таск, выполняется этот метод
    //Сам интерфейс исходит из адаптера со списком загруженных юзеров
    public void onCustomerClick(User needy, String date) {
        /*
        Сохраняем эти переменные на тот случай если
        юзер зайдёт в раздел тасков через навигационное меню
        будет выдаваться последний юзер
         */
        lastSelectedUser = needy;
        lastSelectedDate = date;
        setTasksFromSelectedUser(needy, date);
    }



    //CHANGE FRAGMENTS
    /*
    Методы которые выполняют смену фрагментов на экране
    Выведены вниз для того что-бы не мешались в более
    важных частях кода
     */

    //МЕТОД ВЫЗЫВАЕТСЯ ПРИ ВЫБОРЕ ЮЗЕРА ИЗ СПИСКА
    public void setTasksFromSelectedUser(User user, String date) {
        setNavigationPanel(SelectorVariant.TASKS_LIST);
        fragmentVolunteerTasksView = new FragmentVolunteerTaskView(user, date);
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTasksView);
        fTran.commit();
    }




    //МЕТОД ВЫПОЛНЯЕТСЯ ВО ВРЕМЯ ПЕРЕКЛЮЧЕНИЯ ИЗ ПАНЕЛИ НАВИГАЦИИ
    public void setTasks() {
        if(lastSelectedUser == null){
            fragmentVolunteerTasksView = new FragmentVolunteerTaskView();
        }
        else {
            fragmentVolunteerTasksView = new FragmentVolunteerTaskView(lastSelectedUser, lastSelectedDate);
        }

        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTasksView);
        fTran.commit();
    }



    public void setUsers() {
        fragmentVolunteerMain = new FragmentVolunteerMain();
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }



    public void setProfile() {
        fragmentVolunteerProfile = new FragmentVolunteerProfile();
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerProfile);
        fTran.commit();
    }



    //Создаём навигационную панель с нужными настройками
    private void setNavigationPanel(SelectorVariant selectorVariant){
        fragmentVolunteerNavigation = new FragmentVolunteerNavigation();
        Bundle bundle = new Bundle();
        bundle.putSerializable("selected_key", selectorVariant);
        fragmentVolunteerNavigation.setArguments(bundle);
        fragmentTransactionNavigation = getSupportFragmentManager().beginTransaction();
        fragmentTransactionNavigation.replace(R.id.frame_VolunteerNavigation, fragmentVolunteerNavigation);
        fragmentTransactionNavigation.commit();
    }







    private void startSignalsService(){
        //startService(new Intent(this, ServiceBackGround.class));
    }

    //Метод выводит нв экран уведомление о сигнале
    private void seeSignalWindow(String initials, int type){
        Intent i = new Intent(ActivityVolunteer.this, ActivityDialogWarningTask.class);
        i.putExtra("BlockInitials", initials);
        i.putExtra("Type", type);
        startActivity(i);
    }


}
