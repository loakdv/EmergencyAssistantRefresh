package com.example.dmitriy.emergencyassistant.activities.based;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;

/*
Активность которая нужна для открытия раздела с описанием приложения
Всё что видит пользователь - информацию которая указана в XML файле
 */
public class ActivityAboutApp extends AppCompatActivity {
    
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };

        btnExit=findViewById(R.id.btn_ExitAboutApp);
        btnExit.setOnClickListener(oclBtn);

    }
}
