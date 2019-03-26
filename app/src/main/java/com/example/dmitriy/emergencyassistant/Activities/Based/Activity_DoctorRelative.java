package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Activity_See_Task;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Task;
import com.example.dmitriy.emergencyassistant.Fragments.Fragment_DoctorRelativeMain;
import com.example.dmitriy.emergencyassistant.Fragments.Fragment_DoctorRelativeSettings;
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

public class Activity_DoctorRelative extends AppCompatActivity implements Fragment_DoctorRelativeMain.onChangeDocFrag {

    //Переменная необходимая для смены основных фрагментов


    //Фрагменты основного "вида" и настроек
    private Fragment_DoctorRelativeMain fragmentMain;
    private Fragment_DoctorRelativeSettings fragmentSettings;

    //Транзакция для этих фрагментов
    private FragmentTransaction fragmentTransaction;

    private boolean main=true;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private List<Firebase_Task> tasks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        initializeFirebase();

        initializeFragments();

        setFragment();

        startService(new Intent(this, Service_BackGround.class));

        final FirebaseUser user=mAuth.getCurrentUser();

        databaseReference.child("Users").child(user.getUid()).child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                tasks=new ArrayList<Firebase_Task>();
                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    //Получили профиль, добавили его в список
                    tasks.add(child.getValue(Firebase_Task.class));
                }


                if(!tasks.isEmpty()){
                    Firebase_Task task=tasks.get(0);
                    Intent i=new Intent(Activity_DoctorRelative.this, Activity_See_Task.class);
                    i.putExtra("Initials", task.getInitials());
                    i.putExtra("Type", task.getType());
                    Log.d("SIGNAL", "TASK TASK TASK");
                    startActivity(i);

                    databaseReference.child("Users").child(user.getUid()).child("Tasks").removeValue();
                }
                else {

                    Toast.makeText(Activity_DoctorRelative.this, "Сигналы не поступали", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private void initializeFragments(){
        //Инициализируем основные фрагменты
        fragmentMain=new Fragment_DoctorRelativeMain();
        fragmentSettings=new Fragment_DoctorRelativeSettings();
    }




    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
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
    Метод необходимый для смены фрагмента
    в данном случае для смены основного
    фрагмента на фрагмент
     */
    @Override
    public void changeFragment() {
        main=!main;
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(main){
            fragmentTransaction.replace(R.id.frameDocMain, fragmentMain);
        }
        else {
            fragmentTransaction.replace(R.id.frameDocMain, fragmentSettings);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}
