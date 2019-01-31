package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Activity_Dialog_Users extends AppCompatActivity {

    /*
    Диалоговое окно для просмотра подключенных врачей/родственников
     */

    //Кнопки для взаимодействия
    Button btn_Cancel;
    Button btn_Add;
    Button btn_Final;


    //Список для списка отображения
    static ArrayList<Added_User> users=new ArrayList<Added_User>();
    //Адаптер для списка подкл. пользователей
    Adapter_UsersListRecyclerView a_users;
    //Элемент списка для просмотра
    static RecyclerView rv_users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        Intent i=new Intent(getApplicationContext(), Activity_Dialog_AddNewUser.class);
                        startActivity(i);
                        break;
                }
            }
        };

        //Инициализируем элементы
        btn_Cancel=findViewById(R.id.btn_Cancel_Relatives);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Add=findViewById(R.id.btn_AddNewRelative);
        btn_Add.setOnClickListener(oclBtn);
        btn_Final=findViewById(R.id.btn_finalRelative);
        btn_Final.setOnClickListener(oclBtn);

        //Спик пользователей
        rv_users=findViewById(R.id.rv_Relatives);
        //Адаптер
        a_users=new Adapter_UsersListRecyclerView(getApplicationContext(), users);
        rv_users.setAdapter(a_users);
        rv_users.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    /*
    Специальные статические методы для того что бы не заморачиваться с
    добавлением элементов в список
     */

    /*
    Метод добавления пользователей
    Принимает на вход имя, номер и id
     */
    public static void addUser( String id){
        users.add(new Added_User(id));
        rv_users.getAdapter().notifyDataSetChanged();
    }


    /*
    Метод удаления номера из списка
    Принимает на вход значение индекса которое надо удалить
     */
    public static void deleteUser(int index){
       users.remove(index);
       rv_users.getAdapter().notifyDataSetChanged();
    }
}
