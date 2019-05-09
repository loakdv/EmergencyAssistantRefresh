package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.tbuonomo.creativeviewpager.CreativeViewPager;
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

/*
Активность которая высвечивается при первом запуске приложения
В ней кратко рассказывается о приложении
 */
public class Activity_WelcomeMenu extends AppCompatActivity {

    CreativeViewPager creativeViewPager;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_ExitWelcome:
                        finish();
                        break;
                }
            }
        };


        btnExit = findViewById(R.id.btn_ExitWelcome);
        btnExit.setOnClickListener(oclBtn);

    }
}
