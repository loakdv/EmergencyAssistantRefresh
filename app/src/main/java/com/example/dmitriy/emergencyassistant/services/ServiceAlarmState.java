/*
 *
 *  Created by Dmitry Garmyshev on 7/16/19 8:20 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/16/19 8:00 PM
 *
 */

package com.example.dmitriy.emergencyassistant.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityCustomer;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
Сервис который необходим для срабатывания будильника, что-бы отметить состояние пользователя
 */

public class ServiceAlarmState extends Service {


    //Переменные необходимые для формирования
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    private Looper mServiceLooper;
    private AlarmHandler alarmHandler;



    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Thread.MAX_PRIORITY);
        thread.start();

        mServiceLooper = thread.getLooper();
        alarmHandler = new AlarmHandler(mServiceLooper);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = alarmHandler.obtainMessage();
        msg.arg1 = startId;
        alarmHandler.sendMessage(msg);

        //Помечаем, что сервис должен работать даже после закрытия приложения
        return START_STICKY;
    }





    private final class AlarmHandler extends Handler{

        public AlarmHandler(Looper looper) {
            super(looper);
        }

        private int timeDelay = 5000;

        DataBaseAppDatabase dataBase = Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();

        @Override
        public void handleMessage(Message msg) {
            //В этом цикле у нас происходит образение к серверу

            boolean[] sendedState = new boolean[5];

            /*
            EntityCustomer needy=dataBase.dao_needy().getNeedy();


            try{
                while (needy.getState_signal()==1) {
                    synchronized (this) {
                        try {
                            Thread.sleep(timeDelay);

                            Date phoneDate = new Date();
                            SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm");


                            if(sdfTime.format(phoneDate).equals("09:00")&&sendedState[0]==false){

                                sendNotif("9 hours", "ALARM");


                                sendedState[0]=true;
                            }

                            else if(sdfTime.format(phoneDate).equals("12:00")&&sendedState[1]==false){
                                sendNotif("12 hours", "ALARM");
                                sendedState[1]=true;
                            }

                            else if(sdfTime.format(phoneDate).equals("15:00")&&sendedState[2]==false){
                                sendNotif("15 hours", "ALARM");
                                sendedState[2]=true;
                            }

                            else if(sdfTime.format(phoneDate).equals("18:00")&&sendedState[3]==false){
                                sendNotif("18 hours", "ALARM");
                                sendedState[3]=true;
                            }

                            else if(sdfTime.format(phoneDate).equals("21:00")&&sendedState[4]==false){
                                sendNotif("21 hours", "ALARM");
                                sendedState[4]=true;
                            }

                            else if(sdfTime.format(phoneDate).equals("21:01")){

                                //Обнуляем значения сигналов, что бы можно было отправить их повторно
                                sendedState[0]=false;
                                sendedState[1]=false;
                                sendedState[2]=false;
                                sendedState[3]=false;
                                sendedState[4]=false;
                            }


                            if(dataBase.dao_needy().getNeedy().getState_signal()==0){
                                stopSelf();
                                wait();
                            }


                        } catch (Exception e) {}


                    }
                }
            }
            catch (Exception e){}
             */



        }

        private void sendNotif(String title, String text){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                // Configure the notification channel.
                notificationChannel.setDescription("Channel description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(false);
                notificationManager.createNotificationChannel(notificationChannel);
            }



            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.drawable.ic_launcher_main)
                    .setContentTitle(title)
                    .setContentText(text);

            Intent intentNeedy = new Intent(getApplicationContext(), ActivityCustomer.class);
            intentNeedy.putExtra("check_state", true);

            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    intentNeedy, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }


    }




}
