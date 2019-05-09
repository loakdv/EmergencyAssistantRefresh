package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Added_PhoneNumbers;
import com.example.dmitriy.emergencyassistant.Adapters.Adapter_Organizations;
import com.example.dmitriy.emergencyassistant.Elements.Element_Organization;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_OrganizationsList extends AppCompatActivity {

    private List<Element_Organization> organizations = new ArrayList<Element_Organization>();

    private Adapter_Organizations adapterOrganizations;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations_list);
        initializeList();
        initializeRecycleView();
    }

    //Метод обновления RV, нужен так же для обновления списка на экране
    private void initializeRecycleView(){
        recyclerView=findViewById(R.id.rv_Organizations);
        adapterOrganizations=new Adapter_Organizations(getBaseContext(), organizations);
        recyclerView.setAdapter(adapterOrganizations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void initializeList(){
        organizations.add(new Element_Organization("Департамент труда и социального развития", "8 (423) 227-33-29", "http://soctrud.primorsky.ru/"));
        organizations.add(new Element_Organization("Приморский центр социального обслуживания населения", " 8 (423) 2604-314", "http://pcson.ru/"));
        organizations.add(new Element_Organization("Седанкинский дом-интернат", "233-43-07, 233-36-04", "http://cdipi.vl.socinfo.ru"));
    }
}
