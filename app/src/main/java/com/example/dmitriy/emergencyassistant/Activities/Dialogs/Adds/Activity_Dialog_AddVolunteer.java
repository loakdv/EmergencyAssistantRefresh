package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_SeeSocialInfo;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Relative;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_Relatives;
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

public class Activity_Dialog_AddVolunteer extends AppCompatActivity {

    private Button btnCancel, btnConfirm;
    private EditText etID;

    private List<Firebase_Profile> profileList;
    private List<Firebase_Volunteer> volunteerList;

    private DataBase_AppDatabase dataBase;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private FirebaseStorage firebaseStorage;
    private StorageReference rootRef;

    private byte[] profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dialog__add_volunteer);

        initializeDataBase();

        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_VolunteerCancel:
                        finish();
                        break;
                    case R.id.btn_VolunteerConfirm:
                        findVolunteer();
                        Log.d("VOLUNTEER", "CLICK");
                        break;
                }
            }
        };

        btnCancel=findViewById(R.id.btn_VolunteerCancel);
        btnCancel.setOnClickListener(oclBtn);

        btnConfirm=findViewById(R.id.btn_VolunteerConfirm);
        btnConfirm.setOnClickListener(oclBtn);

        etID=findViewById(R.id.et_VolunteerID);
    }


    //Метод который инициализирует базу данных
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }

    private void findVolunteer(){
        profileList=new ArrayList<Firebase_Profile>();

        Log.d("VOLUNTEER", "FIND VOLUNTEER METHOD");

        Log.d("VOLUNTEER", etID.getText().toString()
        );

        //databaseReference.child("Profile").orderByChild("email").equalTo(etID.getText())

        databaseReference.child("Users").child(etID.getText().toString()).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    profileList.add(child.getValue(Firebase_Profile.class));
                }
                Log.d("VOLUNTEER", "ITERATION");

                if(!profileList.isEmpty() && profileList.get(0).getType() == 2){
                    Firebase_Profile profile=profileList.get(0);
                    if(profile.getType()==2){
                        loadVolunteerExtra(profile.getId(), profile.getName(), profile.getSurname(), profile.getMiddlename(), dataBase.dao_needy().getNeedy().getId());
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
        volunteerList=new ArrayList<Firebase_Volunteer>();

        databaseReference.child("Users").child(id).child("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    //Получили профиль, добавили его в список
                    volunteerList.add(child.getValue(Firebase_Volunteer.class));
                }

                //Если такой пользователь был найден, то добавляем его в локальную базу данных
                if(!volunteerList.isEmpty()){
                    Firebase_Volunteer volunteer=volunteerList.get(0);
                    dataBase.dao_needy_volunteer().insert(new Entity_Needy_Volunteer(id, needyID,
                            name, surname, middlename, volunteer.getOrganization(), profilePhoto));

                    finish();

                    Intent i =new Intent(Activity_Dialog_AddVolunteer.this, Activity_SeeSocialInfo.class);
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
                Helper_CreateProfile.photo=bytes;
                Log.d("DOWNLOAD", "DOWNLOADED");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }
}