/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.R;
import com.tbuonomo.creativeviewpager.CreativeViewPager;

/*
Активность которая высвечивается при первом запуске приложения
В ней кратко рассказывается о приложении
 */
public class ActivityDialogWelcomeMenu extends AppCompatActivity {

    private CreativeViewPager creativeViewPager;
    private Button btnExit;

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
