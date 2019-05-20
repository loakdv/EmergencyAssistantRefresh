package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmitriy.emergencyassistant.R;

/*
Активность которая показывает оповещение о том что пользователю нужна помощь
 */

public class ActivityDialogSeeTask extends AppCompatActivity {

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_task);
        String init = getIntent().getStringExtra("Initials");
        int type = getIntent().getIntExtra("Type", 0);

        TextView text = findViewById(R.id.tv_TaskText);
        text.setText("Пользователь: \n"+init+"\n отправил сигнал SOS!");

        btnClose = findViewById(R.id.btn_close_task);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
