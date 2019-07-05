package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.adapters.customer.AdapterCustomerStateSelect;
import com.example.dmitriy.emergencyassistant.elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOState;
import com.example.dmitriy.emergencyassistant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/*
Активность которая нужна для сбора информации о состоянии пользователя
 */

public class ActivityDialogStateCheck extends AppCompatActivity implements AdapterCustomerStateSelect.CallBackButtons {


    private List<ElementStateSelect> listSelect= new ArrayList<ElementStateSelect>();
    private AdapterCustomerStateSelect adapterSelect;
    private RecyclerView recyclerViewElementsList;
    private Button btnExit;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_checkstate);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                   case R.id.btn_CloseState:
                    finish();
                    break;
                }
            }
        };

        btnExit = findViewById(R.id.btn_CloseState);
        btnExit.setOnClickListener(oclBtn);


        fillList();
        initializeRecycleView();
        initializeFirebase();
    }




    @Override
    public void select(ElementStateSelect state) {
        Toast.makeText(getApplicationContext(), "Готово!", Toast.LENGTH_SHORT).show();

        int percent = 0;

        switch (state.getType()){
            case 5:
                percent = 20;
                break;
            case 4:
                percent = 40;
                break;
            case 3:
                percent = 60;
                break;
            case 2:
                percent = 80;
                break;
            case 1:
                percent = 100;
                break;
        }

        //Отправляем состояние на сервер
        FirebaseUser user=mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("State").push().setValue(new POJOState(
                user.getUid(), state.getType(), percent));


        finish();
    }


    private void initializeFirebase(){
        databaseReference= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
    }





    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewElementsList=findViewById(R.id.rv_StatesList);
        adapterSelect=new AdapterCustomerStateSelect(
                getApplicationContext(), listSelect,
                this);


        recyclerViewElementsList.setAdapter(adapterSelect);
        recyclerViewElementsList.setLayoutManager(
                new LinearLayoutManager(getApplicationContext())
        );
    }




    private void fillList(){
        listSelect.add(new ElementStateSelect("Очень хорошо", 1));
        listSelect.add(new ElementStateSelect("Хорошо", 2));
        listSelect.add(new ElementStateSelect("Нормально", 3));
        listSelect.add(new ElementStateSelect("Плохо", 4));
        listSelect.add(new ElementStateSelect("Очень плохо", 5));
    }




}
