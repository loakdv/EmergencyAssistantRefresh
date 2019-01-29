package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.SharedPreferences;
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

public class Fragment_Login_CreateAccount extends Fragment {

    /*
    Фрагмент с первым забором данных
     */


    private String profileType[]={"Нуждающийся в помощи", "Пользователь", "Врач", "Соц. работник"};

    //Объявляем интерфеяс для связью с основной активностью
    Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;

    //Кнопка завершения создания аккаунта
    Button btn_Ready;
    //Поля заполненные пользователем
    EditText et_LoginSurname;
    EditText et_LoginName;
    EditText et_LoginMiddlename;
    EditText et_LoginNumber;
    EditText et_LoginPassword;
    EditText et_LoginRepeatPassword;

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
                        Profile.setType(position);
                        break;
                    case 1:
                        Profile.setType(position);
                        Profile.setDoctor(false);
                        break;
                    case 2:
                        Profile.setType(1);
                        Profile.setDoctor(true);
                        break;
                    case 3:
                        Profile.setType(position);
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
                   case R.id.btn_LoginReady:
                       checkFields();
                       break;
               }
            }
        };
        //Кнопка готовности
        btn_Ready=v.findViewById(R.id.btn_LoginReady);
        btn_Ready.setOnClickListener(oclBtn);

        //Поля заполненные пользователем
        et_LoginSurname=v.findViewById(R.id.et_LoginSurname);
        et_LoginName=v.findViewById(R.id.et_LoginName);
        et_LoginMiddlename=v.findViewById(R.id.et_LoginMiddlename);
        et_LoginNumber=v.findViewById(R.id.et_Login_Login);
        et_LoginPassword=v.findViewById(R.id.et_Login_Password);
        et_LoginRepeatPassword=v.findViewById(R.id.et_Login_RepeatPassword);

        return v;
    }

    private void checkFields(){
        if(!et_LoginNumber.getText().toString().isEmpty()||!et_LoginNumber.getText().toString().equals("")||!et_LoginPassword.getText().toString().isEmpty()||
                !et_LoginRepeatPassword.getText().toString().isEmpty()){
          if(et_LoginPassword.getText().toString().equals(et_LoginRepeatPassword.getText().toString())){
              if(!et_LoginName.getText().toString().isEmpty()||!et_LoginSurname.getText().toString().isEmpty()||!et_LoginMiddlename.getText().toString().isEmpty()){
                  createAccount();
              }
              else {
                  Toast.makeText(getContext(), "Необходимо заполнить все поля!", Toast.LENGTH_SHORT).show();
              }
            }
            else {
                Toast.makeText(getContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
          }
        }
        else {
            Toast.makeText(getContext(), "Необходимо ввести логин и пароль!", Toast.LENGTH_SHORT).show();
        }

    }

    private void createAccount(){
        Profile.setSurname(et_LoginSurname.getText().toString());
        Profile.setName(et_LoginName.getText().toString());
        Profile.setMiddlename(et_LoginMiddlename.getText().toString());
        Profile.setLogged(true);
        Toast.makeText(getContext(), "Профиль успешно создан!", Toast.LENGTH_SHORT).show();
        intLoginFrag.startMainAct();
    }
}
