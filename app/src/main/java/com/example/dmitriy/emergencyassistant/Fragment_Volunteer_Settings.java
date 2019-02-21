package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment_Volunteer_Settings extends Fragment {

    Fragment_Volunteer_Main.onChangeVolunFrag changeFrag;

    //Временная переменная для сохранения настроек
    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(Fragment_Volunteer_Main.onChangeVolunFrag) context;
    }

    Button btn_Back;
    Button btn_DeleteProfile;

    DataBase_AppDatabase dataBase;

    Entity_Profile profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_settings, container, false);
        initializeDataBase();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackVolunteer:
                        changeFrag.setMain();
                        break;
                    case R.id.btn_DeleteProfileVolun:
                        deleteProfile();
                        break;
                }
            }
        };

        btn_Back=v.findViewById(R.id.btn_BackVolunteer);
        btn_Back.setOnClickListener(oclBtn);
        btn_DeleteProfile=v.findViewById(R.id.btn_DeleteProfileVolun);
        btn_DeleteProfile.setOnClickListener(oclBtn);
        return v;
    }

    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
    }

    private void deleteProfile(){
        dataBase.dao_profile().delete(profile);
        settingsPref=this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor=settingsPref.edit();
        settingsEditor.putBoolean("logged", false);
        settingsEditor.apply();
        Intent main=new Intent(getContext(), Activity_Main.class);
        startActivity(main);
    }
}
