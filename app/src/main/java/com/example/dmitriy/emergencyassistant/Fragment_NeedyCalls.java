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

public class Fragment_NeedyCalls extends Fragment   {



    //Объявляем интерфейс как поле
    Fragment_NeedyMain.onSomeEventListener someEventListener;

    //Получаем контекст для интерфейса
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        someEventListener = (Fragment_NeedyMain.onSomeEventListener) context;
    }

    //Кнопка для перехода к другому фрагменту Main
    Button btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_seeneedycalls, container, false);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnBack:
                        //Меняем основной фрагмент активности
                        someEventListener.changeFrag();
                        break;
                }
            }
        };
        btnBack=v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(oclBtn);



        return v;
    }






}
