package com.example.dmitriy.emergencyassistant.Fragments.Relative;

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

import com.example.dmitriy.emergencyassistant.Activities.Based.ActivityAboutApp;
import com.example.dmitriy.emergencyassistant.Activities.Based.ActivityMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.EntityProfile;
import com.google.firebase.auth.FirebaseAuth;

/*
Фрагмент который нужен для отображения окна с настройками доктора/пользователя
 */

public class FragmentDoctorRelativeSettings extends Fragment {

    private FirebaseAuth mAuth;


    //Объект интерфейса для смены рабочего фрагмента
    private FragmentDoctorRelativeMain.onChangeDocFrag changeFrag;


    //Элементы в настройках
    private Button btn_Back;
    private Button btn_DeleteProfile;
    private Button btnApoutApp;

    private DataBaseAppDatabase dataBase;

    private EntityProfile profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_doctorrelatsettings, container, false);
        mAuth=FirebaseAuth.getInstance();
        initializeDataBase();

        //Считывание нажатий
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackDoc:
                        changeFrag.changeFragment();
                        break;
                    case R.id.btn_DeleteProfileDoc:
                        deleteProfile();
                        break;
                    case R.id.btn_RelativeAboutApp:
                        startAbout();
                        break;
                }
            }
        };
        //инициализация элементов
        btn_Back=v.findViewById(R.id.btn_BackDoc);
        btn_Back.setOnClickListener(oclBtn);
        btn_DeleteProfile=v.findViewById(R.id.btn_DeleteProfileDoc);
        btn_DeleteProfile.setOnClickListener(oclBtn);
        btnApoutApp=v.findViewById(R.id.btn_RelativeAboutApp);
        btnApoutApp.setOnClickListener(oclBtn);
        return v;
    }


    //Инициализируем объект интерфейчас при присоединении
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(FragmentDoctorRelativeMain.onChangeDocFrag)context;
    }


    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
    }

    private void deleteProfile(){
        mAuth.signOut();
        dataBase.dao_profile().delete(profile);
        Intent main=new Intent(getContext(), ActivityMain.class);
        startActivity(main);
    }



    private void startAbout(){
        Intent i = new Intent(getContext(), ActivityAboutApp.class);
        startActivity(i);
    }

}
