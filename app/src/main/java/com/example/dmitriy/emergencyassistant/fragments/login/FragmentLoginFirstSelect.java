/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 2:23 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityLogin;
import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogOrganizationsList;
import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.activities.dialogs.lists.ActivityDialogFastUserSelect;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.tooltip.Tooltip;

/*
Фрагмент первого выбора (создание аккаунта или регистрация)
     */

public class FragmentLoginFirstSelect extends Fragment implements InterfaceInitialize {



    //Кнопки создания аккаунта и авторизации, назад
    private Button
            btnCreateNewAccount,
            btnAutorization,
            btnHelp,
            btnAboutApp,
            btnCreateRequest,
            btnOrganizations,
            btnFastUsers;

    private View v;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_login_firstselect, container, false);
        initializeScreenElements();
        return v;
    }




    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_CreateNewAccount:
//                        //Вызываем метод из интерфейса
//                        ((ActivityLogin)getActivity()).setCreate();
                        break;
                    case R.id.btn_Autorization:
                        //Вызываем метод из интерфейса
                        ((ActivityLogin)getActivity()).setEnter();
                        break;
                    case R.id.btn_Login_FirstSelect_Help:
                        String text= "В этом меню вы можете создать новый профиль или" +
                                " авторизоваться в уже имеющемся. \n" +
                                "(Нажмите на сообщение, что бы его закрыть)";
                        showTooltip(v, Gravity.TOP, text);
                        break;
                    case R.id.btn_login_fsel_aboutapp:
                        startAboutApp();
                        break;
                    case R.id.btn_CreateRequest:
                        ((ActivityLogin)getActivity()).setRequest();
                        break;
                    case R.id.btn_Login_Organizations:
                        seeOrganizations();
                        break;
                    case R.id.btn_ready_users:
                        seeFastUsers();
                        break;
                }
            }
        };


        //Инициализация кнопок и присвоение листенера
        btnAutorization =v.findViewById(R.id.btn_Autorization);
        btnAutorization.setOnClickListener(oclBtn);
        btnCreateNewAccount =v.findViewById(R.id.btn_CreateNewAccount);
        btnCreateNewAccount.setOnClickListener(oclBtn);
        btnHelp = v.findViewById(R.id.btn_Login_FirstSelect_Help);
        btnHelp.setOnClickListener(oclBtn);

        btnCreateRequest = v.findViewById(R.id.btn_CreateRequest);
        btnCreateRequest.setOnClickListener(oclBtn);

        btnOrganizations = v.findViewById(R.id.btn_Login_Organizations);
        btnOrganizations.setOnClickListener(oclBtn);

        btnAboutApp = v.findViewById(R.id.btn_login_fsel_aboutapp);
        btnAboutApp.setOnClickListener(oclBtn);

        btnFastUsers = v.findViewById(R.id.btn_ready_users);
        btnFastUsers.setOnClickListener(oclBtn);
    }


    //Метод нужен для создания подсказок
    private void showTooltip(View v, int gravity, String text){
        Button btn = (Button) v;

        Tooltip tooltip = new Tooltip.Builder(btn).
                setText(text).
                setTextColor(Color.WHITE).
                setGravity(gravity).
                setDismissOnClick(true).
                setBackgroundColor(R.drawable.lout_btn_blue).
                setCornerRadius(10f).
                show();

    }

    //Методы которые выводят различные окна на экран

    private void startAboutApp(){
        Intent i = new Intent(getContext(), ActivityDialogWelcomeMenu.class);
        startActivity(i);
    }

    private void seeOrganizations(){
        Intent i = new Intent(getContext(), ActivityDialogOrganizationsList.class);
        startActivity(i);
    }

    private void seeFastUsers(){
        Intent i = new Intent(getContext(), ActivityDialogFastUserSelect.class);
        startActivity(i);
    }



}
