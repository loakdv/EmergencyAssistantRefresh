package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dmitriy.emergencyassistant.Adapters.Login.AdapterLoginOrganizations;
import com.example.dmitriy.emergencyassistant.Elements.ElementOrganization;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityDialogOrganizationsList extends AppCompatActivity {

    private List<ElementOrganization> organizations = new ArrayList<ElementOrganization>();

    private AdapterLoginOrganizations adapterOrganizations;
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
        adapterOrganizations=new AdapterLoginOrganizations(getBaseContext(), organizations);
        recyclerView.setAdapter(adapterOrganizations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void initializeList(){
        organizations.add(new ElementOrganization("Департамент труда и социального развития", "8 (423) 227-33-29", "http://soctrud.primorsky.ru/"));
        organizations.add(new ElementOrganization("Приморский центр социального обслуживания населения", " 8 (423) 2604-314", "http://pcson.ru/"));
        organizations.add(new ElementOrganization("Седанкинский дом-интернат", "233-43-07, 233-36-04", "http://cdipi.vl.socinfo.ru"));
    }
}
