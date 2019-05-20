package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds.ActivityDialogAddNewUser;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Loading.ActivityDialogLoading;
import com.example.dmitriy.emergencyassistant.Adapters.Needy.AdapterNeedyAddedRelatives;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_Relatives;

import java.util.ArrayList;
import java.util.List;

/*
    Диалоговое окно для просмотра подключенных врачей/родственников
     */

public class ActivityDialogUsers extends AppCompatActivity implements AdapterNeedyAddedRelatives.CallBackButtons {



    //Кнопки для взаимодействия
    private Button btnCancel;
    private Button btnAdd;
    private Button btnFinal;

    private DataBase_AppDatabase dataBase;

    //Список для списка отображения
    private  List<Entity_Added_Relatives> users=new ArrayList<Entity_Added_Relatives>();
    //Адаптер для списка подкл. пользователей
    private AdapterNeedyAddedRelatives adapterUsers;
    //Элемент списка для просмотра
    private RecyclerView recyclerViewUsers;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDataBase();

        setContentView(R.layout.activity_dialog_relatives);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_Cancel_Relatives:
                        //Завершаем активность
                        finish();
                        break;
                    case R.id.btn_finalRelative:
                        //Завершаем активность
                        finish();
                        break;
                    case R.id.btn_AddNewRelative:
                        //Открываем диалоговое окно для самого добавления юзеров
                        Intent i=new Intent(getApplicationContext(),
                                ActivityDialogAddNewUser.class);
                        i.putExtra("TYPE", 2);
                        startActivity(i);
                        break;
                }
            }
        };

        //Инициализируем элементы
        btnCancel=findViewById(R.id.btn_Cancel_Relatives);
        btnCancel.setOnClickListener(oclBtn);
        btnAdd=findViewById(R.id.btn_AddNewRelative);
        btnAdd.setOnClickListener(oclBtn);
        btnFinal=findViewById(R.id.btn_finalRelative);
        btnFinal.setOnClickListener(oclBtn);
        recyclerViewUsers=findViewById(R.id.rv_Relatives);

        initializeList();
        initializeRecycleView();
    }




    public void initializeList(){
        if(!(dataBase.dao_added_relatives().getAll()==null)){
            users=dataBase.dao_added_relatives().getAll();
        }
    }




    public void initializeRecycleView(){
        //Адаптер
        adapterUsers=new AdapterNeedyAddedRelatives(getApplicationContext(),
                users,
                this);
        recyclerViewUsers.setAdapter(adapterUsers);
        recyclerViewUsers.setLayoutManager(new
                LinearLayoutManager(getApplicationContext())
        );
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    private void seeLoading(){
        Intent i = new Intent(getApplicationContext(), ActivityDialogLoading.class);
        startActivity(i);
    }



    @Override
    public void deleteUser(Entity_Added_Relatives relative) {
        dataBase.dao_added_relatives().delete(relative);
        initializeList();
        initializeRecycleView();
    }




    @Override
    protected void onStart() {
        super.onStart();
        onResume();
    }




    @Override
    protected void onResume() {
        super.onResume();
        initializeList();
        initializeRecycleView();}

}
