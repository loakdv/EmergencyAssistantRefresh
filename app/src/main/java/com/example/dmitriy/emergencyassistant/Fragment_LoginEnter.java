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

public class Fragment_LoginEnter extends Fragment {



    //Объявляем интерфейс для связи с основной активностью Activity_Login
    private Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;

    //Кнопка входа в аккаунт
    private Button btn_EnterLog;

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

                        Helper_CreateProfile.phonenumber=etEnterEmail.getText().toString();
                        Helper_CreateProfile.password=etEnterPassword.getText().toString();

                        intLoginFrag.startMainAct(true);
                        break;
                }
            }
        };

        //Инициализируем кнопку и припысываем листенер
        btn_EnterLog=v.findViewById(R.id.btn_EnterLog);
        btn_EnterLog.setOnClickListener(oclBtn);

        etEnterEmail=v.findViewById(R.id.et_Enter_Email);
        etEnterPassword=v.findViewById(R.id.et_Enter_Password);
        return v;
    }
}
