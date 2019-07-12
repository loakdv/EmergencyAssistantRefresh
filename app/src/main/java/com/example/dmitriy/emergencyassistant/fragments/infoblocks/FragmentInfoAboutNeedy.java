/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.fragments.infoblocks;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceOnUpdate;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

/*
Фрагмент необходимый для отображения информации
о пользователе у Соц. работника
*/

@SuppressLint("ValidFragment")
public class FragmentInfoAboutNeedy extends Fragment implements
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Интерфейс необходимый для обновления информации
    private InterfaceOnUpdate onUpdate;

    //Элементы экрана
    private Button btn_Delete;
    private TextView
            tv_Surname,
            tv_Name,
            tv_Middlename,
            tv_Info;

    private View v;

    //Поля необходимые для хранения передаваемых значений
    private String name = "none";
    private String surname = "none";
    private String middlename  = "none";
    private String info = "none";
    private String id = "none";

    //Объект БД
    private DataBaseAppDatabase dataBase;




    @SuppressLint("ValidFragment")
    public FragmentInfoAboutNeedy(String name, String surname, String middlename, String info, String id){
        this.name=name;
        this.surname=surname;
        this.middlename=middlename;
        this.info=info;
        this.id=id;
    }

    public FragmentInfoAboutNeedy(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          v=inflater.inflate(R.layout.fragment_see_needyinfo, container, false);
          initializeDataBase();
          initializeScreenElements();
          return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof InterfaceOnUpdate) {
            onUpdate = (InterfaceOnUpdate) getActivity();
        } else if (getParentFragment() instanceof InterfaceOnUpdate) {
            onUpdate = (InterfaceOnUpdate) getParentFragment();
        }

    }



    @Override
    public void initializeScreenElements() {
        tv_Surname=v.findViewById(R.id.tv_NeedyInfoSurname);
        tv_Name=v.findViewById(R.id.tv_NeedyInfoName);

        tv_Middlename=v.findViewById(R.id.tv_NeedyInfoMiddlename);
        tv_Info=v.findViewById(R.id.tv_NeedyInfoInfo);


        tv_Surname.setText(surname);
        tv_Name.setText(name);

        tv_Middlename.setText(middlename);
        tv_Info.setText(info);
    }



    @Override
    public void initializeDataBase(){
        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getContext(),
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }


    @Override
    public void initializeList() {}
}
