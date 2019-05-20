package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dmitriy.emergencyassistant.Activities.Based.ActivityNeedy;
import com.example.dmitriy.emergencyassistant.Adapters.Needy.AdapterNeedySelectElement;
import com.example.dmitriy.emergencyassistant.Elements.ElementStateSelect;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityDialogSelectTask extends AppCompatActivity implements AdapterNeedySelectElement.CallBackButtons{

    private RecyclerView rvTasks;
    private AdapterNeedySelectElement adapterNeedyTaskElement;
    private List<ElementStateSelect> selectList;

    private int selectType;

    public interface OnSelectItem{
        void selectItem(ElementStateSelect element);
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
        selectList = new ArrayList<ElementStateSelect>();
        if(selectType == 0){
            selectList.add(new ElementStateSelect("Уборка", 2));
            selectList.add(new ElementStateSelect("Мойка посуды", 3));
            selectList.add(new ElementStateSelect("Вынос мусора", 4));
        }
        else {
            selectList.add(new ElementStateSelect("Хлеб", 5));
            selectList.add(new ElementStateSelect("Молоко", 6));
            selectList.add(new ElementStateSelect("Яйца", 7));
            selectList.add(new ElementStateSelect("Огурцы", 8));
            selectList.add(new ElementStateSelect("Помидоры", 9));

        }


    }

    private void initializeRecycleView(){

        adapterNeedyTaskElement = new AdapterNeedySelectElement(getApplicationContext(), selectList, this);
        rvTasks.setAdapter(adapterNeedyTaskElement);
        rvTasks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    public void select(ElementStateSelect relative){
        Intent i = new Intent(this, ActivityNeedy.class);
        i.putExtra("signal type", relative.getType());
        startActivity(i);
    }
}
