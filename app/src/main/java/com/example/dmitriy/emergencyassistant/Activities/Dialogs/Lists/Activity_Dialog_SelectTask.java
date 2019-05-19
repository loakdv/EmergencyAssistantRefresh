package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_Needy;
import com.example.dmitriy.emergencyassistant.Adapters.Needy.Adapter_Needy_TaskElement;
import com.example.dmitriy.emergencyassistant.Elements.Element_StateSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Dialog_SelectTask extends AppCompatActivity implements Adapter_Needy_TaskElement.CallBackButtons{

    private RecyclerView rvTasks;
    private Adapter_Needy_TaskElement adapterNeedyTaskElement;
    private List<Element_StateSelect> selectList;

    private int selectType;

    public interface OnSelectItem{
        void selectItem(Element_StateSelect element);
    }

    private OnSelectItem onSelectItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_selecttask);
        selectType  = getIntent().getIntExtra("select_type", 0);

        if(selectType == 0){
            setTitle("Помощь по дому");
        }
        else {
            setTitle("Выбор товаров");
        }

        rvTasks = findViewById(R.id.rv_TaskSelect);

        //onSelectItem = (OnSelectItem) getApplicationContext();



        initializeList();
        initializeRecycleView();

    }


    private void initializeList(){
        selectList = new ArrayList<Element_StateSelect>();
        if(selectType == 0){
            selectList.add(new Element_StateSelect("Уборка", 2));
            selectList.add(new Element_StateSelect("Мойка посуды", 3));
            selectList.add(new Element_StateSelect("Вынос мусора", 4));
        }
        else {
            selectList.add(new Element_StateSelect("Хлеб", 5));
            selectList.add(new Element_StateSelect("Молоко", 6));
            selectList.add(new Element_StateSelect("Яйца", 7));
            selectList.add(new Element_StateSelect("Огурцы", 8));
            selectList.add(new Element_StateSelect("Помидоры", 9));

        }


    }

    private void initializeRecycleView(){

        adapterNeedyTaskElement = new Adapter_Needy_TaskElement(getApplicationContext(), selectList, this);
        rvTasks.setAdapter(adapterNeedyTaskElement);
        rvTasks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    public void select(Element_StateSelect relative){
        Intent i = new Intent(this, Activity_Needy.class);
        i.putExtra("signal type", relative.getType());
        startActivity(i);
    }
}
