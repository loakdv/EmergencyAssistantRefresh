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

public class Fragment_LoginEnter extends Fragment {



    //Объявляем интерфейс для связи с основной активностью Activity_Login
    Fragment_Login_FirstSelect.changeLoginFragment intLoginFrag;

    //Кнопка входа в аккаунт
    Button btn_EnterLog;



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
                        intLoginFrag.startMainAct();
                        break;
                }
            }
        };

        //Инициализируем кнопку и припысываем листенер
        btn_EnterLog=v.findViewById(R.id.btn_EnterLog);
        btn_EnterLog.setOnClickListener(oclBtn);
        return v;
    }
}
