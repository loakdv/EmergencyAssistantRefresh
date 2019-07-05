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
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.retrofit.pojo.customer.POJOSignal;
import com.example.dmitriy.emergencyassistant.R;
import com.example.dmitriy.emergencyassistant.roomDatabase.DataBaseAppDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/*
Сервис который постоянно обращается к серверу, что бы получить уведомление о помощи
 */
public class ServiceBackGround extends Service {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    //Лист для скидывания его в таски
    private List<POJOSignal> tasks;

    //Переменные необходимые для формирования
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    //Нужны для хендлера
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;



    @Override
    public void onCreate() {
        /*
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Thread.MAX_PRIORITY);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
         */

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        FirebaseApp.initializeApp(getApplicationContext());

        //Остаток от Firebase
        /*
        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();
         */

        //Log.d("SERVICE PUSH", "Start push service");

        /*
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
         */

        //Помечаем, что сервис должен работать даже после закрытия приложения
        return START_STICKY;

    }





    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {

        public ServiceHandler(Looper looper) {
            super(looper);
        }

        private int timeDelay=10000;

        DataBaseAppDatabase dataBase= Room.databaseBuilder(getApplicationContext(),
                DataBaseAppDatabase.class, "note_database").
                allowMainThreadQueries().build();

        @Override
        public void handleMessage(Message msg) {
            //В этом цикле у нас происходит образение к серверу
            while (true) {
                synchronized (this) {
                    try {

                        Thread.sleep(timeDelay);

                        //Инициализируем аккаунт устройства
                        mAuth=FirebaseAuth.getInstance();
                        //Инициализируем базу данных FireBase
                        databaseReference= FirebaseDatabase.getInstance().getReference();

                        //Ищем нашего пользователя
                        final FirebaseUser user=mAuth.getCurrentUser();

                        /*
                        if(dataBase.dao_user().getProfile() != null){
                            databaseReference.child("Users").child(user.getUid()).child("Tasks").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    tasks=new ArrayList<POJOSignal>();

                                    //Получение таска с помощью итерации
                                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                                        //Получили профиль, добавили его в список
                                        tasks.add(child.getValue(POJOSignal.class));
                                    }
                                    if(!tasks.isEmpty()){
                                        POJOSignal task=tasks.get(0);
                                        sendNotif("SOS!", "Пользователь: "+task.getInitials()+" отправил сигнал SOS!");
                                    }

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) { }
                            });
                        }
                         */
                        /*
                        else {
                            stopSelf();
                            wait();
                        }
                         */


                    } catch (Exception e) {
                    }
                }
            }
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

            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    new Intent(getApplicationContext(), ActivityMain.class), PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

    }




}

