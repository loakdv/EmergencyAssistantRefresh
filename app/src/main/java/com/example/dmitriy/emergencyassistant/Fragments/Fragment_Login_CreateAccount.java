package com.example.dmitriy.emergencyassistant.Fragments;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Entity_Profile;

public class Fragment_Login_CreateAccount extends Fragment {

    /*
    Фрагмент с первым забором данных
     */


    private String profileType[]={"Нуждающийся в помощи", "Пользователь", "Врач", "Соц. работник"};

    //Объявляем интерфеяс для связью с основной активностью
    private Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;

    //Кнопка завершения создания аккаунта
    private Button btn_Ready;
    //Поля заполненные пользователем
    private EditText et_LoginNumber;
    private EditText et_LoginPassword;
    private EditText et_LoginRepeatPassword;


    private DataBase_AppDatabase dataBase;

    private Entity_Profile profile;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем объявленный интерфейс
        intLoginFrag=(Fragment_Login_FirstSelect.changeLoginFragment) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_createaccount, container, false);

        initializeDataBase();

        //Создаём спиннер для выбора типа профиля
        //Создаём адаптер
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, profileType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Spinner для типов
        Spinner typeSpinner=v.findViewById(R.id.spinner_LoginType);
        typeSpinner.setAdapter(typeAdapter);

        //Листенер выбранного варианта
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //В профиле ставим тип
                //Потом эти данные сохраняются
                switch (position){
                    case 0:
                        Helper_CreateProfile.type=0;
                        break;
                    case 1:
                        Helper_CreateProfile.type=1;
                        Helper_CreateProfile.doctor=false;
                        break;
                    case 2:
                        Helper_CreateProfile.type=1;
                        Helper_CreateProfile.doctor=true;
                        break;
                    case 3:
                        Helper_CreateProfile.type=2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Создаём листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_LoginNext:
                       nextStep();
                       break;
               }
            }
        };
        //Кнопка готовности
        btn_Ready=v.findViewById(R.id.btn_LoginNext);
        btn_Ready.setOnClickListener(oclBtn);

        //Поля заполненные пользователем
        et_LoginNumber=v.findViewById(R.id.et_Login_Login);
        et_LoginPassword=v.findViewById(R.id.et_Login_Password);
        et_LoginRepeatPassword=v.findViewById(R.id.et_Login_RepeatPassword);

        return v;
    }


    private void checkFields(){
        if(!et_LoginNumber.getText().toString().isEmpty()||!et_LoginNumber.getText().toString().equals("")||!et_LoginPassword.getText().toString().isEmpty()||
                !et_LoginRepeatPassword.getText().toString().isEmpty()){
          if(et_LoginPassword.getText().toString().equals(et_LoginRepeatPassword.getText().toString())){
            }
            else {
                Toast.makeText(getContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
          }
        }
        else {
            Toast.makeText(getContext(), "Необходимо ввести логин и пароль!", Toast.LENGTH_SHORT).show();
        }

    }


    private void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
        //Инициализируем объект профиля
        profile=dataBase.dao_profile().getProfile();
    }


    private void nextStep(){
        Helper_CreateProfile.phonenumber=et_LoginNumber.getText().toString();
        Helper_CreateProfile.password=et_LoginPassword.getText().toString();
        switch (Helper_CreateProfile.type){
            case 0:
                intLoginFrag.setNeedy();
                break;
            case 1:
                intLoginFrag.setRelative();
                break;
            case 2:
                intLoginFrag.setVolun();
                break;
        }
    }
}
