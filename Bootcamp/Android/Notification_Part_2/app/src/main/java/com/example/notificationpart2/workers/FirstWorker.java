package com.example.notificationpart2.workers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.notificationpart2.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static com.example.notificationpart2.constants.Constants.CHANNEL_FIRST_TITLE;
import static com.example.notificationpart2.constants.Constants.CHANNEL_ONE_DESCRIPTION;
import static com.example.notificationpart2.constants.Constants.CHANNEL_ONE_ID;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_FIRST_ID;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_FIRST_MESSAGE;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_FIRST_TITLE;

public class FirstWorker extends Worker {

    Context mContext;

    public FirstWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        sendNotification();

        return Result.success();
    }

    private void sendNotification() {

//        Step 1 (in Channel we describe the sound property of a channel by telling the priority of the channel)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            NotificationChannel channelOne = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_FIRST_TITLE, NotificationManager.IMPORTANCE_HIGH);
            channelOne.setDescription(CHANNEL_ONE_DESCRIPTION);
            channelOne.setVibrationPattern(new long[]{0,50,100,50});
            notificationManager.createNotificationChannel(channelOne);
        }

//        Step 2 - Build Notification give it title and message and choose a notification channel

        NotificationCompat.Builder builderOne = new NotificationCompat.Builder(mContext, CHANNEL_ONE_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(NOTIFICATION_FIRST_TITLE)
                .setContentText(NOTIFICATION_FIRST_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat.from(mContext).notify(NOTIFICATION_FIRST_ID,builderOne.build());

    }
}
