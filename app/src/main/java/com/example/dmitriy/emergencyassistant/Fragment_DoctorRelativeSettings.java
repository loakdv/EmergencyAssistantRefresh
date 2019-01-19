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

public class Fragment_DoctorRelativeSettings extends Fragment {



    //Объект интерфейса для смены рабочего фрагмента
    Fragment_DoctorRelativeMain.onChangeDocFrag changeFrag;

    //Инициализируем объект интерфейчас при присоединении
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeFrag=(Fragment_DoctorRelativeMain.onChangeDocFrag)context;
    }

    //Элементы в настройках
    Button btn_Back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_doctorrelatsettings, container, false);

        //Считывание нажатий
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_BackDoc:
                        changeFrag.changeFragment();
                        break;
                }
            }
        };
        //инициализация элементов
        btn_Back=v.findViewById(R.id.btn_BackDoc);
        btn_Back.setOnClickListener(oclBtn);
        return v;
    }
}
