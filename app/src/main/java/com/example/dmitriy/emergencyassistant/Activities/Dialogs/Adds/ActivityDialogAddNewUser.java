package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJONeedy;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJOProfile;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJORelative;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


  /*
    Диалоговое окно для меню добавления подключенных
     */


public class ActivityDialogAddNewUser extends AppCompatActivity {




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
    private DataBaseAppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    //Список для хранения полученных с сервера пользователей и юзеров
    private List<POJOProfile> userList;
    private List<POJONeedy> needyList;
    private List<POJORelative> relativeList;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newrelative);

        //initializeFirebase();

        getIntentExtras();

        //Инициализируем базу данных
        initializeDataBase();


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
        etNeedyId = findViewById(R.id.et_IDRelatDoc);

        btnFinal = findViewById(R.id.btn_FinalAddRelat);
        btnFinal.setOnClickListener(oclBtn);
        btnCancel = findViewById(R.id.btn_CancelAddRelat);
        btnCancel.setOnClickListener(oclBtn);
    }



    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }



    //Метод который добавляет пользователя в базу данных доктора
    private void addUserDoctor(){
        if(etNeedyId.getText().toString().isEmpty()){

            makeToast("Вы не можете оставить поле пустым!");

        }
        else {

            //Проверяем на наличие этого пользователя уже в базе
            if(dataBase.dao_relative_addedNeedy().getById
                    (etNeedyId.getText().toString())==null){

                //Получаем id из поля ввода
                String id = etNeedyId.getText().toString();

                //Получаем id текущего пользователя
                long relativeID = dataBase.dao_relative().getRelative().getId();

                //Вставляем новую запись в БД
                //loadNeedyUserFromFirebase(id, relativeID);

                //Временное решение!
                finish();

            }
            else {
                makeToast("Пользователь с таким id уже существует!");
            }
        }
    }






    //Метод который добавляет пользователя в базу данных обычного пользователя

    /*
    Те же действия что и в addUserDoctor
     */
    private void addUserSimple(){
        if(etNeedyId.getText().toString().isEmpty()){

            makeToast("Вы не можете оставить поле пустым!");

        }
        else {
            /*
            if(dataBase.dao_added_relatives().getById(
                    etNeedyId.getText().toString())==null){

                String id=etNeedyId.getText().toString();
                long needy_id= dataBase.dao_needy().getNeedy().getId();

                //loadSimpleUser(id, needyId);
                //Временное решение!
                finish();
            }
            else {
                makeToast("Пользователь с таким id уже существует!");
            }
             */
        }
    }





    private void loadSimpleUser(final String id, final long needyID){



        //Остаток от Firebase (временный)
        /*
        Инициализируем лист с профилем
        В него будет кидаться !ОДИН! объект профиля,
        и из него уже будем получать данные

                userList=new ArrayList<POJOProfile>();


                databaseReference.child("Users").child(id).child("Profile").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Получение профиля мы осуществляем с помощью итерации

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            //Получили профиль, добавили его в список
                            userList.add(child.getValue(POJOProfile.class));
                        }


                        if(!userList.isEmpty() && userList.get(0).getType() != 0){
                            POJOProfile profile=userList.get(0);
                            if(profile.getType()==1){
                                loadRelativeExtra(id, profile.getName(), profile.getSurname(), profile.getMiddlename(), needyID);

                            }

                        }
                        else {
                            makeToast("Такого пользователя не существует," +
                                    " или он не нуждается в помощи!");
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                */

    }






    private void loadRelativeExtra(final String id, final String name, final String surname, final String middlename, final long needyID){

        //Остаток от Firebase (временный)
        /*
        relativeList=new ArrayList<POJORelative>();

        databaseReference.child("Users").child(id).child("Relative").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Получение профиля мы осуществляем с помощью итерации

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    //Получили профиль, добавили его в список
                    relativeList.add(child.getValue(POJORelative.class));
                }

                //Если такой пользователь был найден, то добавляем его в локальную базу данных
                if(!relativeList.isEmpty()){
                    POJORelative relative=relativeList.get(0);
                    dataBase.dao_added_relatives().insert(new EntityNeedyAddedRelatives(name,
                            surname, middlename, relative.isDoctor(), needyID, id));


                    finish();

                }
                else {
                    makeToast("Такого пользователя не существует," +
                            " или он не нуждается в помощи!");

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }





    private void loadNeedyUserFromFirebase(final String id, final long relativeId){


        //остаток от Firebase (временный)
        /*
        Инициализируем лист с профилем
        В него будет кидаться !ОДИН! объект профиля,
        и из него уже будем получать данные

        userList=new ArrayList<POJOProfile>();
        needyList=new ArrayList<POJONeedy>();


        databaseReference.child("Users").child(id).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Получение профиля мы осуществляем с помощью итерации

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    userList.add(child.getValue(POJOProfile.class));
                }

                //Если пользователь был найден, и он является Needy - добавляем его
                if(!userList.isEmpty() && userList.get(0).getType() == 0){
                    POJOProfile profile=userList.get(0);
                    loadNeedyExtra(id, profile.getName(), profile.getSurname(), profile.getMiddlename(), relativeId);

                }
                else {

                    makeToast("Такого пользователя не существует," +
                            " или он не нуждается в помощи!");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */
    }





    private void loadNeedyExtra(final String id, final String name, final String surname,
                                final String middlename, final long relativeID){

        //остаток от Firebase (временный)
        /*
                databaseReference.child("Users").child(id).child("Needy").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Получение профиля мы осуществляем с помощью итерации

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            //Получили профиль, добавили его в список
                            needyList.add(child.getValue(POJONeedy.class));
                        }

                        final String lInfo=needyList.get(0).getInfo();

                        dataBase.dao_relative_addedNeedy().insert(new EntityRelativeAddedNeedy(name,
                                surname, middlename, lInfo, relativeID, id));



                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
         */

    }





    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }





    private void getIntentExtras(){
        //Достаём переменную которая устанавливается при создании активности
        boolean extraIsDoctor=getIntent().getBooleanExtra("IS_DOCTOR", false);
        int extraType=getIntent().getIntExtra("TYPE", 0);

        selectedType=extraType;
        isDoctor=extraIsDoctor;
    }





    private void makeToast(String text){
        Toast.makeText(ActivityDialogAddNewUser.this, text, Toast.LENGTH_SHORT).show();
    }









}
