package com.example.dmitriy.emergencyassistant;

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

public class Fragment_Login_Volunteer extends Fragment {

    EditText et_Name, et_Surname, et_Middlename;
    Button btn_Confirm;

    String[] city={"Владивосток"};

    Spinner spinner_city;
    Spinner spinner_organization;

    //Объявляем интерфеяс для связью с основной активностью
    Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем объявленный интерфейс
        intLoginFrag=(Fragment_Login_FirstSelect.changeLoginFragment) context;
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
                        Helper_CreateProfile.name=et_Name.getText().toString();
                        Helper_CreateProfile.middlename=et_Middlename.getText().toString();
                        Helper_CreateProfile.surname=et_Surname.getText().toString();
                        intLoginFrag.startMainAct();
                        break;
                }
            }
        };

        et_Name=v.findViewById(R.id.et_LoginVolunName);
        et_Surname=v.findViewById(R.id.et_LoginVolunSurname);
        et_Middlename=v.findViewById(R.id.et_LoginVolunMiddlename);

        btn_Confirm=v.findViewById(R.id.btn_LoginVolunReady);
        btn_Confirm.setOnClickListener(oclBtn);
        return v;
    }
}
