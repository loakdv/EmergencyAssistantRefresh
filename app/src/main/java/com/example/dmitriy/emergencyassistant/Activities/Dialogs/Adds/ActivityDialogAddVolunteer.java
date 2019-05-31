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

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.ActivityDialogSeeSocialInfo;
import com.example.dmitriy.emergencyassistant.Adapters.Volunteer.AdapterVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.Elements.ElementVolunteerForSelect;
import com.example.dmitriy.emergencyassistant.Firebase.FirebaseProfile;
import com.example.dmitriy.emergencyassistant.Firebase.FirebaseVolunteer;
import com.example.dmitriy.emergencyassistant.Helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedyFixedVolunteer;
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
public class ActivityDialogAddVolunteer extends AppCompatActivity implements AdapterVolunteerForSelect.CallbackButton {


    private Button btnCancel, btnConfirm;
    private EditText etID;

    //В этих списках мы храним наши данные с сервера
    private List<FirebaseProfile> profileList;
    private List<FirebaseVolunteer> volunteerList;

    private DataBaseAppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    //По большей степени это нужно для загрузки фото профиля
    private FirebaseStorage firebaseStorage;
    private StorageReference rootRef;

    private byte[] profilePhoto;

    private List<ElementVolunteerForSelect> volunteerForSelectList;
    private AdapterVolunteerForSelect adapterVolunteerForSelect;
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
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    private void findVolunteer(String id){

        profileList=new ArrayList<FirebaseProfile>();


        databaseReference.child("Users").child(id).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    profileList.add(child.getValue(FirebaseProfile.class));
                }


                if(!profileList.isEmpty() && profileList.get(0).getType() == 2){
                    FirebaseProfile profile=profileList.get(0);
                    if(profile.getType() == 2){

                        loadVolunteerExtra(profile.getId(), profile.getName(), profile.getSurname(),
                                profile.getMiddlename(), dataBase.dao_needy().getNeedy().getId());
                    }

                }
                else {

                    Toast.makeText(ActivityDialogAddVolunteer.this, "Такого пользователя не существует!\n" +
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
        volunteerList = new ArrayList<FirebaseVolunteer>();

        databaseReference.child("Users").child(id).child("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    volunteerList.add(child.getValue(FirebaseVolunteer.class));
                }

                //Если такой пользователь был найден, то добавляем его в локальную базу данных
                if(!volunteerList.isEmpty()){

                    FirebaseVolunteer volunteer=volunteerList.get(0);
                    dataBase.dao_needy_volunteer().insert(new EntityNeedyFixedVolunteer(id, needyID,
                            name, surname, middlename, volunteer.getOrganization(), profilePhoto));

                    finish();

                    Intent i = new Intent(ActivityDialogAddVolunteer.this, ActivityDialogSeeSocialInfo.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(ActivityDialogAddVolunteer.this, "Такого пользователя не существует," +
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
                HelperCreateProfile.PHOTO =bytes;
                Log.d("DOWNLOAD", "DOWNLOADED");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }

    private void initializeVolunteersList(){
        volunteerForSelectList = new ArrayList<ElementVolunteerForSelect>();
        volunteerForSelectList.add(new ElementVolunteerForSelect("USKXrpESYOXokMbVYqO2zoYyNRm2", "Сергиенко Мария Владимировна", "Департамент труда и социального развития"));
        volunteerForSelectList.add(new ElementVolunteerForSelect("4pY4OdRV95d2UGwP4VTrJBVGro73", "Фадеев Иван Сергеевич", "Департамент труда и социального развития"));
    }

    private void initializeRecycleView(){
        recyclerView = findViewById(R.id.rv_Social_Volunteers);
        adapterVolunteerForSelect = new AdapterVolunteerForSelect(getBaseContext(), volunteerForSelectList, this);
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
