package com.example.dmitriy.emergencyassistant.Fragments.Login;

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
import android.widget.Spinner;

import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;

/*
Фрагмент для создания профиля типа Volunteer
 */

public class Fragment_Login_Volunteer extends Fragment {

    private EditText et_Name, et_Surname, et_Middlename, et_VolunteerOrganization;
    private Button btn_Confirm, btnBack;


    //Объявляем интерфеяс для связью с основной активностью
    private Fragment_Login_FirstSelect.ChangeLoginFragment intLoginFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем объявленный интерфейс
        intLoginFrag=(Fragment_Login_FirstSelect.ChangeLoginFragment) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_volunteer, container, false);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_LoginVolunReady:
                        Helper_CreateProfile.NAME = et_Name.getText().toString();
                        Helper_CreateProfile.MIDDLENAME = et_Middlename.getText().toString();
                        Helper_CreateProfile.SURNAME = et_Surname.getText().toString();
                        Helper_CreateProfile.VOLUNTEER_ORGANIZATION = et_VolunteerOrganization.getText().toString();
                        intLoginFrag.continueLogin(false);
                        break;

                    case R.id.btn_login_volunteer_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        et_Name=v.findViewById(R.id.et_LoginVolunName);
        et_Surname=v.findViewById(R.id.et_LoginVolunSurname);
        et_Middlename=v.findViewById(R.id.et_LoginVolunMiddlename);
        et_VolunteerOrganization=v.findViewById(R.id.et_Login_Volunteer_Organization);

        btnBack=v.findViewById(R.id.btn_login_volunteer_back);
        btnBack.setOnClickListener(oclBtn);

        btn_Confirm=v.findViewById(R.id.btn_LoginVolunReady);
        btn_Confirm.setOnClickListener(oclBtn);
        return v;
    }
}
