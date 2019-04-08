package com.example.dmitriy.emergencyassistant.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.dmitriy.emergencyassistant.Activities.Based.Activity_Main;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.RoomDatabase.DataBase_AppDatabase;

import static android.content.Context.NOTIFICATION_SERVICE;


public class Broadcast_AlarmState extends BroadcastReceiver {


    DataBase_AppDatabase dataBase;

    //Переменные необходимые для формирования
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        int h=intent.getIntExtra("time", 0);

        Log.i("NOTIF", "RECEIVER: "+h);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.ic_launcher_main)
                .setContentTitle("Оценка состояния")
                .setContentText("Пожалуйста, оцените ваше состояние!");

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, Activity_Main.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Log.i("NOTIF", "SEND NOTIF");

        builder.setContentIntent(contentIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }





}
