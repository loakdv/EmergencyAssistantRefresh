/*
 *
 *  Created by Dmitry Garmyshev on 8/30/19 3:33 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 8/29/19 6:53 PM
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
import android.widget.LinearLayout;

import com.example.dmitriy.emergencyassistant.adapters.volunteer.AdapterVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.elements.ElementVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceDataBaseWork;
import com.example.dmitriy.emergencyassistant.interfaces.common.InterfaceInitialize;
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

    private Button
            btnCancel,
            btnConfirm;

    private EditText etID;
    private LinearLayout lnLoading;
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

        btnCancel = findViewById(R.id.btn_VolunteerCancel);
        btnCancel.setOnClickListener(oclBtn);

        btnConfirm = findViewById(R.id.btn_VolunteerConfirm);
        btnConfirm.setOnClickListener(oclBtn);

        etID = findViewById(R.id.et_VolunteerID);
        lnLoading = findViewById(R.id.lnADL);
    }




    //Метод который инициализирует базу данных
    @Override
    public void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "app_database").
                allowMainThreadQueries().build();
    }


    private void initializeRecycleView(){
        recyclerView = findViewById(R.id.rv_Social_Volunteers);
        adapterVolunteerForSelect = new AdapterVolunteerForSelect(getBaseContext(), volunteerForSelectList, this);
        recyclerView.setAdapter(adapterVolunteerForSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void findVolunteer(String id){
        finish();
    }

    @Override
    public void selectVolunteer(String id) {
        findVolunteer(id);
    }

}
