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

import com.example.dmitriy.emergencyassistant.Adapters.Needy.AdapterNeedyNumberForCall;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyAddedPhoneNumbers;

import java.util.ArrayList;
import java.util.List;

/*
Фрагмент который отображает список для звонков у Needy
 */

public class FragmentNeedyCalls extends Fragment implements AdapterNeedyNumberForCall.CallBackButtons  {


    //Объявляем интерфейс как поле
    private FragmentNeedyMain.onSomeEventListener someEventListener;

    //Получаем контекст для интерфейса
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        someEventListener = (FragmentNeedyMain.onSomeEventListener) context;
    }

    //Кнопка для перехода к другому фрагменту Main
    private Button btnBack;

    //Лист нужных объектов
    private List<EntityNeedyAddedPhoneNumbers> numbers=new ArrayList<EntityNeedyAddedPhoneNumbers>();

    private AdapterNeedyNumberForCall a_calls;

    private DataBaseAppDatabase dataBase;
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
                DataBaseAppDatabase.class, "note_database").allowMainThreadQueries().build();
    }

    private void initializeList(){
        if(!(dataBase.dao_added_phoneNumbers().getAll()==null)){
            numbers=dataBase.dao_added_phoneNumbers().getAll();
        }
    }

    private void initializeRecycleView(){
        a_calls=new AdapterNeedyNumberForCall(getActivity() ,numbers, this);
        rv_Numbers.setAdapter(a_calls);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void call(EntityNeedyAddedPhoneNumbers number) {
        Intent call=new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:"+number.getNumber()));
        startActivity(call);
    }
}
