/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 7:37 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.adds;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.elements.ElementVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.InterfaceInitialize;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;

import java.util.List;

/*
Диалоговое окно для добавления соц. работника
 */
public class ActivityDialogAddVolunteer extends AppCompatActivity implements
        AdapterVolunteerForSelect.CallbackButton,
        InterfaceInitialize,
        InterfaceDataBaseWork {

    //Элементы экрана
    private Button
            btnCancel,
            btnConfirm;

    //Поле для ввода добавляемого Id
    private EditText etID;

    //Rv для отображения уже готовых соц. работников
    private RecyclerView recyclerView;

    //Локальная база данных приложения
    private DataBaseAppDatabase dataBase;

    //Элементы нужные для списка
    private List<ElementVolunteerForSelect> volunteerForSelectList;
    private AdapterVolunteerForSelect adapterVolunteerForSelect;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_volunteer);
        initializeDataBase();
        initializeScreenElements();
        initializeRecycleView();
    }



    //Инициализируем элементы экрана с помощью метода из интерфейса
    @Override
    public void initializeScreenElements() {

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerCancel:
                        finish();
                        break;
                    case R.id.btn_VolunteerConfirm:
                        findVolunteer(etID.getText().toString());
                        break;
                }
            }
        };

        btnCancel=findViewById(R.id.btn_VolunteerCancel);
        btnCancel.setOnClickListener(oclBtn);

        btnConfirm=findViewById(R.id.btn_VolunteerConfirm);
        btnConfirm.setOnClickListener(oclBtn);

        etID=findViewById(R.id.et_VolunteerID);
    }




    //Метод который инициализирует базу данных
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    private void initializeRecycleView(){
        recyclerView = findViewById(R.id.rv_Social_Volunteers);
        adapterVolunteerForSelect = new AdapterVolunteerForSelect(getBaseContext(), volunteerForSelectList, this);
        recyclerView.setAdapter(adapterVolunteerForSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }



    @Override
    public void initializeList() {}

    private void findVolunteer(String id){
        finish();
    }

    @Override
    public void selectVolunteer(String id) {
        findVolunteer(id);
    }

}
