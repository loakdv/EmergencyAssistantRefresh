package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.dmitriy.emergencyassistant.R;

public class Activity_Loading extends AppCompatActivity {

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        progressBar = findViewById(R.id.pb_Loading);


    }
}
