package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_Dialog_SendedSignal;
import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_Dialog_StateCheck;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Signal;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Task;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer_Needy;
import com.example.dmitriy.emergencyassistant.Fragments.Needy.Fragment_NeedyCalls;
import com.example.dmitriy.emergencyassistant.Fragments.Needy.Fragment_NeedyMain;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_Relatives;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_Volunteer;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.Services.Broadcast_AlarmState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Activity_Needy extends AppCompatActivity implements Fragment_NeedyMain.onSomeEventListener {

    /*
      Данное активити используется для "пациента"
    */


    private List<Entity_Added_Relatives> users=new ArrayList<Entity_Added_Relatives>();

    private DataBase_AppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    private Fragment_NeedyMain fragmentMain;
    private Fragment_NeedyCalls fragmentCalls;
    private FragmentTransaction fragmentTransaction;

    private boolean main=true;
    private boolean checkState;

    private List<String> ids;




    //OnCreate
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);

        initializeFirebase();
        initializeDataBase();

        initializeList();

        setFragment();
        getFromIntent();

        startReceiver(9);
        startReceiver(12);
        startReceiver(15);
        startReceiver(18);
        startReceiver(21);
    }


    //Инициализация базы данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    /*
    Метод который изначально устанавливает главный экран
    Он в общем полезен для определения нужного фрагмента
     */
    private void setFragment(){
        fragmentMain=new Fragment_NeedyMain();
        fragmentCalls=new Fragment_NeedyCalls();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();

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
        main=!main;
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
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

        Entity_Profile profile=dataBase.dao_profile().getProfile();

        for(int i=0; i<users.size(); i++){

            Log.i("SIGNAL", "SEND SIGNAL: "+users.get(i).getId());


            databaseReference.child("Users").child(users.get(i).getId()).child("Tasks").push().setValue(

                    new Firebase_Signal(profile.getSurname()+" "+profile.getName()+" "+profile.getMiddlename(), profile.getId(), 0));

        }

        Intent signal=new Intent(this,
                Activity_Dialog_SendedSignal.class);
        startActivity(signal);

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




    @Override
    public void sendExtra() {

    }




    @Override
    public void sendHouse(int type) {
        if (dataBase.dao_needy_volunteer().getVolunteer() != null){
            final Entity_Needy_Volunteer volunteer = dataBase.dao_needy_volunteer().getVolunteer();

            final Date phoneDate = new Date();
            final SimpleDateFormat sdfCal=new SimpleDateFormat("dd-MM-yyyy");
            final SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm");
            final Entity_Profile profile = dataBase.dao_profile().getProfile();

            databaseReference.child("Users").child(volunteer.getId()).child("Tasks").child(sdfCal.format(phoneDate)).child("Profiles").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                        ids=new ArrayList<>();

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            ids.add(child.getValue(String.class));
                        }
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



            sendHouseToServer(type);

        }

    }


    private void sendHouseToServer(int type){

        Entity_Profile profile = dataBase.dao_profile().getProfile();
        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat sdfCal=new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfTime=new SimpleDateFormat("HH-mm");

        databaseReference.child("Users").child(profile.getId()).child("Tasks").child("Task").child(sdfCal.format(date)).child(sdfTime.format(date)).
                push().setValue(new Firebase_Task(profile.getId(), sdfTime.format(date), type, sdfCal.format(date)));

        databaseReference.child("Users").child(profile.getId()).child("Tasks").child("Task").child(sdfCal.format(date)).child("Times").
                push().setValue(sdfTime.format(date));

        Intent signal=new Intent(this,
                Activity_Dialog_SendedSignal.class);
        startActivity(signal);
    }



    @Override
    public void checkState() {
        Intent state=new Intent(this,
                Activity_Dialog_StateCheck.class);
        startActivity(state);
    }




    private void startCheckState(){
        Intent state=new Intent(this, Activity_Dialog_StateCheck.class);
        startActivity(state);
        checkState=false;
    }




    private void getFromIntent(){
        boolean extraCheckState=getIntent().getBooleanExtra("check_state", false);
        checkState=extraCheckState;
        if(checkState){
            startCheckState();
        }

    }


    //Инициализация листа
    private void initializeList(){
        if(!(dataBase.dao_added_relatives().getAll()==null)){
            users=dataBase.dao_added_relatives().getByDoc(false);
        }
    }


    private void startReceiver(int h){
        Log.i("NOTIF", "===== "+h+" =====");

        Log.i("NOTIF", "SET ALARM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, 00);

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Date nowDate = calendar.getTime();

        Intent intent = new Intent("SEND_STATE_NOTIF");
        intent.setClass(Activity_Needy.this, Broadcast_AlarmState.class);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("time", h);

        Log.i("NOTIF", "PENDING INTENT");


        long time24h = 24*60*60*1000;

        switch (h){
            case 9:
                PendingIntent pAlarm0 = PendingIntent.getBroadcast(Activity_Needy.this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, nowDate.getTime(),2000 ,pAlarm0);
                break;
            case 12:
                PendingIntent pAlarm1 = PendingIntent.getBroadcast(Activity_Needy.this, 1, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, nowDate.getTime(),2000 ,pAlarm1);
                break;
            case 15:
                PendingIntent pAlarm2= PendingIntent.getBroadcast(Activity_Needy.this, 2, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, nowDate.getTime(),2000 ,pAlarm2);
                break;
            case 18:
                PendingIntent pAlarm3 = PendingIntent.getBroadcast(Activity_Needy.this, 3, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, nowDate.getTime(),2000 ,pAlarm3);
                break;
            case 21:
                PendingIntent pAlarm4 = PendingIntent.getBroadcast(Activity_Needy.this, 4, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, nowDate.getTime(),2000 ,pAlarm4);
                break;

        }


        Log.i("NOTIF", "alarm.set()");

    }




}
