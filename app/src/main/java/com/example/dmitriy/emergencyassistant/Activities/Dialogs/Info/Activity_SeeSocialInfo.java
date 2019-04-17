package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_Volunteer;

public class Activity_SeeSocialInfo extends AppCompatActivity {

    TextView tvName, tvSurname, tvMiddlename, tvOrganization, tvVolID;

    Button btnBack, btnDisconnect;

    DataBase_AppDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_social_info);

        initializeDataBase();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_SeeSocialBack:
                        finish();
                        break;
                    case R.id.btn_SeeSocialDisconnect:
                        disconnectUser();
                        break;
                }
            }
        };

        tvName=findViewById(R.id.tv_VolunteerName);
        tvSurname=findViewById(R.id.tv_VolunteerSurname);
        tvMiddlename=findViewById(R.id.tv_VolunteerMiddlename);
        tvOrganization=findViewById(R.id.tv_VolunteerOrganization);
        tvVolID=findViewById(R.id.tv_Needy_VolunteerID);

        btnBack=findViewById(R.id.btn_SeeSocialBack);
        btnBack.setOnClickListener(oclBtn);
        btnDisconnect=findViewById(R.id.btn_SeeSocialDisconnect);
        btnDisconnect.setOnClickListener(oclBtn);

        setInfo();
    }


    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }


    private void setInfo(){
        Entity_Needy_Volunteer volunteer=dataBase.dao_needy_volunteer().getVolunteer();
        tvName.setText(volunteer.getName());
        tvSurname.setText(volunteer.getSurname());
        tvMiddlename.setText(volunteer.getMiddlename().toString());
        tvOrganization.setText(volunteer.getOrganization());
        tvVolID.setText(volunteer.getId());
    }


    private void disconnectUser(){
        dataBase.dao_needy_volunteer().delete(dataBase.dao_needy_volunteer().getVolunteer());
        finish();
    }
}
