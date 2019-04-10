package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Needy_StateSelect;
import com.example.dmitriy.emergencyassistant.Elements.Element_StateSelect;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_State;
import com.example.dmitriy.emergencyassistant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_StateCheck extends AppCompatActivity implements Adapter_Needy_StateSelect.CallBackButtons {


    private List<Element_StateSelect> listSelect= new ArrayList<Element_StateSelect>();
    private Adapter_Needy_StateSelect adapterSelect;
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

        btnExit=findViewById(R.id.btn_CloseState);
        btnExit.setOnClickListener(oclBtn);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        fillList();
        initializeRecycleView();
    }




    @Override
    public void select(Element_StateSelect state) {
        Toast.makeText(getApplicationContext(),
                "Выбран элемент №: "+state.getText(),
                Toast.LENGTH_SHORT).show();

        int percent=0;

        switch (state.getType()){
            case 5:
                percent=20;
                break;
            case 4:
                percent=40;
                break;
            case 3:
                percent=60;
                break;
            case 2:
                percent=80;
                break;
            case 1:
                percent=100;
                break;
        }


        FirebaseUser user=mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("State").push().setValue(new Firebase_State(
                user.getUid(), state.getType(), percent));


        finish();
    }


    private void initializeFirebase(){
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }





    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerViewElementsList=findViewById(R.id.rv_StatesList);
        adapterSelect=new Adapter_Needy_StateSelect(
                getApplicationContext(), listSelect,
                this);


        recyclerViewElementsList.setAdapter(adapterSelect);
        recyclerViewElementsList.setLayoutManager(
                new LinearLayoutManager(getApplicationContext())
        );
    }




    private void fillList(){
        listSelect.add(new Element_StateSelect("Очень хорошо", 1));
        listSelect.add(new Element_StateSelect("Хорошо", 2));
        listSelect.add(new Element_StateSelect("Нормально", 3));
        listSelect.add(new Element_StateSelect("Плохо", 4));
        listSelect.add(new Element_StateSelect("Очень плохо", 5));
    }




}
