package com.example.dmitriy.emergencyassistant;

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
    static ArrayList<Added_Number> numbers=new ArrayList<Added_Number>();

    //Адаптер для списка номеров
    Adapter_Added_PhoneNumbers a_numbers;

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
                       //Завершаем активность
                       finish();
                       break;
                   case R.id.btn_FinalNumbers:
                       //Завершаем активность
                       finish();
                       break;
                   case R.id.btn_AddNewNumber:
                       //Запускаем диалоговое окно для создания номера
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
        a_numbers=new Adapter_Added_PhoneNumbers(getApplicationContext(), numbers);
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    /*
    Специальные статические методы для того что бы не заморачиваться с
    добавлением элементов в список
     */

    /*
    Метод добавления номера
    Принимает на вход имя, номер и id
     */
    public static void addNumber(String name, String number, String id){
        numbers.add(new Added_Number(number, name, id));
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }


    /*
    Метод удаления номера из списка
    Принимает на вход значение индекса которое надо удалить
     */
    public static void deleteNumber(int index){
        numbers.remove(index);
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }


}
