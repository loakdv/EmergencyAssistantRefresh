package com.example.dmitriy.emergencyassistant.Fragments.Volunteer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_AboutApp;
import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_Main;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.google.firebase.auth.FirebaseAuth;

/*
Фрагмент который отображает окно настроек соц. работника
 */

public class Fragment_Volunteer_Settings extends Fragment {

    private  Fragment_Volunteer_Main.onChangeVolunFrag changeFrag;

    private FirebaseAuth mAuth;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(Fragment_Volunteer_Main.onChangeVolunFrag) context;
    }

    private Button btn_Back;
    private Button btn_DeleteProfile;
    private Button btnAbout;

    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_volunteer_settings, container, false);

        mAuth= FirebaseAuth.getInstance();

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
                    case R.id.btn_VolunAboutApp:
                        startAbout();
                        break;
                }
            }
        };

        btn_Back=v.findViewById(R.id.btn_BackVolunteer);
        btn_Back.setOnClickListener(oclBtn);
        btn_DeleteProfile=v.findViewById(R.id.btn_DeleteProfileVolun);
        btn_DeleteProfile.setOnClickListener(oclBtn);
        btnAbout=v.findViewById(R.id.btn_VolunAboutApp);
        btnAbout.setOnClickListener(oclBtn);
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
        mAuth.signOut();
        dataBase.dao_profile().delete(profile);
        Intent main=new Intent(getContext(), Activity_Main.class);
        startActivity(main);
    }

    private void startAbout(){
        Intent i = new Intent(getContext(), Activity_AboutApp.class);
        startActivity(i);
    }
}
