/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.volunteer;

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

import com.example.dmitriy.emergencyassistant.activities.based.ActivityAboutApp;
import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.google.firebase.auth.FirebaseAuth;

/*
Фрагмент который отображает окно настроек соц. работника
 */

public class FragmentVolunteerSettings extends Fragment {

    private  FragmentVolunteerMain.onChangeVolunFrag changeFrag;

    private FirebaseAuth mAuth;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(FragmentVolunteerMain.onChangeVolunFrag) context;
    }

    private Button btn_Back;
    private Button btn_DeleteProfile;
    private Button btnAbout;

    private DataBaseAppDatabase dataBase;

    private EntityUser profile;

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
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_user().getProfile();
    }

    private void deleteProfile(){
        mAuth.signOut();
        //dataBase.dao_user().delete(profile);
        Intent main=new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }

    private void startAbout(){
        Intent i = new Intent(getContext(), ActivityAboutApp.class);
        startActivity(i);
    }
}
