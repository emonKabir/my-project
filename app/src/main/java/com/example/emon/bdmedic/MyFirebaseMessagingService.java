package com.example.emon.bdmedic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String MY_PREFS_NAME = "Token";
    @Override
    public void onNewToken(String s) {
        Log.e("NEW_TOKEN", s);
        storeToken(s);
        System.out.println("NEW TOKEN...................... : "+s);
    }

    private void storeToken(String token){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("token_id", token);
        editor.apply();
    }
    @Override

    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);



        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();

            System.out.println("notification title : "+title);
            Log.e("notificationTitle",title);
            String body = remoteMessage.getNotification().getBody();
            System.out.println("notification body : "+body);
            Log.e("notificationTitle",body);

            showNotification(title,body);

//            NotificationHelper.displayNotification(getApplicationContext(), title, body);

        }

    }

    private void showNotification(String title, String body) {

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        final String NOTIFICATION_CHANNEL_ID = "com.example.emon.test";
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

             NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);

             notificationChannel.setDescription("BdMedic");
             notificationChannel.enableLights(true);
             notificationChannel.setLightColor(Color.BLUE);
             notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
             notificationManager.createNotificationChannel(notificationChannel);


         }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

         notificationBuilder.setAutoCancel(true)
                 .setDefaults(Notification.DEFAULT_ALL)
                 .setWhen(System.currentTimeMillis())
                 .setSmallIcon(R.drawable.ic_favorite_border_black_24dp)
                 .setContentTitle(title)
                 .setContentText(body)
                 .setContentInfo("Info");

         notificationManager.notify(new Random().nextInt(),notificationBuilder.build());
    }









    private void saveToken(String refreshedToken) {
        String phone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        System.out.println("Done!!!...................... : "+phone);
        User user = new User(phone,refreshedToken);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {

                    System.out.println("Done!!!");
                }
            }
        });

    }

}
