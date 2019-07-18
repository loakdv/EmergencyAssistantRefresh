/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 7:48 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.loading;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.helpers.HelperLoading;
import com.example.dmitriy.emergencyassistant.R;

public class ActivityDialogLoading extends AppCompatActivity {

    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Log.d("CHECK BOOLEAN", "ON CREATE 1");
        progressBar = findViewById(R.id.pb_Loading);

        Log.d("CHECK BOOLEAN", "START THREAD");
        CheckThread checkThread = new CheckThread();
        checkThread.run();


    }

    private void closeActivity(){
        finish();
    }


    public void stopLoading(){
        finish();
    }


    class CheckThread extends Thread{

        @Override
        public void run() {

            try{
                while (true) {
                    if(HelperLoading.IS_LOADING == false){
                        closeActivity();
                        break;
                    }
                }
            }
            catch (Exception e){}

        }
    }



}
