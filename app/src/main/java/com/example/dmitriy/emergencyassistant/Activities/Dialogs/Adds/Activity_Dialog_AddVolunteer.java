package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_SeeSocialInfo;
import com.example.dmitriy.emergencyassistant.Adapters.Volunteer.Adapter_VolunteerForSelect;
import com.example.dmitriy.emergencyassistant.Elements.Element_VolunteerForSelect;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy_Volunteer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/*
Диалоговое окно для добавления соц. работника
 */
public class Activity_Dialog_AddVolunteer extends AppCompatActivity implements Adapter_VolunteerForSelect.CallbackButton {


    private Button btnCancel, btnConfirm;
    private EditText etID;

    //В этих списках мы храним наши данные с сервера
    private List<Firebase_Profile> profileList;
    private List<Firebase_Volunteer> volunteerList;

    private DataBase_AppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    //По большей степени это нужно для загрузки фото профиля
    private FirebaseStorage firebaseStorage;
    private StorageReference rootRef;

    private byte[] profilePhoto;

    private List<Element_VolunteerForSelect> volunteerForSelectList;
    private Adapter_VolunteerForSelect adapterVolunteerForSelect;
    private RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_volunteer);

        initializeDataBase();

        initializeFirebase();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerCancel:
                        finish();
                        break;
                    case R.id.btn_VolunteerConfirm:
                        findVolunteer(etID.getText().toString());
                        break;
                }
            }
        };

        btnCancel=findViewById(R.id.btn_VolunteerCancel);
        btnCancel.setOnClickListener(oclBtn);

        btnConfirm=findViewById(R.id.btn_VolunteerConfirm);
        btnConfirm.setOnClickListener(oclBtn);

        etID=findViewById(R.id.et_VolunteerID);

        initializeVolunteersList();
        initializeRecycleView();

    }




    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    private void findVolunteer(String id){

        profileList=new ArrayList<Firebase_Profile>();


        databaseReference.child("Users").child(id).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    profileList.add(child.getValue(Firebase_Profile.class));
                }


                if(!profileList.isEmpty() && profileList.get(0).getType() == 2){
                    Firebase_Profile profile=profileList.get(0);
                    if(profile.getType() == 2){

                        loadVolunteerExtra(profile.getId(), profile.getName(), profile.getSurname(),
                                profile.getMiddlename(), dataBase.dao_needy().getNeedy().getId());
                    }

                }
                else {

                    Toast.makeText(Activity_Dialog_AddVolunteer.this, "Такого пользователя не существует!\n" +
                            "Проверьте правильность введённых данных!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private void loadVolunteerExtra(final String id, final String name, final String surname,
                                    final String middlename, final long needyID){
        volunteerList = new ArrayList<Firebase_Volunteer>();

        databaseReference.child("Users").child(id).child("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    volunteerList.add(child.getValue(Firebase_Volunteer.class));
                }

                //Если такой пользователь был найден, то добавляем его в локальную базу данных
                if(!volunteerList.isEmpty()){

                    Firebase_Volunteer volunteer=volunteerList.get(0);
                    dataBase.dao_needy_volunteer().insert(new Entity_Needy_Volunteer(id, needyID,
                            name, surname, middlename, volunteer.getOrganization(), profilePhoto));

                    finish();

                    Intent i = new Intent(Activity_Dialog_AddVolunteer.this, Activity_SeeSocialInfo.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(Activity_Dialog_AddVolunteer.this, "Такого пользователя не существует," +
                            " или он не нуждается в помощи!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    private void loadPhoto(){
        FirebaseUser user=mAuth.getCurrentUser();

        StorageReference root = rootRef.child(user.getUid()).child("profilePhoto");
        Log.d("DOWNLOAD", "START DOWNLOAD");
        final long ONE_MEGABYTE = 1024 * 1024;
        rootRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Helper_CreateProfile.PHOTO =bytes;
                Log.d("DOWNLOAD", "DOWNLOADED");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }

    private void initializeVolunteersList(){
        volunteerForSelectList = new ArrayList<Element_VolunteerForSelect>();
        volunteerForSelectList.add(new Element_VolunteerForSelect("USKXrpESYOXokMbVYqO2zoYyNRm2", "Сергиенко Мария Владимировна", "Департамент труда и социального развития"));
        volunteerForSelectList.add(new Element_VolunteerForSelect("4pY4OdRV95d2UGwP4VTrJBVGro73", "Фадеев Иван Сергеевич", "Департамент труда и социального развития"));
    }

    private void initializeRecycleView(){
        recyclerView = findViewById(R.id.rv_Social_Volunteers);
        adapterVolunteerForSelect = new Adapter_VolunteerForSelect(getBaseContext(), volunteerForSelectList, this);
        recyclerView.setAdapter(adapterVolunteerForSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void initializeFirebase(){
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void selectVolunteer(String id) {
        findVolunteer(id);
    }

}
