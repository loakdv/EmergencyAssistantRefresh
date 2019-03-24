package com.example.dmitriy.emergencyassistant;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity_Dialog_AddNewUser extends AppCompatActivity {




     /*
    Диалоговое окно для меню добавления подключенных
     */




    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */
    private Button btnFinal;
    private Button btnCancel;
    private EditText etNeedyId;

    //Переменная необходимая для определения в
    // какой список именно добавлять пользователей
    //Список у врача/родственника или список у нуждающегося
    private boolean isDoctor;

    //Переменная нужна для определения типа пользователя
    //От неё идёт выбор, в какую БД кидать запись
    private int selectedType;

    //База данных для добавления записей
    private DataBase_AppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private List<Firebase_Profile> userList;
    private List<Firebase_Needy> needyList;
    private Firebase_Relative_AddedNeedy needy;

    private String name;
    private String surname;
    private String middlename;
    private String info;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);


        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();


        //Инициализируем базу данных
        initializeDataBase();
        //Достаём переменную которая устанавливается при создании активности
        boolean extraIsDoctor=getIntent().getBooleanExtra("doctor", false);
        int extraType=getIntent().getIntExtra("type", 0);
        selectedType=extraType;
        isDoctor=extraIsDoctor;

        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_FinalAddRelat:
                        if(selectedType==1){
                            addUserDoctor();
                        }
                        else if(selectedType==2){
                            addUserSimple();
                        }


                        break;
                    case R.id.btn_CancelAddRelat:
                        finish();
                        break;
                }
            }
        };

        //Инициализация элементов
        etNeedyId=findViewById(R.id.et_IDRelatDoc);

        btnFinal=findViewById(R.id.btn_FinalAddRelat);
        btnFinal.setOnClickListener(oclBtn);
        btnCancel=findViewById(R.id.btn_CancelAddRelat);
        btnCancel.setOnClickListener(oclBtn);
    }


    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    //Метод который добавляет пользователя в базу данных доктора
    private void addUserDoctor(){
        if(etNeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Вы не можете оставить поле пустым!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            //Проверяем на наличие этого пользователя уже в базе
            if(dataBase.dao_relative_addedNeedy().getById
                    (etNeedyId.getText().toString())==null){
                //Получаем ID из поля ввода
                String id=etNeedyId.getText().toString();
                //Получаем ID текущего пользователя
                long relativeID= dataBase.dao_relative().getRelative().getId();
                //Вставляем новую запись в БД

                loadNeedyUserFromFirebase(id, relativeID);


                //Завершаем активность
                Log.i("LOG_TAG", "--- New user added ---");

            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Пользователь с таким ID уже существует!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }




    //Метод который добавляет пользователя в базу данных обычного пользователя

    /*
    Те же действия что и в addUserDoctor
     */
    private void addUserSimple(){
        if(etNeedyId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Вы не можете оставить поле пустым!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if(dataBase.dao_added_relatives().getById(
                    etNeedyId.getText().toString())==null){
                String id=etNeedyId.getText().toString();
                long needy_id= dataBase.dao_needy().getNeedy().getId();
                dataBase.dao_added_relatives().
                        insert(new Entity_Added_Relatives(
                                "Имя", "Фамилия", "Отчество",
                        Math.random() < 0.5,needy_id, id));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Пользователь с таким ID уже существует!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void loadNeedyUserFromFirebase(final String id, final long relativeId){

        /*
        Инициализируем лист с профилем
        В него будет кидаться !ОДИН! объект профиля,
        и из него уже будем получать данные
        */
        userList=new ArrayList<Firebase_Profile>();
        needyList=new ArrayList<Firebase_Needy>();


        databaseReference.child("Users").child(id).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    userList.add(child.getValue(Firebase_Profile.class));
                }

                if(!userList.isEmpty() && userList.get(0).getType() == 0){
                    Firebase_Profile profile=userList.get(0);
                    loadNeedyExtra(id, profile.getName(), profile.getSurname(), profile.getMiddlename(), relativeId);
                }
                else {

                    Toast.makeText(Activity_Dialog_AddNewUser.this, "Такого пользователя не существует," +
                            " или он не нуждается в помощи!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void loadNeedyExtra(final String id, final String name, final String surname,
                                final String middlename, final long relativeID){
        databaseReference.child("Users").child(id).child("Needy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    needyList.add(child.getValue(Firebase_Needy.class));
                }

                final String lInfo=needyList.get(0).getInfo();
                dataBase.dao_relative_addedNeedy().insert(new Entity_Relative_AddedNeedy(name,
                        surname, middlename, lInfo, relativeID, id));

                Log.d("TAG", lInfo);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
