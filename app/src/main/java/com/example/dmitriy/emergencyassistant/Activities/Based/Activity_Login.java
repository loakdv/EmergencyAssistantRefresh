package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Needy;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Relative;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_LoginEnter;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_CreateAccount;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_FirstSelect;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_Needy;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_Relative;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_Volunteer;
import com.example.dmitriy.emergencyassistant.Helpers.Helper_CreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Needy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.Entity_Profile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.Entity_Relative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.Entity_Volunteer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Activity_Login extends AppCompatActivity implements
        Fragment_Login_FirstSelect.changeLoginFragment {

    /*
    Активность для создания аккаунта
     */


    //Фрагменты для создания аккаунта и для авторизации
    private Fragment_Login_FirstSelect fragmentFirstSelect;
    private Fragment_LoginEnter fragmentEnter;
    private Fragment_Login_CreateAccount fragmentCreate;
    private Fragment_Login_Needy fragmentNeedy;
    private Fragment_Login_Relative fragmentRelative;
    private Fragment_Login_Volunteer fragmentVolunteer;

    //Транзакция
    private FragmentTransaction fragmentTransaction;

    //Локальная база данных
    private DataBase_AppDatabase dataBase;

    /*
    Компоненты FireBase
    Компонент аутентификации
    Компонент базы данных
     */
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference rootRef;
    private FirebaseUser user;


    /*
    Листы нужные для добавления в них данных из FireBase
    Нужны для авторизации, т.е. сейчас мы туда кидаем полученный объект,
    потом достаём объект и уже из него береём данные
    !ОБЪЕКТ ВСЕГДА ОДИН!
    Выглядит костыльно, но ничего лучше я ещё не смог сделать
     */
    public List<Firebase_Profile> profiles;
    public List<Firebase_Needy> needys;
    public List<Firebase_Relative> doctors;
    public List<Firebase_Volunteer> volunteers;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Инициализируем объекты firebase
        initialiseFirebaseObj();

        //Инициализируем локальную базу данных
        initializeDataBase();

        //Инициализируем фрагменты
        initializeFragments();

        //Устанавливаем первый фрагмент
        setFirst();
    }



    /*
    Метод для инициализации модулей firebase
     */
    private void initialiseFirebaseObj(){

        firebaseStorage = FirebaseStorage.getInstance();
        rootRef = firebaseStorage.getReference();

        //Инициализируем аккаунт устройства
        mAuth = FirebaseAuth.getInstance();

        //Инициализируем базу данных FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        user = mAuth.getCurrentUser();
    }




    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    private void initializeFragments(){
        //Инициализация фрагментов
        fragmentFirstSelect = new Fragment_Login_FirstSelect();
        fragmentEnter = new Fragment_LoginEnter();
        fragmentCreate = new Fragment_Login_CreateAccount();
        fragmentNeedy = new Fragment_Login_Needy();
        fragmentRelative = new Fragment_Login_Relative();
        fragmentVolunteer = new Fragment_Login_Volunteer();
    }




    /*
    Метод нужный для регистрации пользователя в FireBase и на устройстве
    Сначала создаём пользователя в FireBase, если всё проходит успешно,
    продолжаем регистрацию, т.е. продолжаем заполнять нужные нам данные
     */
    private void registration(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    makeToast("Регистрация прошла успешно!");
                    finishRegistration();
                }
                else {
                    makeToast("Ошибка при регистрации!");
                }
            }
        });
    }




    /*
    Метод нужный для авторизпции пользователя в системе
    Отправляем данные из полей ввода в FireBase
    Если пользователь найден и совпали логин/пароль то
    продолжаем вход в систему
     */
    private void login(String email, String password){

        //Отправляем данные для входа
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    makeToast("Вход успешно выполнен!");
                    downloadImage();
                    finishLogin();
                }

                else {
                    makeToast("Ошибка при входе!");
                }
            }
        });
    }



    /*
    Метод который мы вызываем при завершении
    процесса входа в аккаунт.
    Получаем данные из базы данных, и на основе этих данных
    создаём локальную базу данных
     */
    private void finishLogin(){


        /*
        Инициализируем лист с профилем
        В него будет кидаться !ОДИН! объект профиля,
        и из него уже будем получать данные
        */
        profiles = new ArrayList<Firebase_Profile>();

        /*
        Устанавливаем путь, по которому мы будем получать доступ к данным профиля.
        С помощью метода child() мы указываем ветку к которой хотим обратиться,
        затем устанавливаем ValueEventListener для осуществления действий
         */
        databaseReference.child("Users").child(user.getUid()).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    //Получили профиль, добавили его в список
                    profiles.add(child.getValue(Firebase_Profile.class));

                    /*
                    Создаём объект профиля, устанавливаем ему профиль
                    из нашего списка
                     */
                    Firebase_Profile profile;
                    profile=profiles.get(0);


                    /*
                    После того как данные профиля были получены, на его основе создаём профиль
                    в локальной базу данных
                     */

                    dataBase.dao_profile().insert(new Entity_Profile(profile.getType(),
                            profile.getSurname(), profile.getName(), profile.getMiddlename(),
                            profile.getEmail(), profile.getPassword(), profile.getId(), Helper_CreateProfile.PHOTO));

                    //После этого переходим к загрузке дополнительных сведений
                    loadExtraInfo();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    /*
    Метод необходимый для завершения процесса регистрации
    Создаём данные в локальной базе данных,
    и отправляем их в БД на FireBase
     */

    private void finishRegistration(){

        //Достаём данные из хелпера
        //Тип профиля
        int type = Helper_CreateProfile.TYPE;
        //Врач или нет
        boolean doctor = Helper_CreateProfile.IS_DOCTOR;

        //Основные сведения пользователя
        String name = Helper_CreateProfile.NAME;
        String surName = Helper_CreateProfile.SURNAME;
        String middleName = Helper_CreateProfile.MIDDLENAME;
        String info = Helper_CreateProfile.INFO;

        //Данные для входа/регистрации
        String email = Helper_CreateProfile.PHONE_NUMBER;
        String password = Helper_CreateProfile.PASSWORD;

        byte[] profilePhoto = Helper_CreateProfile.PHOTO;

        /*
        Получаем пользователя, который был привязан к этому устройству
        в процесса регистрации
         */


        /*
        Создаём запись в локальной базе данных,
        на основе полученных из хелпера данных
         */
        dataBase.dao_profile().insert(new Entity_Profile(type, surName,
                name, middleName, email, password, user.getUid(), profilePhoto));


        /*
        Создаём запись в базе данных FireBase,
        на основе полученных данных из хелпера
         */
        databaseReference.child("Users").child(user.getUid()).child("Profile").push().setValue(
                new Firebase_Profile(type, surName,
                name, middleName, email, password, user.getUid()));


        //Получаем id пользователя из локальной базы данных
        String profileId = dataBase.dao_profile().getProfile().getId();

        uploadImage();

        /*
        Проверяем данные взятые из хелпера,
        а именно - проверяем тип профиля
        0 - needy
        1 - relative
        2 - volunteer
         */

        if(type == 0){

            //Создаём запись needy в локальной базе данных
            dataBase.dao_needy().insert(new Entity_Needy(profileId,
                    1, 1, 1, info, ""));

            //Создаём запись в БД FireBase
            databaseReference.child("Users").child(user.getUid()).child("Needy").push().setValue(
                    new Firebase_Needy(user.getUid(),
                    1, 1, 0, info, Helper_CreateProfile.ORGANIZATION));

        }

        else if(type == 1 && doctor){
            dataBase.dao_relative().insert(new Entity_Relative(profileId, true));

            databaseReference.child("Users").child(user.getUid()).child("Relative").push().setValue(
                    new Firebase_Relative(user.getUid(), true));
        }

        else if(type == 1 && !doctor){
            dataBase.dao_relative().insert(new Entity_Relative(profileId, false));

            databaseReference.child("Users").child(user.getUid()).child("Relative").push().setValue(
                    new Firebase_Relative(user.getUid(), false));
        }

        else if(type == 2){

            dataBase.dao_volunteer().insert(new Entity_Volunteer("Organization", profileId));

            databaseReference.child("Users").child(user.getUid()).child("Volunteer").push().setValue(
                    new Firebase_Volunteer(Helper_CreateProfile.ORGANIZATION, user.getUid()));
        }

        //После применённых изменений запускаем главную активность
        startMain();
    }


    private void loadExtraInfo(){

        if(profiles.get(0).getType() == 0){
            loadExtraNeedy(); }

        else if(profiles.get(0).getType() == 1){
            loadExtraDoctor(); }

        else if(profiles.get(0).getType() == 2){
            loadExtraVolunteer(); }

    }


    /*
    Метод нужный для загрузке доп. данных о профиле
    Тут загружаются данные о needy
     */
    private void loadExtraNeedy(){

        needys = new ArrayList<Firebase_Needy>();

        databaseReference.child("Users").child(user.getUid()).child("Needy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    needys.add(child.getValue(Firebase_Needy.class));

                    Firebase_Needy needy;
                    needy = needys.get(0);

                    dataBase.dao_needy().insert(new Entity_Needy(needy.getProfile_id(), needy.getSos_signal(),
                            needy.getHelp_signal(), needy.getState_signal(), needy.getInfo(), needy.getOrganization()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startMain();

    }





    public void loadExtraDoctor(){

        doctors = new ArrayList<Firebase_Relative>();

        databaseReference.child("Users").child(user.getUid()).child("Relative").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    doctors.add(child.getValue(Firebase_Relative.class));
                    Firebase_Relative relative;
                    relative = doctors.get(0);

                    dataBase.dao_relative().insert(new Entity_Relative(relative.getProfile_id(), relative.isDoctor()));

                    startMain();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    public void loadExtraVolunteer(){

        volunteers = new ArrayList<Firebase_Volunteer>();

        databaseReference.child("Users").child(user.getUid()).child("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    volunteers.add(child.getValue(Firebase_Volunteer.class));

                    Firebase_Volunteer volunteer;
                    volunteer = volunteers.get(0);
                    dataBase.dao_volunteer().insert(new Entity_Volunteer(volunteer.getOrganization(), volunteer.getProfile_id()));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startMain();

    }





    private void startMain(){
        Intent main = new Intent(this, Activity_Main.class);
        startActivity(main);
    }




    private void downloadImage(){

        FirebaseUser user_= mAuth.getCurrentUser();

        StorageReference root = rootRef.child(user_.getUid()).child("profilePhoto");

        final long ONE_MEGABYTE = 1024 * 1024;
        rootRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Helper_CreateProfile.PHOTO = bytes;

                startMain();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }




    private void uploadImage(){

        UploadTask uploadTask = rootRef.child(user.getUid()).child("profilePhoto").putBytes(Helper_CreateProfile.PHOTO);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }




    private void makeToast(String text){
        //Если всё хорошо, продолжаем регистрацию
        Toast.makeText(Activity_Login.this, text, Toast.LENGTH_SHORT).show();
    }




    //Имплементированные методы смены рабочего фрагмента
    @Override
    public void setFirst() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentFirstSelect);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setCreate() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentCreate);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setEnter() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentEnter);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void startMainAct(boolean login) {

        String password=Helper_CreateProfile.PASSWORD;
        String email=Helper_CreateProfile.PHONE_NUMBER;

        if(!login){
            registration(email, password);
        }
        else {
            login(email, password);
        }

    }




    @Override
    public void setNeedy() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentNeedy);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setRelative() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentRelative);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    @Override
    public void setVolun() {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentVolunteer);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
