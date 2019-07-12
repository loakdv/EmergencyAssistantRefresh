/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitriy.emergencyassistant.helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;

/*
Фрагмент для логирования в системе
 */

public class FragmentLoginEnter extends Fragment implements InterfaceInitialize {



    //Объявляем интерфейс для связи с основной активностью ActivityLogin
    private FragmentLoginFirstSelect.ChangeLoginFragment intLoginFrag;

    //Кнопка входа в аккаунт
    private Button btnEnterLog;
    private Button btnBack;

    private EditText etEnterEmail;
    private EditText etEnterPassword;
    private View v;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем интерфейс
        intLoginFrag=(FragmentLoginFirstSelect.ChangeLoginFragment) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_login_enter, container, false);
        initializeScreenElements();
        return v;
    }



    @Override
    public void initializeScreenElements() {
        //Листенер нажатий
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_EnterLog:
                        //Вызываем метод из интерфейса

                        HelperCreateProfile.EMAIL =etEnterEmail.getText().toString();
                        HelperCreateProfile.PASSWORD =etEnterPassword.getText().toString();

                        intLoginFrag.continueLogin(true);
                        break;

                    case R.id.btn_login_enter_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        //Инициализируем кнопку и припысываем листенер
        btnEnterLog =v.findViewById(R.id.btn_EnterLog);
        btnEnterLog.setOnClickListener(oclBtn);

        btnBack=v.findViewById(R.id.btn_login_enter_back);
        btnBack.setOnClickListener(oclBtn);

        etEnterEmail=v.findViewById(R.id.et_Enter_Email);
        etEnterPassword=v.findViewById(R.id.et_Enter_Password);
    }

}
