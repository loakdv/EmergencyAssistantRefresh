package com.example.dmitriy.emergencyassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Activity_Dialog_Numbers extends AppCompatActivity {

    /*
    Диалоговое окно для простотра списка подключённых
     */

    //Лист нужных объектов
    static ArrayList<AddedNumber> numbers=new ArrayList<AddedNumber>();

    //Адаптер для списка номеров
    NumbersRecyclerViewAdapter a_numbers;

    static RecyclerView rv_Numbers;
    //Кнопки для отмены, добавления, и сохранения
    Button btn_Cancel;
    Button btn_Final;
    Button btn_Add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_numbers);

        //Листенер
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CancelNumbers:
                       finish();
                       break;
                   case R.id.btn_FinalNumbers:
                       finish();
                       break;
                   case R.id.btn_AddNewNumber:
                       Intent i=new Intent(getApplicationContext(), Activity_Dialog_AddNumber.class);
                       startActivity(i);
                       break;
               }
            }
        };

        //Нужные элементы
        btn_Cancel=findViewById(R.id.btn_CancelNumbers);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Final=findViewById(R.id.btn_FinalNumbers);
        btn_Final.setOnClickListener(oclBtn);
        btn_Add=findViewById(R.id.btn_AddNewNumber);
        btn_Add.setOnClickListener(oclBtn);

        rv_Numbers=findViewById(R.id.rv_Numbers);

        //Элементы списка
        a_numbers=new NumbersRecyclerViewAdapter(getApplicationContext(), numbers);
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    public static void addNumber(String name, String number, String id){
        numbers.add(new AddedNumber(number, name, id));
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }

    public static void deleteNumber(int index){
        numbers.remove(index);
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }


}
