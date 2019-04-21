package com.example.dmitriy.emergencyassistant.Services;

import android.content.Context;
import android.content.Intent;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Loading.Activity_Loading;

public class Thread_Loading extends Thread {

    Context context;

    public Thread_Loading(Context context){
        this.context=context;
    }


    @Override
    public void run() {
        super.run();

        startActivity();
    }


    private void startActivity(){
        Intent i = new Intent(context, Activity_Loading.class);

    }
}
