package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dmitriy.emergencyassistant.Activities.Based.ActivityLogin;
import com.example.dmitriy.emergencyassistant.Adapters.Login.AdapterLoginFastUsers;
import com.example.dmitriy.emergencyassistant.Elements.ElementFastUser;
import com.example.dmitriy.emergencyassistant.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityDialogFastUserSelect extends AppCompatActivity implements AdapterLoginFastUsers.CallBack {

    private RecyclerView rvUsers;
    private List<ElementFastUser> listUsers = new ArrayList<ElementFastUser>();
    private AdapterLoginFastUsers adapterLoginFastUsers;

    private Button btnExit;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fastuserselect);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_ExitUsers:
                        finish();
                        break;
                }
            }
        };
        btnExit = findViewById(R.id.btn_ExitUsers);
        btnExit.setOnClickListener(oclBtn);

        initializeList();
        initializeRecycleView();
    }

    private void initializeList(){
        listUsers.add(new ElementFastUser("Алексеев Иван Евгеньевич", "needy1@mail.com", "111111","", "Нуждающийся в помощи"));
        listUsers.add(new ElementFastUser("Сидоров Алексей Евгеньевич", "needy2@mail.com", "111111","", "Нуждающийся в помощи"));
        listUsers.add(new ElementFastUser("Сергиенко Мария Владимировна", "volunteer1@gmail.com", "111111","", "Соц. работник"));
        listUsers.add(new ElementFastUser("Фадеев Иван Сергеевич", "volunteer2@gmail.com", "111111","", "Соц. работник"));
        //listUsers.add(new ElementFastUser("Петров Денис Алексеевич", "relative1@mail.com", "111111","", "Родственник"));
        //listUsers.add(new ElementFastUser("Масков Валерий Альбертович", "doctor1@mail.com", "111111","", "Врач"));

    }

    private void initializeRecycleView(){
        rvUsers = findViewById(R.id.rv_fastUsers);
        adapterLoginFastUsers = new AdapterLoginFastUsers(getApplicationContext(), listUsers, this);
        rvUsers.setAdapter(adapterLoginFastUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onUserSelected(ElementFastUser elementFastUser) {
        loginUser(elementFastUser.getEmail(), elementFastUser.getPassword());
    }

    private void loginUser(String email, String password){
        Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
        i.putExtra("isFastUser", true);
        i.putExtra("fastEmail", email);
        i.putExtra("fastPassword", password);
        startActivity(i);
    }
}
