package com.example.dmitriy.emergencyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Dialog_AddNote extends AppCompatActivity {

    /*
    Диалоговое окно для создания заметок
     */

    //Элементы которые используются при создании заметок
    Button btn_Cancel;
    Button btn_Concl;
    EditText et_AddNoteText;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_addnote);


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_CancelAddNote:
                        finish();
                        break;
                    case R.id.btn_SaveAddNote:
                        /*Обращаемся к фрагменту для создания заметки и
                        передаем текст полученный из EditText(et_AddNoteText)
                        */
                        //Проверка поля ввода на пустоту
                        if(et_AddNoteText.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Вы не можете создать пустую заметку!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Fragment_SeeNotes.addNote(et_AddNoteText.getText().toString());
                            finish();
                        }

                        break;
                }
            }
        };

        //Инициализация элементов
        btn_Cancel=findViewById(R.id.btn_CancelAddNote);
        btn_Concl=findViewById(R.id.btn_SaveAddNote);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Concl.setOnClickListener(oclBtn);
        et_AddNoteText=findViewById(R.id.et_AddNoteText);
    }



}
