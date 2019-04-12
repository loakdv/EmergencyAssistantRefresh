package com.example.dmitriy.emergencyassistant.Fragments.Login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;

public class Fragment_Login_FirstSelect extends Fragment {

    /*
    Фрагмент первого выбора
     */

    /*Интерфейс который будет связывать все фрагменты логина
      Также нужен для связи с основной активностью Activity_Login
     */
    public interface changeLoginFragment{
        void setVolun();
        void setNeedy();
        void setRelative();
        void setFirst();
        void setEnter();
        void setCreate();
        void startMainAct(boolean login);
    }

    //объявляем созданный интерфейс
    private changeLoginFragment intLoginFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем интерфейс
        intLoginFrag=(changeLoginFragment) context;
    }


    //Кнопки создания аккаунта и авторизации, назад
    private Button btn_CreateNewAccount;
    private Button btn_Autorization;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_firstselect, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CreateNewAccount:
                       //Вызываем метод из интерфейса
                       intLoginFrag.setCreate();
                       break;
                   case R.id.btn_Autorization:
                       //Вызываем метод из интерфейса
                       intLoginFrag.setEnter();
                       break;
               }
            }
        };


        //Инициализация кнопок и присвоение листенера
        btn_Autorization=v.findViewById(R.id.btn_Autorization);
        btn_Autorization.setOnClickListener(oclBtn);
        btn_CreateNewAccount=v.findViewById(R.id.btn_CreateNewAccount);
        btn_CreateNewAccount.setOnClickListener(oclBtn);


        return v;
    }



}
