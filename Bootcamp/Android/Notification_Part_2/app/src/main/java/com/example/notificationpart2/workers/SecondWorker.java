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

import static com.example.notificationpart2.constants.Constants.CHANNEL_SECOND_TITLE;
import static com.example.notificationpart2.constants.Constants.CHANNEL_TWO_DESCRIPTION;
import static com.example.notificationpart2.constants.Constants.CHANNEL_TWO_ID;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_FIRST_ID;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_SECOND_MESSAGE;
import static com.example.notificationpart2.constants.Constants.NOTIFICATION_SECOND_TITLE;

public class SecondWorker extends Worker {

    Context mContext;

    public SecondWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        secondNotification();
        return Result.success();
    }
    private void secondNotification() {

     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
        NotificationChannel channelSecond = new NotificationChannel(CHANNEL_TWO_ID, CHANNEL_SECOND_TITLE, NotificationManager.IMPORTANCE_LOW);
        channelSecond.setDescription(CHANNEL_TWO_DESCRIPTION);
        notificationManager.createNotificationChannel(channelSecond);
    }

//        Step 2 - Build Notification give it title and message and choose a notification channel

        NotificationCompat.Builder builderTwo = new NotificationCompat.Builder(mContext, CHANNEL_TWO_ID)

            .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(NOTIFICATION_SECOND_TITLE)
                .setContentText(NOTIFICATION_SECOND_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat.from(mContext).notify(NOTIFICATION_FIRST_ID,builderTwo.build());

}
}
