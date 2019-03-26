package com.example.dmitriy.emergencyassistant.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;

public class Fragment_Login_Relative extends Fragment {

    private EditText et_Name, et_Surname, et_Middlename;
    private Button btn_Confirm, btn_SelectImage;



    //Объявляем интерфеяс для связью с основной активностью
    private Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем объявленный интерфейс
        intLoginFrag=(Fragment_Login_FirstSelect.changeLoginFragment) context;
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
                        Helper_CreateProfile.name=et_Name.getText().toString();
                        Helper_CreateProfile.middlename=et_Middlename.getText().toString();
                        Helper_CreateProfile.surname=et_Surname.getText().toString();
                        intLoginFrag.startMainAct(false);
                        break;
                    case R.id.btn_loadImgRelative:
                        //Обращаемся к активности выбора фото из галереи
                        Intent photoPickerIntent =new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 1);
                        break;
                }
            }
        };

        et_Name=v.findViewById(R.id.et_LoginRelativeName);
        et_Surname=v.findViewById(R.id.et_LoginRelativeSurname);
        et_Middlename=v.findViewById(R.id.et_LoginRelativeMiddlename);

        btn_Confirm=v.findViewById(R.id.btn_LoginRelativeReady);
        btn_Confirm.setOnClickListener(oclBtn);
        btn_SelectImage=v.findViewById(R.id.btn_loadImgRelative);
        btn_SelectImage.setOnClickListener(oclBtn);
        return v;
    }


}
