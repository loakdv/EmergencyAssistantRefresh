package com.example.dmitriy.emergencyassistant.Activities.Based;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.Activity_WelcomeMenu;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Needy;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Profile;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Relative;
import com.example.dmitriy.emergencyassistant.Firebase.Firebase_Volunteer;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_LoginEnter;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_CreateAccount;
import com.example.dmitriy.emergencyassistant.Fragments.Login.Fragment_Login_CreateRequest;
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

/*
Активность для создания аккаунта
Здесь задействованы элементы Firebase
*/

public class Activity_Login extends AppCompatActivity implements
        Fragment_Login_FirstSelect.ChangeLoginFragment {



    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private SharedPreferences loginPreferences;

    //Фрагменты для создания аккаунта и для авторизации
    private Fragment_Login_FirstSelect fragmentFirstSelect;
    private Fragment_LoginEnter fragmentEnter;
    private Fragment_Login_CreateAccount fragmentCreate;
    private Fragment_Login_Needy fragmentNeedy;
    private Fragment_Login_Relative fragmentRelative;
    private Fragment_Login_Volunteer fragmentVolunteer;
    private Fragment_Login_CreateRequest fragmentLoginCreateRequest;

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



    /*
   Эти переменные изначально хранились в методе finishRegistration,
   но я решил их перенести в поля класса, и вынести их инициализацию
   в отдельный метод
   */

    //Достаём данные из хелпера
    //Тип профиля
    private int profileType = Helper_CreateProfile.TYPE;
    //Врач или нет
    private boolean profileIsDoctor = Helper_CreateProfile.IS_DOCTOR;

    //Основные сведения пользователя
    private String profileName = Helper_CreateProfile.NAME;
    private String profileSurname = Helper_CreateProfile.SURNAME;
    private String profileMiddlename = Helper_CreateProfile.MIDDLENAME;
    private String profileInfo = Helper_CreateProfile.INFO;

    //Данные для входа/регистрации
    private String profileEmail = Helper_CreateProfile.EMAIL;
    private String profilePassword = Helper_CreateProfile.PASSWORD;

    private byte[] profilePhoto = Helper_CreateProfile.PHOTO;

    private String profileId;






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

        getPreferences();

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




    //Метод который инициплизирует локальную БД
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
        fragmentLoginCreateRequest = new Fragment_Login_CreateRequest();
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

                    initialiseFirebaseObj();

                    finishRegistration();

                }
                else {
                    makeToast(task.getException().toString());
                    Log.d("REGISTRATION", task.getException().toString());
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
                    initialiseFirebaseObj();

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
    Получаем данные из серверной БД, и на основе этих данных
    создаём запись в локальной БД
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


                    downloadImage();

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

        /*
        Инициализируем поля из хелпера
         */
        initializeRegistrationFields();


        /*
        Получаем пользователя, который был привязан к этому устройству
        в процесса регистрации
         */
        initialiseFirebaseObj();

/*
        Создаём запись в локальной базе данных,
        на основе полученных из хелпера данных
         */
        createLocalProfile();

        /*
        Создаём запись в базе данных FireBase,
        на основе полученных данных из хелпера
         */
        createServerProfile();

        //Получаем id пользователя из локальной базы данных
        getProfileId();

        //Отправляем на сервер фото профиля
        uploadImage();


        /*
        Проверяем данные взятые из хелпера,
        а именно - проверяем тип профиля
        0 - needy
        1 - relative
        2 - volunteer
         */

        if(profileType == 0){
            createNeedy();
        }

        else if(profileType == 1 && profileIsDoctor){
            createRelative(profileIsDoctor);
        }

        else if(profileType == 1 && !profileIsDoctor){
            createRelative(profileIsDoctor);
        }

        else if(profileType == 2){
            createVolunteer();
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

                    startMain();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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

        setPreferencesConfirmed();

        Intent main = new Intent(this, Activity_Main.class);
        startActivity(main);
    }




    private void downloadImage(){

        FirebaseUser user_= mAuth.getCurrentUser();

        rootRef.child(user_.getUid()).child("profilePhoto");


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



    /*
    Метод который инициализирует переменные взятые из хелпера
    Вынес это всё в отдельный метод, что бы не нагромождать это
    всё в другом методе
     */
    private void initializeRegistrationFields(){

        profileType = Helper_CreateProfile.TYPE;
        profileIsDoctor = Helper_CreateProfile.IS_DOCTOR;

        profileName = Helper_CreateProfile.NAME;
        profileSurname = Helper_CreateProfile.SURNAME;
        profileMiddlename = Helper_CreateProfile.MIDDLENAME;
        profileInfo = Helper_CreateProfile.INFO;

        profileEmail = Helper_CreateProfile.EMAIL;
        profilePassword = Helper_CreateProfile.PASSWORD;

        profilePhoto = Helper_CreateProfile.PHOTO;

    }



    //Отдельный метод для более быстрого и удобного создания тостов
    private void makeToast(String text){
        //Если всё хорошо, продолжаем регистрацию
        Toast.makeText(Activity_Login.this, text, Toast.LENGTH_SHORT).show();
    }


    /*
    Все последующие методы create я создал для того, что бы не нагромождать
    код в методе finishRegistration
    Всё что они делают - создают записи с данными о профиле
    на сервере и в локальной БД
     */

    private void createVolunteer(){
        dataBase.dao_volunteer().insert(new Entity_Volunteer("Organization", profileId));

        databaseReference.child("Users").child(user.getUid()).child("Volunteer").push().setValue(
                new Firebase_Volunteer(Helper_CreateProfile.ORGANIZATION, user.getUid()));
    }

    private void createNeedy(){
        dataBase.dao_needy().insert(new Entity_Needy(profileId,
                1, 1, 0, profileInfo, ""));

        databaseReference.child("Users").child(user.getUid()).child("Needy").push().setValue(
                new Firebase_Needy(user.getUid(),
                        1, 1, 0, profileInfo, Helper_CreateProfile.ORGANIZATION));
    }

    private void createRelative(boolean isDoctor){
        dataBase.dao_relative().insert(new Entity_Relative(profileId, isDoctor));

        databaseReference.child("Users").child(user.getUid()).child("Relative").push().setValue(
                new Firebase_Relative(user.getUid(), isDoctor));
    }

    private void createLocalProfile(){
        /*
        Создаём запись в локальной базе данных,
        на основе полученных из хелпера данных
         */
        dataBase.dao_profile().insert(new Entity_Profile(profileType, profileSurname,
                profileName, profileMiddlename, profileEmail, profilePassword, user.getUid(), profilePhoto));
    }

    private void createServerProfile(){
        /*
        Создаём запись в базе данных FireBase,
        на основе полученных данных из хелпера
         */
        databaseReference.child("Users").child(user.getUid()).child("Profile").push().setValue(
                new Firebase_Profile(profileType, profileSurname,
                        profileName, profileMiddlename, profileEmail, profilePassword, user.getUid()));

    }



    /*
    Метод который получает id профиля
    Вынесен отдельно для удобства
     */
    private void getProfileId(){
        profileId = dataBase.dao_profile().getProfile().getId();
    }


    private void startWelcomeMenu(){
        Intent i = new Intent(this, Activity_WelcomeMenu.class);
        startActivity(i);
    }


    /*
    Этот метод выполняет свою работу при первом запуске приложения
    Если приложение запущено в первый раз - открываем окно приветствия
     */
    private void getPreferences(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstLogin = loginPreferences.getBoolean("isFirstStartConfirmed", false);

        if (!isFirstLogin){
            startWelcomeMenu();
        }
    }


    /*
    Устанавливаем переменную "первого запуска" правдивой
     */
    private void setPreferencesConfirmed(){
        loginPreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putBoolean("isFirstStartConfirmed", true);
        editor.apply();
    }



    /*
    После вызова этого метода, засчет переменной
    мы определяем что делать дальше - логинимся,
    или же создаём новый профиль
     */
    @Override
    public void continueLogin(boolean login) {

        initializeRegistrationFields();

        if(!login){
            registration(profileEmail, profilePassword);
        }
        else {
            login(profileEmail, profilePassword);
        }
    }




    //Имплементированные методы смены рабочего фрагмента

    /*
    Все эти методы я убрал в самый низ, т.к. они однотипные и в принципе выполняют
    одну и ту же роль

    Так они не будут мешаться
     */
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

    @Override
    public void setRequest() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContLogin, fragmentLoginCreateRequest);
        fragmentTransaction.commit();
    }
}
