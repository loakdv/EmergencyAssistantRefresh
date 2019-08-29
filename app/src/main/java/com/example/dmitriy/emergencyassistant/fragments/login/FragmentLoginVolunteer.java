/*
 *
 *  Created by Dmitry Garmyshev on 8/29/19 4:14 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 2:11 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitriy.emergencyassistant.helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.model.user.User;
import com.example.dmitriy.emergencyassistant.retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Фрагмент для создания профиля типа Volunteer
 */

public class FragmentLoginVolunteer extends Fragment implements
        InterfaceInitialize {

    private EditText
            etName,
            etSurname,
            etMiddlename,
            etVolunteerOrganization;

    private Button
            btnConfirm,
            btnBack;

    private View v;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_login_volunteer, container, false);


        return v;
    }


    @Override
    public void initializeScreenElements() {
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_LoginVolunReady:
                        HelperCreateProfile.NAME = etName.getText().toString();
                        HelperCreateProfile.MIDDLENAME = etMiddlename.getText().toString();
                        HelperCreateProfile.SURNAME = etSurname.getText().toString();
                        HelperCreateProfile.VOLUNTEER_ORGANIZATION = etVolunteerOrganization.getText().toString();
//                        intLoginFrag.continueLogin(false);

                        break;

                    case R.id.btn_login_volunteer_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        etName =v.findViewById(R.id.et_LoginVolunName);
        etSurname =v.findViewById(R.id.et_LoginVolunSurname);
        etMiddlename =v.findViewById(R.id.et_LoginVolunMiddlename);
        etVolunteerOrganization =v.findViewById(R.id.et_Login_Volunteer_Organization);

        btnBack=v.findViewById(R.id.btn_login_volunteer_back);
        btnBack.setOnClickListener(oclBtn);

        btnConfirm =v.findViewById(R.id.btn_LoginVolunReady);
        btnConfirm.setOnClickListener(oclBtn);
    }
}
