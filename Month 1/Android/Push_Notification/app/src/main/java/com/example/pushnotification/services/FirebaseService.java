package com.example.pushnotification.services;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.pushnotification.R;
import com.example.pushnotification.activities.ItemScreen;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseService extends FirebaseMessagingService {



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent touchIntent = new Intent(getApplicationContext(), ItemScreen.class);

        Bitmap bigPictureBitmap = null;

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("Sarthak", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            Log.d("Sarthak", "Message data payload: " + remoteMessage.getData());
            Bundle bundle = new Bundle();

            try {

                bigPictureBitmap = Glide.with(this)
                        .asBitmap()
                        .load(object.get("image"))
                        .submit().get();



                bundle.putString("price",object.getString("price"));
                bundle.putString("image",object.getString("image"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            touchIntent.putExtras(bundle);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Sarthak", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d("Sarthak", "Message Notification title: " + remoteMessage.getNotification().getTitle());

            NotificationManager notificationManager ;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationManager = this.getSystemService(NotificationManager.class);
                NotificationChannel channelSecond = new NotificationChannel("channel id","channel name", NotificationManager.IMPORTANCE_HIGH);
                channelSecond.setDescription("channel description");
                notificationManager.createNotificationChannel(channelSecond);
            }



            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,touchIntent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder builderTwo = new NotificationCompat.Builder(this,"channel id" )

                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPictureBitmap))
                    .setLargeIcon(bigPictureBitmap);

            NotificationManagerCompat.from(this).notify(1,builderTwo.build());






        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}
