package com.example.dmitriy.emergencyassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment_NumbersForNeedy extends Fragment {


    //Переменная для хранения имени
    private String name;

    //Элементы фрагмента
    private Button btnCall;
    private EditText etName;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_numbers_for_needy, container, false);
        /*
        btnCall=v.findViewById(R.id.CbtnCall);
        etName=v.findViewById(R.id.CtvName);

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.CbtnCall:
                        Toast.makeText(getContext(), "Начался вызов подключённого абонента", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        btnCall.setOnClickListener(oclBtn);
        */
        return v;
    }
}
