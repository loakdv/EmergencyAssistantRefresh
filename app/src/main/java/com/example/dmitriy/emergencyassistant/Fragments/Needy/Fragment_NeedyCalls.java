package com.example.dmitriy.emergencyassistant.Fragments.Needy;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Adapters.Needy.Adapter_Number_ForCall;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_PhoneNumbers;

import java.util.ArrayList;
import java.util.List;

/*
Фрагмент который отображает список для звонков у Needy
 */

public class Fragment_NeedyCalls extends Fragment implements Adapter_Number_ForCall.CallBackButtons  {


    //Объявляем интерфейс как поле
    private Fragment_NeedyMain.onSomeEventListener someEventListener;

    //Получаем контекст для интерфейса
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        someEventListener = (Fragment_NeedyMain.onSomeEventListener) context;
    }

    //Кнопка для перехода к другому фрагменту Main
    private Button btnBack;

    //Лист нужных объектов
    private List<Entity_Added_PhoneNumbers> numbers=new ArrayList<Entity_Added_PhoneNumbers>();

    private Adapter_Number_ForCall a_calls;

    private DataBase_AppDatabase dataBase;
    private RecyclerView rv_Numbers;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_needycalls, container, false);
        rv_Numbers=v.findViewById(R.id.rv_Number_ForCall);
        initializeDataBase();
        initializeList();
        initializeRecycleView();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnBack:
                        //Меняем основной фрагмент активности
                        someEventListener.changeFrag();
                        break;
                }
            }
        };
        btnBack=v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(oclBtn);

        return v;
    }


    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getContext(),
                DataBase_AppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    private void initializeList(){
        if(!(dataBase.dao_added_phoneNumbers().getAll()==null)){
            numbers=dataBase.dao_added_phoneNumbers().getAll();
        }
    }

    private void initializeRecycleView(){
        a_calls=new Adapter_Number_ForCall(getActivity() ,numbers, this);
        rv_Numbers.setAdapter(a_calls);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void call(Entity_Added_PhoneNumbers number) {
        Intent call=new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:"+number.getNumber()));
        startActivity(call);
    }
}
