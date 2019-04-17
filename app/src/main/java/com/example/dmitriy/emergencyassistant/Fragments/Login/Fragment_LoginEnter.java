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

import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;

public class Fragment_LoginEnter extends Fragment {



    //Объявляем интерфейс для связи с основной активностью Activity_Login
    private Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;

    //Кнопка входа в аккаунт
    private Button btn_EnterLog, btnBack;

    private EditText etEnterEmail;
    private EditText etEnterPassword;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Инициализируем интерфейс
        intLoginFrag=(Fragment_Login_FirstSelect.changeLoginFragment) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login_enter, container, false);
        //Листенер нажатий
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_EnterLog:
                        //Вызываем метод из интерфейса

                        Helper_CreateProfile.EMAIL =etEnterEmail.getText().toString();
                        Helper_CreateProfile.PASSWORD =etEnterPassword.getText().toString();

                        intLoginFrag.startMainAct(true);
                        break;

                    case R.id.btn_login_enter_back:
                        getActivity().onBackPressed();
                        break;
                }
            }
        };

        //Инициализируем кнопку и припысываем листенер
        btn_EnterLog=v.findViewById(R.id.btn_EnterLog);
        btn_EnterLog.setOnClickListener(oclBtn);

        btnBack=v.findViewById(R.id.btn_login_enter_back);
        btnBack.setOnClickListener(oclBtn);

        etEnterEmail=v.findViewById(R.id.et_Enter_Email);
        etEnterPassword=v.findViewById(R.id.et_Enter_Password);
        return v;
    }
}
