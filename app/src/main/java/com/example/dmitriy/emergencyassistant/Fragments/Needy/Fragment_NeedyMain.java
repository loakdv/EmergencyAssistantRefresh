package com.example.dmitriy.emergencyassistant.Fragments.Needy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;

public class Fragment_NeedyMain extends Fragment  {

    private FragmentTransaction fTran;

    //Основные кнопки на главном экране "пациента"
    private Button btnCalls, btnSos, btnHome, btnShop, btn_State;

    //Создаём интерфейс для связи с активностью "пациента"
    public interface onSomeEventListener {
        //Методы которые должны выполниться внутри активности
         void changeFrag();
         void sendSos();
         void sendHouse(int type);
         void sendExtra();
         void checkState();

    }

    //Создаём экземпляр интерфейса
    private onSomeEventListener someEventListener;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Присваиваем листенеру эвента сонтекст(активность), которая должна исполнять эвент
            someEventListener = (onSomeEventListener) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needymain, container, false);

        //Инициализация кнопок
        btnCalls=v.findViewById(R.id.btnCall);
        btnSos=v.findViewById(R.id.btnSOS);
        btnHome=v.findViewById(R.id.btnHome);
        btnShop=v.findViewById(R.id.btnShop);
        btn_State=v.findViewById(R.id.btn_CheckState);


        //Листенер кнопок
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //При нажатии на кнопку срабатывают методы в интерфейсе
                    //Которые потом срабатывают в активности
                    case R.id.btnCall:
                        someEventListener.changeFrag();
                        break;
                    case R.id.btnSOS:
                        someEventListener.sendSos();
                        break;
                    case R.id.btnHome:
                        someEventListener.sendHouse(0);
                        break;
                    case R.id.btnShop:
                        someEventListener.sendHouse(1);
                        break;
                    case R.id.btn_CheckState:
                        someEventListener.checkState();
                        break;
                }
            }
        };

        //Установка листенеров
        btnCalls.setOnClickListener(oclBtn);
        btnShop.setOnClickListener(oclBtn);
        btnHome.setOnClickListener(oclBtn);
        btnSos.setOnClickListener(oclBtn);
        btn_State.setOnClickListener(oclBtn);
        return v;
    }

}
