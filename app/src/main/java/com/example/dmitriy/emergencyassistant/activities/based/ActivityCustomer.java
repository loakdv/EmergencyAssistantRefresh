package com.example.dmitriy.emergencyassistant.activities.based;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogSendSignal;
import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogStateCheck;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerCalls;
import com.example.dmitriy.emergencyassistant.fragments.customer.FragmentCustomerMain;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.EntityUser;
import com.example.dmitriy.emergencyassistant.services.ServiceAlarmState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

 /*
      Данное активити используется для "пациента"
    */


public class ActivityCustomer extends AppCompatActivity implements FragmentCustomerMain.onSomeEventListener{


    //База данных
    private DataBaseAppDatabase dataBase;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    //Фрагменты
    private FragmentCustomerMain fragmentMain;
    private FragmentCustomerCalls fragmentCalls;
    private FragmentTransaction fragmentTransaction;

    //Переменная для смены фрагмента
    private boolean main = true;

    //Переменная для проверки состояния
    private boolean checkState;

    //В эти списки мы кидаем полученные с сервера данные
    private List<String> ids;



    //OnCreate
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);

        //initializeFirebase();
        initializeDataBase();

        initializeList();

        setFragment();
        getFromIntent();

        //startService();
        //checkSignals();


    }

    private void checkSignals(){
        int type = getIntent().getIntExtra("signal type", 100);
        if(!(type == 100)){
            sendHelpSignal(type);
        }
    }



    //Инициализация базы данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    private void initializeFirebase(){

        //Остаток от Firebase
        /*
        //Инициализируем аккаунт устройства
        mAuth = FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
         */
    }




    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFragment(){
        fragmentMain = new FragmentCustomerMain();
        fragmentCalls = new FragmentCustomerCalls();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        /*
        Проверяем переменную main
        если true, то ставим гланый экран
         */
        if(main){
            fragmentTransaction.add(R.id.fragContNeedy, fragmentMain); }
        //Если false, то ставим экран звонков
        else{
            fragmentTransaction.add(R.id.fragContNeedy, fragmentCalls); }

        /*
        Добавляем в бэкстэк для того что бы можно
        было вернуться к фрагменту после нажатия
        физической кнопки "назад"
         */
        fragmentTransaction.addToBackStack(null);
        //Применяем транзакцию
        fragmentTransaction.commit();

    }




    /*
    Метод используемый для смены фрагмента во время
    работы самой активности
    Сначала он ставит противоположное значение переменной main
    и уже от этой переменной отталкивается в дальнейшем
    (Происходит почти тоже самое что в методе setFragment, только
    используется replace)
     */

    /*
    Методы интерфейса, что бы можно было связаться с этой активностью из фрагментов
    Как только фрагмент использует эвент, то он переходит к этой активности
    и тут уже срабатывают нужные методы;
     */
    @Override
    public void changeFrag() {
        main = !main;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.fragContNeedy, fragmentCalls);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    //Перебираем список пользователей кому можно отправлять сигнал о помощи
    private void sendSignalSosToUsers(){

        EntityUser profile = dataBase.dao_user().getProfile();

        //Остаток от Firebase(Временный)
        /*
        for(int i = 0; i < users.size(); i++){

            databaseReference.child("Users").child(users.get(i).getId()).child("Tasks").push().setValue(
                    new POJOSignal(profile.getSurname()+" "+profile.getName()+" "+
                            profile.getMiddlename(), profile.getId(), 0));
        }

        Intent signal = new Intent(this,
                ActivityDialogSendSignal.class);
        startActivity(signal);
         */

    }



    /*
    Достаются загруженные данные из класса Needy
    Описание сигналов есть в этом классе
    В зависимости от выбранного значения, происходят разные события
     */
    @Override
    public void sendSos() {
        sendSignalSosToUsers();
    }




    /*
    Метод который отправляет сигнал о помощи соц. работникам
     */
    @Override
    public void sendHelpSignal(int type) {


        //Остаток от Firebase временный

        /*

        Проверяем, есть ли подключённый соц. работник

        if (dataBase.dao_needy_volunteer().getVolunteer() != null){
            final EntityCustomerConnectedVolunteer volunteer = dataBase.dao_needy_volunteer().getVolunteer();

            //Для формирования даты и времени
            final Date phoneDate = new Date();
            final SimpleDateFormat sdfCal = new SimpleDateFormat("dd-MM-yyyy");
            final EntityUser profile = dataBase.dao_user().getProfile();


            Проверяем, есть ли данный пользователь в списке у соц. работника, что бы не перегружать БД

            databaseReference.child("Users").child(volunteer.getId()).child("Tasks").
                    child(sdfCal.format(phoneDate)).child("Profiles").addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                        ids = new ArrayList<>();

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            ids.add(child.getValue(String.class));
                        }

                        //Если такого нет, то кидаем ему в список наш id
                        if(!ids.contains(profile.getId())){
                            databaseReference.child("Users").child(volunteer.getId()).child("Tasks").child(sdfCal.format(phoneDate)).child("Profiles").
                                    push().setValue(profile.getId());
                        }
                    }
                    catch (Exception e){}

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //После этого уже отправляем сам таск на сервер
            sendHouseToServer(type);

        }
        */

    }


    private void sendHouseToServer(int type){

        EntityUser profile = dataBase.dao_user().getProfile();

        //Для формирования даты и времени
        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat sdfCal = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH-mm");


        //Остаток от Firebase (Временный)
        /*
        //Кидаем сам таск во ветку по времени(что бы можно было найти именно нужный таск)
        databaseReference.child("Users").child(profile.getId()).child("Tasks").child("Task").child(sdfCal.format(date)).child(sdfTime.format(date)).
                push().setValue(new POJOTask(profile.getId(), sdfTime.format(date), type, sdfCal.format(date)));

        //Кидаем значение в список времени, для того, что бы пройдясь по нему, мы смогли найти сами таски
        databaseReference.child("Users").child(profile.getId()).child("Tasks").child("Task").child(sdfCal.format(date)).child("Times").
                push().setValue(sdfTime.format(date));

         */


        Intent signal = new Intent(this,
                ActivityDialogSendSignal.class);
        startActivity(signal);
    }




    @Override
    public void checkState() {
        Intent state = new Intent(this,
                ActivityDialogStateCheck.class);
        startActivity(state);
    }




    private void startCheckState(){
        Intent state = new Intent(this, ActivityDialogStateCheck.class);
        startActivity(state);
        checkState = false;
    }



    //Получаем значение из интента, что бы открыть окно с выбором состояния
    private void getFromIntent(){
        boolean extraCheckState = getIntent().getBooleanExtra("check_state", false);
        checkState = extraCheckState;
        if(checkState){
            startCheckState();
        }

    }



    //Инициализация листа
    private void initializeList(){
        /*
        if(!(dataBase.dao_added_relatives().getAll() == null)){
            users=dataBase.dao_added_relatives().getByDoc(false);
        }
         */
    }

    private void startService(){
        startService(new Intent(this, ServiceAlarmState.class));
    }


    /*
    private void sendSosToVolunteer(){
        if(!dataBase.dao_needy_volunteer().getAll().isEmpty()){

            EntityUser profile = dataBase.dao_user().getProfile();
            EntityCustomerConnectedVolunteer volunteer = dataBase.dao_needy_volunteer().getVolunteer();

            databaseReference.child("Users").child(volunteer.getId()).child("Tasks").push().setValue(
                    new POJOSignal(profile.getSurname()+" "+profile.getName()+" "+
                            profile.getMiddlename(), profile.getId(), 0));
        }
    }
     */


}