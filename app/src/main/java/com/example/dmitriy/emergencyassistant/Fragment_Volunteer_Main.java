package com.example.dmitriy.emergencyassistant;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
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
import android.widget.TextView;

public class Fragment_Volunteer_Main extends Fragment implements Adapter_Volunteer_NeedyList.CallBackButtons {


    /*
   Этот интерфейс имплементируется активностью доктора
   Он необходим для смены рабочего фрагмента
    */
    public interface onChangeVolunFrag{
        void setMain();
        void setSettings();
        void setTasks(Entity_Volunteer_AddedNeedy needy);
    }


    onChangeVolunFrag changeVolun;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeVolun=(onChangeVolunFrag)context;
    }

    //Фрагмент со списком пользователей
    Fragment_Volunteer_NeedyList fragmentVolunteerNeedyList;
    FragmentTransaction fChildTranNeedyList;
    FragmentManager fChildManNeedyList;

    //Фрагмент с фото в левом меню
    Fragment_TopPhoto fTopPhoto;
    FragmentTransaction fChildTranTopPhoto;
    FragmentManager fChildManTopPhoto;

    TextView tv_Surname;
    TextView tv_Name;
    TextView tv_MiddleName;
    Button btn_Settings;

    DataBase_AppDatabase dataBase;

    Entity_Profile profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_main, container, false);
        initializeDataBase();

        seeTop();
        seeNeedyList();

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

    private void seeNeedyList(){
        fragmentVolunteerNeedyList=new Fragment_Volunteer_NeedyList();
        fChildManNeedyList=getChildFragmentManager();
        fChildTranNeedyList=fChildManNeedyList.beginTransaction();
        fChildTranNeedyList.add(R.id.frame_VolunteerNotes, fragmentVolunteerNeedyList);
        fChildTranNeedyList.commit();
    }

    @Override
    public void setTask(Entity_Volunteer_AddedNeedy needy) {
        changeVolun.setTasks(needy);
    }


}
