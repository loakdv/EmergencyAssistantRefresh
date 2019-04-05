package com.example.dmitriy.emergencyassistant.Fragments.Volunteer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Volunteer_NeedyList;
import com.example.dmitriy.emergencyassistant.Fragments.InfoBlocks.Fragment_TopPhoto;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment_Volunteer_Main extends Fragment implements Adapter_Volunteer_NeedyList.CallBackButtons {


    /*
   Этот интерфейс имплементируется активностью доктора
   Он необходим для смены рабочего фрагмента
    */
    public interface onChangeVolunFrag{
        void setMain();
        void setSettings();
        void setTasks(Entity_Volunteer_AddedNeedy needy, String date);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeVolun=(onChangeVolunFrag)context;
    }

    //Фрагмент со списком пользователей
    private Fragment_Volunteer_NeedyList fragmentVolunteerNeedyList;
    private FragmentTransaction fChildTranNeedyList;
    private FragmentManager fChildManNeedyList;

    private onChangeVolunFrag changeVolun;

    //Фрагмент с фото в левом меню
    private Fragment_TopPhoto fTopPhoto;
    private FragmentTransaction fChildTranTopPhoto;
    private FragmentManager fChildManTopPhoto;

    private TextView tv_Surname;
    private TextView tv_Name;
    private TextView tv_MiddleName;
    private Button btn_Settings;

    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;
    private CalendarView calendarView;

    private String mainSelectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        initializeDataBase();

        seeTop();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerSettings:
                        changeVolun.setSettings();
                        break;
                }
            }
        };

        btn_Settings=v.findViewById(R.id.btn_VolunteerSettings);
        btn_Settings.setOnClickListener(oclBtn);



        tv_Surname=v.findViewById(R.id.tv_VolunteerSurname);
        tv_Surname.setText(profile.getSurname());
        tv_Name=v.findViewById(R.id.tv_VolunteerName);
        tv_Name.setText(profile.getName());
        tv_MiddleName=v.findViewById(R.id.tv_VolunteerMiddleName);
        tv_MiddleName.setText(profile.getMiddlename());

        calendarView=v.findViewById(R.id.calendar_TasksCalendar);

        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");



        Date date=new Date(calendarView.getDate());



        Log.d("CALENDAR", ""+date);

        sdf=new SimpleDateFormat("dd/MM/yyyy");
        Log.d("CALENDAR", ""+sdf.format(new Date(calendarView.getDate())));


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            Date phoneDate = new Date();
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {

                SimpleDateFormat sdfCal=new SimpleDateFormat("dd-MM-yyyy");

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
                Log.d("CALENDAR", "CALENDAR DATE "+selectedDate);

                mainSelectedDate=selectedDate;
                seeNeedyList(selectedDate);

            }
        });


        return v;
    }


    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
    }



    private void seeTop(){
        fTopPhoto=new Fragment_TopPhoto();
        fChildManTopPhoto=getChildFragmentManager();
        fChildTranTopPhoto=fChildManTopPhoto.beginTransaction();
        fChildTranTopPhoto.add(R.id.frame_VolunteerPhoto, fTopPhoto);
        fChildTranTopPhoto.commit();
        Log.i("LOG_TAG", "--- Created See_TopPhoto fragment ---");
    }

    private void seeNeedyList(String date){
        fragmentVolunteerNeedyList=new Fragment_Volunteer_NeedyList(date);
        fChildManNeedyList=getChildFragmentManager();
        fChildTranNeedyList=fChildManNeedyList.beginTransaction();
        fChildTranNeedyList.add(R.id.frame_VolunteerNotes, fragmentVolunteerNeedyList);
        fChildTranNeedyList.commit();
    }

    @Override
    public void setTask(Entity_Volunteer_AddedNeedy needy) {

        Date phoneDate = new Date();
        SimpleDateFormat sdfCal=new SimpleDateFormat("dd-MM-yyyy");

        changeVolun.setTasks(needy, mainSelectedDate);
    }


}
