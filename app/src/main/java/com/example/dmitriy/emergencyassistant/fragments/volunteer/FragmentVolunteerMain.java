/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 6:55 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityVolunteer;
import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/*
Фрагмент который отображает основное меню соц. работника
 */

public class FragmentVolunteerMain extends Fragment implements
        AdapterVolunteerNeedyList.CallBackButtons,
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Фрагмент со списком пользователей
    private FragmentVolunteerCustomersList fragmentVolunteerCustomersList;
    private FragmentTransaction fChildTranNeedyList;
    private FragmentManager fChildManNeedyList;

    private View v;

    private DataBaseAppDatabase dataBase;

    private String mainSelectedDate;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        //initializeDataBase();
        initializeScreenElements();

        return v;
    }




    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "app_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        //profile=dataBase.dao_user().getProfile();
    }




    /*
    Метод который мы взяли из интерфейса, который нужен для
    инициализации элементов
     */
    @Override
    public void initializeScreenElements(){
        initializeCalendar();
    }





    private void initializeCalendar(){

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(v, R.id.cal_horizontalCal)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .defaultSelectedDate(Calendar.getInstance())
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                try{

                    Date date1 = date.getTime();

                    SimpleDateFormat sdfCal = new SimpleDateFormat("dd-MM-yyyy");

                    String selDate=sdfCal.format(date1);
                    mainSelectedDate=selDate;
                    System.out.println(mainSelectedDate);
                    showNeedyList(mainSelectedDate);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }



            }
        });


        horizontalCalendar.selectDate(Calendar.getInstance(), true);
    }




    //Отображаем на экране фрагмент с пользователями на нужную дату
    private void showNeedyList(String date){
        fragmentVolunteerCustomersList =new FragmentVolunteerCustomersList(date);
        fChildManNeedyList=getChildFragmentManager();
        fChildTranNeedyList=fChildManNeedyList.beginTransaction();
        fChildTranNeedyList.replace(R.id.frame_VolunteerNotes, fragmentVolunteerCustomersList);
        fChildTranNeedyList.commit();
    }


    //Обращается к основной активности соц. работника, что-бы сменить основной фрагмент
    /*
    => Получаем через этот метод юзера которого мы выбрали в списке
    => Основной активности говорим что нам нужно поставить фрагмент с тасками
    от этого юзера (передаём нужные для этого данные)
     */
    @Override
    public void selectUser(User user) {
        ((ActivityVolunteer)getActivity()).setTasksFromSelectedUser(user, mainSelectedDate);
    }





}
