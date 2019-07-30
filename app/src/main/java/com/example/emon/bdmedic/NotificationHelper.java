package com.example.emon.bdmedic;

import android.content.Context;

import android.support.v4.app.NotificationCompat;

import android.support.v4.app.NotificationManagerCompat;



public class NotificationHelper {



    public static void displayNotification(Context context, String title, String body) {



        NotificationCompat.Builder mBuilder =

                new NotificationCompat.Builder(context, RequestBlood.CHANNEL_ID)

                        .setSmallIcon(R.drawable.ic_003_blood)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);



        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);

        mNotificationMgr.notify(1, mBuilder.build());



    }



}