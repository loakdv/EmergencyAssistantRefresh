package com.example.dmitriy.emergencyassistant.activities.based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.activities.dialogs.info.ActivityDialogSeeTask;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOSignal;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerMain;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerNeedyList;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerSettings;
import com.example.dmitriy.emergencyassistant.fragments.volunteer.FragmentVolunteerTaskList;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.entities.user.volunteer.EntityVolunteerAddedNeedy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/*
Активность раздела соц. работника
 */
public class ActivityVolunteer extends AppCompatActivity implements FragmentVolunteerMain.onChangeVolunFrag, FragmentVolunteerNeedyList.onTaskClick,
        FragmentVolunteerTaskList.OnTasksClick {

    //Фрагменты для переключения
    private FragmentVolunteerMain fragmentVolunteerMain;
    private FragmentVolunteerSettings fragmentVolunteerSettings;
    private FragmentVolunteerTaskList fragmentVolunteerTaskList;

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
    private List<POJOSignal> tasks;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        initializeFragments();
        setFragment();
    }




    private void initializeFragments(){
        //Инициализируем фрагменты
        fragmentVolunteerMain = new FragmentVolunteerMain();
        fragmentVolunteerSettings = new FragmentVolunteerSettings();

    }




    private void setFragment(){
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.add(R.id.frame_VolunteerMain, fragmentVolunteerMain);
        fTran.commit();
    }


    private void startSignalsService(){
        //startService(new Intent(this, ServiceBackGround.class));
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
    public void setTasks(EntityVolunteerAddedNeedy needy, String date) {
        fragmentVolunteerTaskList = new FragmentVolunteerTaskList(needy.getNeedyId(), date, ""+needy.getSurname()+" "+needy.getName()+" "+needy.getMiddlename());
        fTran = getSupportFragmentManager().beginTransaction();
        fTran.replace(R.id.frame_VolunteerMain, fragmentVolunteerTaskList);
        fTran.commit();
    }




    @Override
    public void onTaskClick(EntityVolunteerAddedNeedy needy, String date) {
        setTasks(needy, date);
    }


    /*
   С помощью этого метода мы получаем последние
   сигналы и выводим их на экран
    */
    private void getLastSignal(){

    }

    private void seeSignalWindow(String initials, int type){
        Intent i = new Intent(ActivityVolunteer.this, ActivityDialogSeeTask.class);
        i.putExtra("Initials", initials);
        i.putExtra("Type", type);

        startActivity(i);
    }

    private void removeTasks(){
        //databaseReference.child("Users").child(user.getUid()).child("Tasks").removeValue();
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
