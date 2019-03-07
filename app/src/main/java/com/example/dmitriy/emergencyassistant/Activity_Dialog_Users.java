package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_Users extends AppCompatActivity implements  Adapter_AddedRelatives.CallBackButtons {

    /*
    Диалоговое окно для просмотра подключенных врачей/родственников
     */

    //Кнопки для взаимодействия
    Button btnCancel;
    Button btnAdd;
    Button btnFinal;

    DataBase_AppDatabase dataBase;

    //Список для списка отображения
    List<Entity_Added_Relatives> users=new ArrayList<Entity_Added_Relatives>();
    //Адаптер для списка подкл. пользователей
    Adapter_AddedRelatives adapterUsers;
    //Элемент списка для просмотра
    RecyclerView recyclerViewUsers;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewUsers=findViewById(R.id.rv_Relatives);
        new Loading().execute();

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
                                Activity_Dialog_AddNewUser.class);
                        i.putExtra("type", 2);
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
        adapterUsers=new Adapter_AddedRelatives(getApplicationContext(),
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

    class Loading extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            initializeDataBase();
            initializeList();
            initializeRecycleView();
            return null;
        }
    }

}
