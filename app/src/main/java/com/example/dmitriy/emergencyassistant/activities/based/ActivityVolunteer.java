/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 6:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWarningTask;
import com.example.dmitriy.emergencyassistant.fragments.navigation.FragmentVolunteerNavigation;
import com.example.dmitriy.emergencyassistant.fragments.navigation.SelectorVariant;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerMain;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerProfile;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerTaskView;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityUser;

/*
Активность раздела соц. работника
 */
public class ActivityVolunteer extends AppCompatActivity {

    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private static final String LOG_TAG = "LOGIN_SERVICE";
    private SharedPreferences loginPreferences;

    //Фрагменты используемые в активности
    private FragmentVolunteerMain fragmentVolunteerMain;
    private FragmentVolunteerTaskView fragmentVolunteerTasksView;
    private FragmentVolunteerProfile fragmentVolunteerProfile;
    private FragmentTransaction fTran;


    private FragmentVolunteerNavigation fragmentVolunteerNavigation;
    private FragmentTransaction fragmentTransactionNavigation;


    //Используется в том случае, если не был выбран юзер но был совершён переъод в раздел тасков
    private User lastSelectedUser;
    private String lastSelectedDate;

    //Текущий юзер для передачи данных фрагментам
    //Например, для отображения данных о профиле
    private EntityUser currentUser;

    //База данных откуда берутся данные о текущем юзере
    private DataBaseAppDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        setNavigationPanel(SelectorVariant.USERS_LIST);
        initializeDatabase();
        initializeFragments();
        initializeCurrentUser();
        setUsers();

    }


    //Инициализируем объекты фрагментов
    private void initializeFragments(){
        fragmentVolunteerMain = new FragmentVolunteerMain();
        fragmentVolunteerProfile = new FragmentVolunteerProfile(currentUser);
        fragmentVolunteerNavigation = new FragmentVolunteerNavigation();
    }


    private void initializeCurrentUser(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String mainNickname = loginPreferences.getString("mainUserNickname", "null");

        currentUser = database.daoUser().getByNickname(mainNickname);
    }

    private void initializeDatabase(){
        database = Room.databaseBuilder(this,
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();

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
        fragmentVolunteerProfile = new FragmentVolunteerProfile(currentUser);
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
