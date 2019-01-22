package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Dialog_AddNumber extends AppCompatActivity {

    /*
    Диалоговое окно для меню добавления номера
     */

    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */

    EditText et_Name;
    EditText et_Numbers;
    EditText et_Id;

    Button btn_Cancel;
    Button btn_Final;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_dialog_newnumber);

       //Листенер
       View.OnClickListener oclBtn=new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CancelAddNumber:
                       //Завершаем активность
                       finish();
                       break;
                   case R.id.btn_CommitAddNumber:
                       /*
                       Вызываем метод создания номера в нужной активности
                       Передаём туда полученные из полей ввода значения:
                       Имя, номер, id, изображение
                        */
                       if(et_Name.getText().toString().isEmpty()||et_Numbers.getText().toString().isEmpty()){
                           Toast.makeText(getApplicationContext(), "Вы не можете оставить пустыми поля номера и имени!", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Activity_Dialog_Numbers.addNumber( et_Name.getText().toString(), et_Numbers.getText().toString(), et_Id.getText().toString());
                           finish();
                       }


                       break;
               }
           }
       };

       //Поля ввода
       et_Name=findViewById(R.id.et_NumberName);
       et_Numbers=findViewById(R.id.et_PhoneNumber);
       et_Id=findViewById(R.id.et_IDforNumber);

       //Имициализация кнопок
       btn_Cancel=findViewById(R.id.btn_CancelAddNumber);
       btn_Cancel.setOnClickListener(oclBtn);
       btn_Final=findViewById(R.id.btn_CommitAddNumber);
       btn_Final.setOnClickListener(oclBtn);

    }
}
