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

/*
Фрагмент для создания профиля типа Relative
 */

public class FragmentLoginRelative extends Fragment {

    private EditText et_Name, et_Surname, et_Middlename;
    private Button btn_Confirm, btnBack;



    //Объявляем интерфеяс для связью с основной активностью
    private FragmentLoginFirstSelect.ChangeLoginFragment intLoginFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем объявленный интерфейс
        intLoginFrag=(FragmentLoginFirstSelect.ChangeLoginFragment) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_relative, container, false);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_LoginRelativeReady:
                        HelperCreateProfile.NAME =et_Name.getText().toString();
                        HelperCreateProfile.MIDDLENAME =et_Middlename.getText().toString();
                        HelperCreateProfile.SURNAME =et_Surname.getText().toString();
                        intLoginFrag.continueLogin(false);
                        break;

                    case R.id.btn_login_relative_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        et_Name=v.findViewById(R.id.et_LoginRelativeName);
        et_Surname=v.findViewById(R.id.et_LoginRelativeSurname);
        et_Middlename=v.findViewById(R.id.et_LoginRelativeMiddlename);

        btnBack=v.findViewById(R.id.btn_login_relative_back);
        btnBack.setOnClickListener(oclBtn);

        btn_Confirm=v.findViewById(R.id.btn_LoginRelativeReady);
        btn_Confirm.setOnClickListener(oclBtn);
        return v;
    }





}
