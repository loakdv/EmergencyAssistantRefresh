package com.example.dmitriy.emergencyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_Dialog_Relatives extends AppCompatActivity {

    Button btn_Cancel;
    Button btn_Add;
    Button btn_Final;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_relatives);
        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_Cancel_Relatives:
                        finish();
                        break;
                    case R.id.btn_finalRelative:
                        finish();
                        break;
                    case R.id.btn_AddNewRelative:
                        Intent i=new Intent(getApplicationContext(), Activity_Dialog_AddNewUser.class);
                        startActivity(i);
                        break;
                }
            }
        };
        btn_Cancel=findViewById(R.id.btn_Cancel_Relatives);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Add=findViewById(R.id.btn_AddNewRelative);
        btn_Add.setOnClickListener(oclBtn);
        btn_Final=findViewById(R.id.btn_finalRelative);
        btn_Final.setOnClickListener(oclBtn);
    }
}
