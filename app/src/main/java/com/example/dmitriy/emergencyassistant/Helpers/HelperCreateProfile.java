package com.example.dmitriy.emergencyassistant.Helpers;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/*
Хелпер который нужен для создания в нём временных переменных
 */
public class HelperCreateProfile {


    public static long id;

    /*
    0 - Needy
    1 - Relative
    2 - Doctor
    3 - Volunteer
     */
    public static int TYPE =0;

    public static String SURNAME = "Не указано";
    public static String NAME = "Не указано";
    public static String MIDDLENAME = "Не указано";

    public static String EMAIL;
    public static String PASSWORD;

    public static String INFO = "Не указано";
    public static String ORGANIZATION ="Не на соц. обслуживании";
    public static String VOLUNTEER_ORGANIZATION = "Не указано";

    public static boolean IS_DOCTOR;

    public static byte[] PHOTO;

    public static void setPHOTO(Bitmap bitmap){


        //Меняем размер изображения
        Bitmap scaledBitmap= scaleDown(bitmap, 300, true);

        byte[] imageArray;

        //Поток преобразования изображения в байт-массив
        ByteArrayOutputStream streamImage = new ByteArrayOutputStream();
        //Переводим bitmap в нужный нам формат
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, streamImage);
        //Переводим полученный bitmap в байт-массив, и присваиваем результат к локальному массиву изображения
        imageArray=streamImage.toByteArray();
        HelperCreateProfile.PHOTO =imageArray;

        PHOTO = imageArray;
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


}
