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

public class FragmentVolunteerMain extends Fragment implements AdapterVolunteerNeedyList.CallBackButtons, InterfaceInitialize {


    /*
   Этот интерфейс имплементируется активностью доктора
   Он необходим для смены рабочего фрагмента
    */
    public interface onChangeVolunFrag{
        void setMain();
        void setSettings();
        void setTasks(EntityVolunteerAddedNeedy needy, String date);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeVolun=(onChangeVolunFrag)context;
    }

    //Фрагмент со списком пользователей
    private FragmentVolunteerNeedyList fragmentVolunteerNeedyList;
    private FragmentTransaction fChildTranNeedyList;
    private FragmentManager fChildManNeedyList;

    private onChangeVolunFrag changeVolun;

    //Фрагмент с фото в левом меню
    private FragmentHeader fTopPhoto;
    private FragmentTransaction fChildTranTopPhoto;
    private FragmentManager fChildManTopPhoto;

    private TextView tv_Surname;
    private TextView tv_Name;
    private TextView tv_MiddleName;
    private TextView tv_ID;
    private Button btn_Settings;

    private Button btnMainHelp;
    private Button btnHelpDrawer;
    private Button btnCopyID;

    private DataBaseAppDatabase dataBase;

    private EntityUser profile;
    private CalendarView calendarView;

    private String mainSelectedDate;

    private View v;


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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        initializeDataBase();
        initializeScreenElements();
        seeTop();
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
        btn_Settings=v.findViewById(R.id.btn_VolunteerSettings);
        btn_Settings.setOnClickListener(oclBtn);

        btnMainHelp=v.findViewById(R.id.btn_volunteer_main_help);
        btnMainHelp.setOnClickListener(oclBtn);

        btnHelpDrawer=v.findViewById(R.id.btn_volunteer_main_id);
        btnHelpDrawer.setOnClickListener(oclBtn);

        btnCopyID = v.findViewById(R.id.btn_volun_main_copy);
        btnCopyID.setOnClickListener(oclBtn);

        tv_Surname=v.findViewById(R.id.tv_VolunteerSurname);
        tv_Name=v.findViewById(R.id.tv_VolunteerName);
        tv_MiddleName=v.findViewById(R.id.tv_VolunteerMiddleName);
        tv_ID=v.findViewById(R.id.tv_VolunteerID);

        calendarView=v.findViewById(R.id.calendar_TasksCalendar);
    }


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

    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_user().getProfile();
    }




    private void seeTop(){
        fTopPhoto=new FragmentHeader();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_VolunteerPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
    }




    private void seeNeedyList(String date){
        fragmentVolunteerNeedyList=new FragmentVolunteerNeedyList(date);
        fChildManNeedyList=getChildFragmentManager();
        fChildTranNeedyList=fChildManNeedyList.beginTransaction();
        fChildTranNeedyList.add(R.id.frame_VolunteerNotes, fragmentVolunteerNeedyList);
        fChildTranNeedyList.commit();
    }




    private void setInitials(){
        /*
        tv_Surname.setText(profile.getSurname());
        tv_Name.setText(profile.getName());
        tv_MiddleName.setText(profile.getMiddlename());
         */
        //tv_ID.setText("Ваш ID: "+profile.getId());
    }


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


    private void copyId(){

        ClipData clipData;

        ClipboardManager clipboardManager;
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        String id = dataBase.dao_user().getProfile().getId();

        clipData = ClipData.newPlainText("id", id);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getContext(),"ID был скопирован! ",Toast.LENGTH_SHORT).show();
    }

}
