package com.example.dmitriy.emergencyassistant.Activities.Dialogs.Adds;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;
import com.example.dmitriy.emergencyassistant.RoomDatabase.Entities.Needy.Entity_Added_PhoneNumbers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Activity_Dialog_AddNumber extends AppCompatActivity {

    /*
    Диалоговое окно для меню добавления номера
     */

    /*
    Кнопки принятия номера и отмены действия
    Принажатии на кнопку final должны забиваться
    данные в базу данных
     */

    //Элементы экрана
    private EditText etName;
    private EditText etNumbers;

    private Button btnCancel;
    private Button btnFinal;
    private ImageButton btnSelectImage;


    //База данных
    private DataBase_AppDatabase dataBase;


    //Элементы для добавления фото контакта
    private Bitmap bitmap;

    //Байт массив используется для хренения его в БД
    private  byte[] imageArray;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_dialog_newnumber);

       initializeDataBase();

       View.OnClickListener oclBtn=new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId()){
                   case R.id.btn_CancelAddNumber:
                       finish();
                       break;
                   case R.id.btn_CommitAddNumber:
                       /*
                       Вызываем метод создания номера в нужной активности
                       Передаём туда полученные из полей ввода значения:
                       Имя, номер, id, изображение
                        */
                       if(checkFields()){

                           Toast.makeText(getApplicationContext(), "Вы не можете оставить пустыми поля номера и имени!",
                                   Toast.LENGTH_SHORT).show();

                       }
                       else {
                           addNumberToDB();
                           finish();
                       }
                       break;
                   case R.id.btn_SelectNumberImage:
                       startPhotoPicker();
                       break;
               }
           }
       };

       //Поля ввода
       etName=findViewById(R.id.et_NumberName);
       etNumbers=findViewById(R.id.et_PhoneNumber);

       //Имициализация кнопок
       btnCancel=findViewById(R.id.btn_CancelAddNumber);
       btnCancel.setOnClickListener(oclBtn);
       btnFinal=findViewById(R.id.btn_CommitAddNumber);
       btnFinal.setOnClickListener(oclBtn);
       btnSelectImage=findViewById(R.id.btn_SelectNumberImage);
       btnSelectImage.setOnClickListener(oclBtn);

    }




    //Метод для проверки введённых полей
    private boolean checkFields(){
        return etName.getText().toString().isEmpty()||
                etNumbers.getText().toString().isEmpty();
    }




    //Метод для инициализации БД
    private void initializeDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBase_AppDatabase.class, "note_database").
                allowMainThreadQueries().build();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    try {
                        //Получаем bitmap нужного изображения
                        bitmap = MediaStore.Images.Media.getBitmap(
                                getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Меняем размер изображения
                    Bitmap scaledBitmap= scaleDown(bitmap, 300, true);

                    //Устанавливаем кнопке выбранное изображение
                    btnSelectImage.setImageBitmap(scaledBitmap);
                    //Поток преобразования изображения в байт-массив
                    ByteArrayOutputStream streamImage = new ByteArrayOutputStream();
                    //Переводим bitmap в нужный нам формат
                    scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, streamImage);
                    //Переводим полученный bitmap в байт-массив, и присваиваем результат к локальному массиву изображения
                    imageArray=streamImage.toByteArray();
                }
        }
    }




    //Метод для сжатия размеров изображения
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }




    private void startPhotoPicker(){

        //Обращаемся к активности выбора фото из галереи
        Intent photoPickerIntent =new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }




    private void addNumberToDB(){

        //Вставляем запись в БД и закрываем окно
        dataBase.dao_added_phoneNumbers().
                insert(new Entity_Added_PhoneNumbers(etName.getText().toString(),
                        etNumbers.getText().toString(),
                        imageArray, dataBase.dao_needy().getNeedy().getId()));
    }


}
