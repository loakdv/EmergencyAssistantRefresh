package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.ActivityDialogSeeTask;
import com.example.dmitriy.emergencyassistant.Firebase.FirebaseSignal;
import com.example.dmitriy.emergencyassistant.Fragments.Relative.Fragment_DoctorRelativeMain;
import com.example.dmitriy.emergencyassistant.Fragments.Relative.Fragment_DoctorRelativeSettings;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.Services.Service_BackGround;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
Активность которая отвечает за раздел доктора и обычного пользователя
 */

public class ActivityDoctorRelative extends AppCompatActivity implements Fragment_DoctorRelativeMain.onChangeDocFrag {


    //Фрагменты основного "вида" и настроек
    private Fragment_DoctorRelativeMain fragmentMain;
    private Fragment_DoctorRelativeSettings fragmentSettings;
    private FragmentTransaction fragmentTransaction;


    //Переменная необходимая для смены фрагментов
    private boolean main=true;


    //Firebase объекты
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;


    /*
    Список необходим для добавления в него
    добавленных с сервера сигналов
     */
    private List<FirebaseSignal> tasks;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        initializeFirebase();

        initializeFragments();

        setFragment();

        startSignalsService();

        getLastSignal();

    }




    //Инициализируем основные фрагменты
    private void initializeFragments(){
        fragmentMain = new Fragment_DoctorRelativeMain();
        fragmentSettings = new Fragment_DoctorRelativeSettings();
    }




    private void initializeFirebase(){

        //Инициализируем аккаунт устройства
        mAuth = FirebaseAuth.getInstance();

        //Инициализируем базу данных FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Инициализируем текущего юзера
        user = mAuth.getCurrentUser();
    }




    /*
    Метод определяющий какой фрагмент вставить в главный контейнер
    определяет это по переменной main
     */
    private void setFragment(){
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.add(R.id.frameDocMain, fragmentMain);
        }
        else {
            fragmentTransaction.add(R.id.frameDocMain, fragmentSettings);
        }
        fragmentTransaction.commit();

    }



    /*
    С помощью этого метода мы получаем из БД последние
    сигналы и выводим их на экран
     */
    private void getLastSignal(){


        databaseReference.child("Users").child(user.getUid()).child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tasks = new ArrayList<FirebaseSignal>();

                //Получение профиля мы осуществляем с помощью итерации

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    tasks.add(child.getValue(FirebaseSignal.class));
                }

                //Если список не пустой, достаём из него данные и
                //выводим их на экран
                if(!tasks.isEmpty()){

                    //Получили объект профиял
                    FirebaseSignal task = tasks.get(0);

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



    private void startSignalsService(){
        startService(new Intent(this, Service_BackGround.class));
    }


    private void seeSignalWindow(String initials, int type){
        Intent i = new Intent(ActivityDoctorRelative.this, ActivityDialogSeeTask.class);
        i.putExtra("Initials", initials);
        i.putExtra("Type", type);

        startActivity(i);
    }

    /*
    Метод необходимый для смены фрагмента
    в данном случае для смены основного
    фрагмента на фрагмент
     */
    @Override
    public void changeFragment() {
        main =! main;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.frameDocMain, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.frameDocMain, fragmentSettings);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void removeTasks(){
        databaseReference.child("Users").child(user.getUid()).child("Tasks").removeValue();
    }


}
