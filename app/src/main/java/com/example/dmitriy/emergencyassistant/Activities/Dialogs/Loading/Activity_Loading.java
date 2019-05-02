package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.Helpers.Helper_Loading;
import com.example.dmitriy.emergencyassistant.R;

public class Activity_Loading extends AppCompatActivity {

    ProgressBar progressBar;

    CheckThread checkThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Log.d("CHECK BOOLEAN", "ON CREATE 1");
        progressBar = findViewById(R.id.pb_Loading);
        checkThread = new CheckThread();

        Log.d("CHECK BOOLEAN", "START THREAD");
        checkThread.run();

    }


    public void stopLoading(){

        Log.d("CHECK BOOLEAN", "STOP THREAD");
        finish();
    }

    private class CheckThread extends Thread{
        @Override
        public void run() {

            while (Helper_Loading.IS_LOADING){
                try {
                    currentThread().wait(3000);
                    stopLoading();

                }
                catch (Exception e){

                }
            }




        }
    }
}