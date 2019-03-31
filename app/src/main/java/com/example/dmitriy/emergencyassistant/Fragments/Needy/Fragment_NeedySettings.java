package com.example.dmitriy.emergencyassistant.Fragments.Needy;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds.Activity_Dialog_AddVolunteer;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_SeeSocialInfo;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists.Activity_Dialog_Numbers;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists.Activity_Dialog_Users;
import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_Main;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.google.firebase.auth.FirebaseAuth;

public class Fragment_NeedySettings extends Fragment {


    private FirebaseAuth mAuth;


    //Элементы экрана
    private TextView etSurname;
    private TextView etName;
    private TextView etMiddleName;
    private TextView etInfo;
    private TextView etNeedyId;
    private Button btn_Delete;



    /*
    Кнопки для выбора фрагмента
    Фрагменты для добавления номеров, врачей, родственников
     */
    private Button btn_Numbers;
    private Button btn_Relatives;
    private Button btnSocialHelp;


    //Элементы выборов сигнала
    private Button btn_Help0, btn_Help1, btn_Help2;

    private Button btn_stateYes, btn_stateNo;
    private TextView tv_CheckState;

    private TextView tv_stateHelp;


    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;

    private Entity_Needy needy;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needysettings, container, false);

        mAuth=FirebaseAuth.getInstance();

        initializeDataBase();



        //Листенер для кнопок сохранения и удаления
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                    case R.id.btn_Profiles:
                        startRelatives();
                        break;
                    case R.id.btn_DeleteProfileNeedy:
                        deleteProfile();
                        break;
                    case R.id.btn_SocialHelp:
                        startSocial();
                        break;

                }
            }
        };

        View.OnClickListener oclState=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_state_no:
                        dataBase.dao_needy().setState(0);
                        tv_CheckState.setText("Не отслеживается");
                        break;
                    case R.id.btn_state_yes:
                        dataBase.dao_needy().setState(1);
                        tv_CheckState.setText("Отслеживается");
                        break;
                }
            }
        };

        //Загружаем настрокий
        //loadSettings();
        //Инициализация элементов экрана
        etSurname=v.findViewById(R.id.etSurname);
        etSurname.setText(profile.getSurname());

        etName=v.findViewById(R.id.etName);
        etName.setText(profile.getName());

        etMiddleName=v.findViewById(R.id.etMiddleName);
        etMiddleName.setText(profile.getMiddlename());

        etInfo=v.findViewById(R.id.etInfo);
        etInfo.setText(needy.getInfo());

        btn_Numbers=v.findViewById(R.id.btn_Numbers);
        btn_Numbers.setOnClickListener(oclBtn);

        btn_Relatives=v.findViewById(R.id.btn_Profiles);
        btn_Relatives.setOnClickListener(oclBtn);

        etNeedyId=v.findViewById(R.id.tv_NeedySettingsID);
        etNeedyId.setText(needy.getProfile_id());



        btn_Delete=v.findViewById(R.id.btn_DeleteProfileNeedy);
        btn_Delete.setOnClickListener(oclBtn);

        btnSocialHelp=v.findViewById(R.id.btn_SocialHelp);
        btnSocialHelp.setOnClickListener(oclBtn);

        btn_stateNo=v.findViewById(R.id.btn_state_no);
        btn_stateNo.setOnClickListener(oclState);
        btn_stateYes=v.findViewById(R.id.btn_state_yes);
        btn_stateYes.setOnClickListener(oclState);
        tv_CheckState=v.findViewById(R.id.tv_StateCheck);


        setState();
        return v;
    }



    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
        needy=dataBase.dao_needy().getNeedy();
    }

    private void deleteProfile(){
        mAuth.signOut();
        dataBase.dao_profile().delete(profile);
        Intent main=new Intent(getContext(), Activity_Main.class);
        startActivity(main);
    }


    private void startNumbers(){
        Intent numbers=new Intent(getContext(), Activity_Dialog_Numbers.class);
        startActivity(numbers);
    }

    private void startRelatives(){
        Intent relatives=new Intent(getContext(), Activity_Dialog_Users.class);
        startActivity(relatives);
    }

    private void startSocial(){
        if(dataBase.dao_needy_volunteer().getVolunteer() != null){
            Intent i = new Intent(getContext(), Activity_SeeSocialInfo.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getContext(), Activity_Dialog_AddVolunteer.class);
            startActivity(i);
        }


    }






    private void setState(){
        if(dataBase.dao_needy().getNeedy().getState_signal()==1){
            tv_CheckState.setText("Отслеживается");
        }
        else if(dataBase.dao_needy().getNeedy().getState_signal()==0){
            tv_CheckState.setText("Не отслеживается");
        }
    }




}
