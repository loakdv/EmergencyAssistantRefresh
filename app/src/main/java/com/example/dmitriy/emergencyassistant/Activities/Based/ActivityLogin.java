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

import com.example.dmitriy.emergencyassistant.Activities.Dialogs.Info.ActivityDialogWelcomeMenu;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJONeedy;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJOProfile;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJORelative;
import com.example.dmitriy.emergencyassistant.Retrofit.POJOs.Login.POJOVolunteer;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginEnter;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginCreateAccount;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginCreateRequest;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginFirstSelect;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginNeedy;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginRelative;
import com.example.dmitriy.emergencyassistant.Fragments.Login.FragmentLoginVolunteer;
import com.example.dmitriy.emergencyassistant.Helpers.HelperCreateProfile;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBaseAppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.EntityNeedy;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Profile.EntityProfile;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Relative.EntityRelative;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Volunteer.EntityVolunteer;
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

public class ActivityLogin extends AppCompatActivity implements
        FragmentLoginFirstSelect.ChangeLoginFragment {



    private static final String LOGIN_PREFERENCES = "LOGIN_SETTINGS";
    private SharedPreferences loginPreferences;

    //Фрагменты для создания аккаунта и для авторизации
    private FragmentLoginFirstSelect fragmentFirstSelect;
    private FragmentLoginEnter fragmentEnter;
    private FragmentLoginCreateAccount fragmentCreate;
    private FragmentLoginNeedy fragmentNeedy;
    private FragmentLoginRelative fragmentRelative;
    private FragmentLoginVolunteer fragmentVolunteer;
    private FragmentLoginCreateRequest fragmentLoginCreateRequest;

    //Транзакция
    private FragmentTransaction fragmentTransaction;

    //Локальная база данных
    private DataBaseAppDatabase dataBase;


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
    public List<POJOProfile> profiles;
    public List<POJONeedy> needys;
    public List<POJORelative> doctors;
    public List<POJOVolunteer> volunteers;



    /*
   Эти переменные изначально хранились в методе finishRegistration,
   но я решил их перенести в поля класса, и вынести их инициализацию
   в отдельный метод
   */

    //Достаём данные из хелпера
    //Тип профиля
    private int profileType = HelperCreateProfile.TYPE;
    //Врач или нет
    private boolean profileIsDoctor = HelperCreateProfile.IS_DOCTOR;

    //Основные сведения пользователя
    private String profileName = HelperCreateProfile.NAME;
    private String profileSurname = HelperCreateProfile.SURNAME;
    private String profileMiddlename = HelperCreateProfile.MIDDLENAME;
    private String profileInfo = HelperCreateProfile.INFO;

    //Данные для входа/регистрации
    private String profileEmail = HelperCreateProfile.EMAIL;
    private String profilePassword = HelperCreateProfile.PASSWORD;

    private byte[] profilePhoto = HelperCreateProfile.PHOTO;

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

        getIntentMessages();

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
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    private void initializeFragments(){
        //Инициализация фрагментов
        fragmentFirstSelect = new FragmentLoginFirstSelect();
        fragmentEnter = new FragmentLoginEnter();
        fragmentCreate = new FragmentLoginCreateAccount();
        fragmentNeedy = new FragmentLoginNeedy();
        fragmentRelative = new FragmentLoginRelative();
        fragmentVolunteer = new FragmentLoginVolunteer();
        fragmentLoginCreateRequest = new FragmentLoginCreateRequest();
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
            profiles = new ArrayList<POJOProfile>();

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
                        profiles.add(child.getValue(POJOProfile.class));

                    /*
                    Создаём объект профиля, устанавливаем ему профиль
                    из нашего списка
                     */
                        POJOProfile profile;
                        profile=profiles.get(0);


                    /*
                    После того как данные профиля были получены, на его основе создаём профиль
                    в локальной базу данных
                     */


                    downloadImage();

                    dataBase.dao_profile().insert(new EntityProfile(profile.getType(),
                            profile.getSurname(), profile.getName(), profile.getMiddlename(),
                            profile.getEmail(), profile.getPassword(), profile.getId(), HelperCreateProfile.PHOTO));

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
            //После применённых изменений запускаем главную активность
            startMain(true);
        }

        else if(profileType == 1){
            createVolunteer();
            startMain(false);
        }



    }




    private void loadExtraInfo(){

        if(profiles.get(0).getType() == 0){
            loadExtraNeedy(); }

        else if(profiles.get(0).getType() == 1){
            loadExtraVolunteer();}

    }




    /*
    Метод нужный для загрузке доп. данных о профиле
    Тут загружаются данные о needy
     */
    private void loadExtraNeedy(){

        needys = new ArrayList<POJONeedy>();

        databaseReference.child("Users").child(user.getUid()).child("Needy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    needys.add(child.getValue(POJONeedy.class));

                    POJONeedy needy;
                    needy = needys.get(0);

                    dataBase.dao_needy().insert(new EntityNeedy(needy.getProfile_id(), needy.getSos_signal(),
                            needy.getHelp_signal(), needy.getState_signal(), needy.getInfo(), needy.getOrganization()));

                    startMain(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }










    public void loadExtraVolunteer(){

        volunteers = new ArrayList<POJOVolunteer>();

        databaseReference.child("Users").child(user.getUid()).child("Volunteer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    volunteers.add(child.getValue(POJOVolunteer.class));

                    POJOVolunteer volunteer;
                    volunteer = volunteers.get(0);
                    dataBase.dao_volunteer().insert(new EntityVolunteer(volunteer.getOrganization(), volunteer.getProfile_id()));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startMain(false);

    }





    private void startMain(boolean isNeedy){
        setPreferencesConfirmed();

        if(isNeedy){
            Intent i = new Intent(getApplicationContext(), ActivityNeedySettings.class);
            startActivity(i);
        }
        else {
            Intent main = new Intent(this, ActivityMain.class);
            startActivity(main);
        }

    }




    private void downloadImage(){

        FirebaseUser user_= mAuth.getCurrentUser();

        rootRef.child(user_.getUid()).child("profilePhoto");


        final long ONE_MEGABYTE = 1024 * 1024;

        rootRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                HelperCreateProfile.PHOTO = bytes;

                startMain(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }




    private void uploadImage(){

        UploadTask uploadTask = rootRef.child(user.getUid()).child("profilePhoto").putBytes(HelperCreateProfile.PHOTO);

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

        profileType = HelperCreateProfile.TYPE;
        profileIsDoctor = HelperCreateProfile.IS_DOCTOR;

        profileName = HelperCreateProfile.NAME;
        profileSurname = HelperCreateProfile.SURNAME;
        profileMiddlename = HelperCreateProfile.MIDDLENAME;
        profileInfo = HelperCreateProfile.INFO;

        profileEmail = HelperCreateProfile.EMAIL;
        profilePassword = HelperCreateProfile.PASSWORD;

        profilePhoto = HelperCreateProfile.PHOTO;

    }



    //Отдельный метод для более быстрого и удобного создания тостов
    private void makeToast(String text){
        //Если всё хорошо, продолжаем регистрацию
        Toast.makeText(ActivityLogin.this, text, Toast.LENGTH_SHORT).show();
    }


    /*
    Все последующие методы create я создал для того, что бы не нагромождать
    код в методе finishRegistration
    Всё что они делают - создают записи с данными о профиле
    на сервере и в локальной БД
     */

    private void createVolunteer(){
        dataBase.dao_volunteer().insert(new EntityVolunteer("Organization", profileId));

        databaseReference.child("Users").child(user.getUid()).child("Volunteer").push().setValue(
                new POJOVolunteer(HelperCreateProfile.VOLUNTEER_ORGANIZATION, user.getUid()));
    }

    private void createNeedy(){
        dataBase.dao_needy().insert(new EntityNeedy(profileId,
                1, 1, 0, profileInfo, ""));

        databaseReference.child("Users").child(user.getUid()).child("Needy").push().setValue(
                new POJONeedy(user.getUid(),
                        1, 1, 0, profileInfo, HelperCreateProfile.ORGANIZATION));
    }

    private void createRelative(boolean isDoctor){
        dataBase.dao_relative().insert(new EntityRelative(profileId, isDoctor));

        databaseReference.child("Users").child(user.getUid()).child("Relative").push().setValue(
                new POJORelative(user.getUid(), isDoctor));
    }

    private void createLocalProfile(){
        /*
        Создаём запись в локальной базе данных,
        на основе полученных из хелпера данных
         */
        dataBase.dao_profile().insert(new EntityProfile(profileType, profileSurname,
                profileName, profileMiddlename, profileEmail, profilePassword, user.getUid(), profilePhoto));
    }

    private void createServerProfile(){
        /*
        Создаём запись в базе данных FireBase,
        на основе полученных данных из хелпера
         */
        databaseReference.child("Users").child(user.getUid()).child("Profile").push().setValue(
                new POJOProfile(profileType, profileSurname,
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
        Intent i = new Intent(this, ActivityDialogWelcomeMenu.class);
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


    private void getIntentMessages(){
        boolean isFastUser = getIntent().getBooleanExtra("isFastUser", false);
        if (isFastUser){
            String login = getIntent().getStringExtra("fastEmail");
            String password = getIntent().getStringExtra("fastPassword");
            login(login, password);
        }
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



        /*
    public void loadExtraDoctor(){

        doctors = new ArrayList<POJORelative>();

        databaseReference.child("Users").child(user.getUid()).child("Relative").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    doctors.add(child.getValue(POJORelative.class));
                    POJORelative relative;
                    relative = doctors.get(0);

                    dataBase.dao_relative().insert(new EntityRelative(relative.getProfile_id(), relative.isDoctor()));

                    startMain(false);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
     */
}
