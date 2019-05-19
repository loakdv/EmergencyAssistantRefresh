package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_See_Task;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Signal;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_Main;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_NeedyList;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_Settings;
import com.example.dmitriy.emergencyassistant.Fragments.Volunteer.Fragment_Volunteer_TaskList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer_AddedNeedy;
import com.example.dmitriy.emergencyassistant.Services.Service_BackGround;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Активность раздела соц. работника
 */
public class Activity_Volunteer extends AppCompatActivity implements Fragment_Volunteer_Main.onChangeVolunFrag, Fragment_Volunteer_NeedyList.onTaskClick,
        Fragment_Volunteer_TaskList.OnTasksClick {

    //Фрагменты для переключения
    private Fragment_Volunteer_Main fragmentVolunteerMain;
    private Fragment_Volunteer_Settings fragmentVolunteerSettings;
    private Fragment_Volunteer_TaskList fragmentVolunteerTaskList;

    //Транзакция
    private FragmentTransaction fTran;

    //Firebase объекты
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    /*
    Список необходим для добавления в него
    добавленных с сервера сигналов
     */
    private List<Firebase_Signal> tasks;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        initializeFragments();
        setFragment();
    }




    private void initializeFragments(){
        //Инициализируем фрагменты
        fragmentVolunteerMain = new Fragment_Volunteer_Main();
        fragmentVolunteerSettings = new Fragment_Volunteer_Settings();

    }




    private void setFragment(){
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }


    private void startSignalsService(){
        startService(new Intent(this, Service_BackGround.class));
    }


    @Override
    public void setMain() {
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }




    @Override
    public void setSettings() {
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerSettings);
        fTran.commit();
    }




    @Override
    public void setTasks(Entity_Volunteer_AddedNeedy needy, String date) {
        fragmentVolunteerTaskList = new Fragment_Volunteer_TaskList(needy.getNeedyId(), date, ""+needy.getSurname()+" "+needy.getName()+" "+needy.getMiddlename());
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTaskList);
        fTran.commit();
    }




    @Override
    public void onTaskClick(Entity_Volunteer_AddedNeedy needy, String date) {
        setTasks(needy, date);
    }


    /*
   С помощью этого метода мы получаем из БД последние
   сигналы и выводим их на экран
    */
    private void getLastSignal(){


        databaseReference.child("Users").child(user.getUid()).child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tasks = new ArrayList<Firebase_Signal>();

                //Получение профиля мы осуществляем с помощью итерации

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    tasks.add(child.getValue(Firebase_Signal.class));
                }

                //Если список не пустой, достаём из него данные и
                //выводим их на экран
                if(!tasks.isEmpty()){

                    //Получили объект профиял
                    Firebase_Signal task = tasks.get(0);

                    //Показываем окно с сигналом
                    seeSignalWindow(task.getInitials(), task.getType());

                    //Очищаем серверную БД
                    removeTasks();

                    //Запускаем сервис отслеживания сигналов
                    startSignalsService();
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void seeSignalWindow(String initials, int type){
        Intent i = new Intent(Activity_Volunteer.this, Activity_See_Task.class);
        i.putExtra("Initials", initials);
        i.putExtra("Type", type);

        startActivity(i);
    }

    private void removeTasks(){
        databaseReference.child("Users").child(user.getUid()).child("Tasks").removeValue();
    }


    private void initializeFirebase(){

        //Инициализируем аккаунт устройства
        mAuth = FirebaseAuth.getInstance();

        //Инициализируем базу данных FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Инициализируем текущего юзера
        user = mAuth.getCurrentUser();
    }


    @Override
    public void goBack() {
        setMain();
    }


}
