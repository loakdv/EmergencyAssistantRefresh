/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.fragments.infoblocks.FragmentHeader;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;
import com.tooltip.Tooltip;

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
    private FragmentVolunteerNeedyList fragmentVolunteerNeedyList;
    private FragmentTransaction fChildTranNeedyList;
    private FragmentManager fChildManNeedyList;

    //Фрагмент с фото в левом меню
    private FragmentHeader fTopPhoto;
    private FragmentTransaction fChildTranTopPhoto;
    private FragmentManager fChildManTopPhoto;

    //Элементы экрана
    private TextView
            tvSurname,
            tvName,
            tvMiddleName,
            tvID;

    private Button
            btnSettings,
            btnMainHelp,
            btnHelpDrawer,
            btnCopyID;

    //На данный момент заменён calendar view из библиотеки
    private CalendarView calendarView;

    //View используемый на этом экране
    private View v;

    //Локальная БД
    private DataBaseAppDatabase dataBase;

    //Интерфейс для связи с основной активностью
    //Сам класс интерфейса внизу кода этого класса
    private onChangeVolunFrag changeVolun;

    //Используется для
    private String mainSelectedDate;

    //Используется как поле класса, для того что-бы можно было получать к нему доступ из
    //разных частей класса
    private EntityUser profile;


    /*
    В этом методе инициализируем интерфейс для связи с основной активностью
    Без него интерфейс не будет работать
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeVolun=(onChangeVolunFrag)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        initializeDataBase();
        initializeScreenElements();
        seeHeader();
        setInitials();
        initializeCalendar();
        return v;
    }


    /*
    Метод который мы взяли из интерфейса, который нужен для
    инициализации элементов
     */
    @Override
    public void initializeScreenElements(){
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerSettings:
                        changeVolun.setSettings();
                        break;
                    case R.id.btn_volunteer_main_help:
                        showTooltip(v, Gravity.TOP, "Для отображения " +
                                "текущего списка задач выберите одну из дат на календаре. \n" + "\n" +
                                "Для доступа к основному меню используйте левую боковую панель.\n" + "\n"+
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;

                    case R.id.btn_volunteer_main_id:
                        showTooltip(v, Gravity.TOP, "Это ваш уникальный ID. \n \n" +
                                "Используйте его для того, что бы другие пользователи смогли " +
                                "вас найти. \n \n" +
                                "(Нажмите на сообщение чтобы закрыть его)");
                        break;
                    case R.id.btn_volun_main_copy:
                        copyId();
                        break;

                }
            }
        };

        btnSettings =v.findViewById(R.id.btn_VolunteerSettings);
        btnSettings.setOnClickListener(oclBtn);

        btnMainHelp=v.findViewById(R.id.btn_volunteer_main_help);
        btnMainHelp.setOnClickListener(oclBtn);

        btnHelpDrawer=v.findViewById(R.id.btn_volunteer_main_id);
        btnHelpDrawer.setOnClickListener(oclBtn);

        btnCopyID = v.findViewById(R.id.btn_volun_main_copy);
        btnCopyID.setOnClickListener(oclBtn);

        tvSurname =v.findViewById(R.id.tv_VolunteerSurname);
        tvName =v.findViewById(R.id.tv_VolunteerName);
        tvMiddleName =v.findViewById(R.id.tv_VolunteerMiddleName);
        tvID =v.findViewById(R.id.tv_VolunteerID);

        calendarView=v.findViewById(R.id.calendar_TasksCalendar);
    }


    /*
    Инициализация календаря
    Настроиваем его и выставляем изначально нужную дату
     */
    private void initializeCalendar(){

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

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

                    seeNeedyList(selDate);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }



            }
        });

        horizontalCalendar.selectDate(Calendar.getInstance(), true);


        /*
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            Date phoneDate = new Date();
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {

                int mYear = year;
                int mMonth = month+1;
                int mDay = dayOfMonth;

                String sMonth=Integer.toString(mMonth);
                String sDay=Integer.toString(mDay);

                if (mMonth>0&&mMonth<10){
                    sMonth="0"+sMonth;
                }

                if (mDay>0&&mDay<10) {
                    sDay="0"+sDay;
                }
                String selectedDate = new StringBuilder().append(sDay)
                        .append("-").append(sMonth).append("-").append(mYear).toString();


                mainSelectedDate=selectedDate;
                seeNeedyList(selectedDate);

            }
        });
         */
    }

    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_user().getProfile();
    }

    @Override
    public void initializeList() {}


    //Отображаем на экране фрагмент с пользователями на нужную дату
    private void seeNeedyList(String date){
        fragmentVolunteerNeedyList=new FragmentVolunteerNeedyList(date);
        fChildManNeedyList=getChildFragmentManager();
        fChildTranNeedyList=fChildManNeedyList.beginTransaction();
        fChildTranNeedyList.add(R.id.frame_VolunteerNotes, fragmentVolunteerNeedyList);
        fChildTranNeedyList.commit();
    }


    //Отображаем на экране заголовок и информацией о юзере
    private void seeHeader(){
        fTopPhoto=new FragmentHeader();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_VolunteerPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
    }



    private void setInitials(){
        /*
        tvSurname.setText(profile.getSurname());
        tvName.setText(profile.getName());
        tvMiddleName.setText(profile.getMiddlename());
         */
        //tvID.setText("Ваш ID: "+profile.getId());
    }


    //Метод который строит подсказку и отображает её
    private void showTooltip(View v, int gravity, String text){
        Tooltip tooltip = new Tooltip.Builder(v).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(Color.BLUE).
                setCornerRadius(10f).
                show();

    }


    @Override
    public void setTask(EntityVolunteerAddedNeedy needy) {
        changeVolun.setTasks(needy, mainSelectedDate);
    }

    //Метод который копирует Id пользователя
    private void copyId(){

        ClipData clipData;

        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        String id = dataBase.dao_user().getProfile().getId();

        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
    }


    /*
  Этот интерфейс имплементируется активностью доктора
  Он необходим для смены рабочего фрагмента
   */
    public interface onChangeVolunFrag{
        void setMain();
        void setSettings();
        void setTasks(EntityVolunteerAddedNeedy needy, String date);
    }


}
