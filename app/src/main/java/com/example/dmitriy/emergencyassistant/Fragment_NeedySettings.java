package com.example.dmitriy.emergencyassistant;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_NeedySettings extends Fragment {

    //Временная переменная для сохранения настроек
    SharedPreferences settingsPref;
    public static final String APP_PREFERENCES = "settings";


    //Элементы экрана
    EditText etSurname;
    EditText etName;
    EditText etMiddleName;
    EditText etInfo;
    Button btnSave;
    Button btn_Delete;



    /*
    Кнопки для выбора фрагмента
    Фрагменты для добавления номеров, врачей, родственников
     */
    Button btn_Numbers;
    Button btn_Relatives;


    //Элементы выборов сигнала
    Button btn_Sos0, btn_Sos1, btn_Sos2, btn_Help0, btn_Help1, btn_Help2;

    Button btn_stateYes, btn_stateNo;
    TextView tv_CheckState;

    TextView tv_stateHelp, tv_StateSos;


    DataBase_AppDatabase dataBase;

    Entity_Profile profile;

    Entity_Needy needy;

    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needysettings, container, false);

        initializeDataBase();

        //Листенер для кнопок SOS
        View.OnClickListener oclSos=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btn_Sos0:
                        dataBase.dao_needy().setSos(0);
                        tv_StateSos.setText("Родственники и соц. работники");
                        break;
                    case R.id.btn_Sos1:
                        dataBase.dao_needy().setSos(1);
                        tv_StateSos.setText("Только родственники");
                        break;
                    case R.id.btn_Sos2:
                        dataBase.dao_needy().setSos(2);
                        tv_StateSos.setText("Только соц. работники");
                        break;

                }
            }
        };


        //Листенер для кнопок Help
        View.OnClickListener oclHelp=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_Help0:
                        dataBase.dao_needy().setHelp(0);
                        tv_stateHelp.setText("Родственники и соц. работники");
                        break;
                    case R.id.btn_Help1:
                        dataBase.dao_needy().setHelp(1);
                        tv_stateHelp.setText("Только родственники");
                        break;
                    case R.id.btn_Help2:
                        dataBase.dao_needy().setHelp(2);
                        tv_stateHelp.setText("Только соц. работники");
                        break;
                }
            }

        };

        //Листенер для кнопок сохранения и удаления
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnSave:
                        break;
                    case R.id.btn_Numbers:
                        startNumbers();
                        break;
                    case R.id.btn_Profiles:
                        startRelatives();
                        break;
                    case R.id.btn_DeleteProfileNeedy:
                        deleteProfile();

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

        btnSave=v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(oclBtn);

        btn_Numbers=v.findViewById(R.id.btn_Numbers);
        btn_Numbers.setOnClickListener(oclBtn);

        btn_Relatives=v.findViewById(R.id.btn_Profiles);
        btn_Relatives.setOnClickListener(oclBtn);

        //Инициализируем элементы сигналов
        btn_Sos0=v.findViewById(R.id.btn_Sos0);
        btn_Sos0.setOnClickListener(oclSos);
        btn_Sos1=v.findViewById(R.id.btn_Sos1);
        btn_Sos1.setOnClickListener(oclSos);
        btn_Sos2=v.findViewById(R.id.btn_Sos2);
        btn_Sos2.setOnClickListener(oclSos);

        btn_Help0=v.findViewById(R.id.btn_Help0);
        btn_Help0.setOnClickListener(oclHelp);
        btn_Help1=v.findViewById(R.id.btn_Help1);
        btn_Help1.setOnClickListener(oclHelp);
        btn_Help2=v.findViewById(R.id.btn_Help2);
        btn_Help2.setOnClickListener(oclHelp);

        tv_StateSos=v.findViewById(R.id.tv_StateSos);
        tv_stateHelp=v.findViewById(R.id.tv_StateHelp);

        btn_Delete=v.findViewById(R.id.btn_DeleteProfileNeedy);
        btn_Delete.setOnClickListener(oclBtn);

        btn_stateNo=v.findViewById(R.id.btn_state_no);
        btn_stateNo.setOnClickListener(oclState);
        btn_stateYes=v.findViewById(R.id.btn_state_yes);
        btn_stateYes.setOnClickListener(oclState);
        tv_CheckState=v.findViewById(R.id.tv_StateCheck);

        setSignalSos();
        setSignalHelp();
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

        dataBase.dao_profile().delete(profile);
        settingsPref=this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor=settingsPref.edit();
        settingsEditor.putBoolean("logged", false);
        settingsEditor.apply();


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


    private void setSignalSos(){
        if(needy.getSos_signal()==0){
            tv_StateSos.setText("Родственники и соц. работники");
        }
        else if(needy.getSos_signal()==1){
            tv_StateSos.setText("Только родственники");
        }
        else if(needy.getSos_signal()==2){
            tv_StateSos.setText("Только соц. работники");
        }
        else {
            tv_StateSos.setText("Не выбрано");
        }

    }



    private void setSignalHelp(){
        if(needy.getHelp_signal()==0){
            tv_stateHelp.setText("Родственники и соц. работники");
        }
        else if(needy.getHelp_signal()==1){
            tv_stateHelp.setText("Только родственники");
        }
        else if(needy.getHelp_signal()==2){
            tv_stateHelp.setText("Только соц. работники");
        }
        else {
            tv_stateHelp.setText("Не выбрано");
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
