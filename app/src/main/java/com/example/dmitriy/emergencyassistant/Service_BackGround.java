package com.example.dmitriy.emergencyassistant;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Service_BackGround extends Service {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private List<Firebase_Task> tasks;

    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";



    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){




        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {
        startForeground();

        //Инициализируем аккаунт устройства
        mAuth=FirebaseAuth.getInstance();
        //Инициализируем базу данных FireBase
        databaseReference= FirebaseDatabase.getInstance().getReference();

        final FirebaseUser user=mAuth.getCurrentUser();

        databaseReference.child("Users").child(user.getUid()).child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                tasks=new ArrayList<Firebase_Task>();
                /*
                Получение профиля мы осуществляем с помощью итерации
                 */
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    //Получили профиль, добавили его в список
                    tasks.add(child.getValue(Firebase_Task.class));
                }


                if(!tasks.isEmpty()){
                    Firebase_Task task=tasks.get(0);
                    sendNotif("SOS", "Сигнал SOS от пользователя: "+task.getInitials());
                    /*
                    Intent i=new Intent(Service_BackGround.this, Activity_See_Task.class);
                    i.putExtra("Initials", task.getInitials());
                    i.putExtra("Type", task.getType());
                    Log.d("SIGNAL", "TASK TASK TASK");
                    startActivity(i);
                    */


                    databaseReference.child("Users").child(user.getUid()).child("Tasks").removeValue();
                }
                else {
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendNotif(String title, String text){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 0, 0, 0});
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setVibrate(new long[]{0, 0, 0, 0, 0, 0})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, Activity_DoctorRelative.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_main)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }


}

