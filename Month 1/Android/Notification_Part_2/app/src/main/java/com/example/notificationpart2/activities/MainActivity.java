package com.example.notificationpart2.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.notificationpart2.R;
import com.example.notificationpart2.workers.FirstWorker;
import com.example.notificationpart2.workers.SecondWorker;

import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import static com.example.notificationpart2.constants.Constants.*;

public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat mNotificationManager;
    NotificationCompat.Builder mBuilderOne,mBuilderTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = NotificationManagerCompat.from(this);
/*

        Notifiaton Channel priorities :-
        There are 6 types of priorities in notification channel

        1.IMPORTANCE_NONE ( = 0)        will not gonna see anywhere
        2.IMPORTNACE_MIN ( = 1)         will only shows in the notification shade
        3.IMPORTNACE_LOW ( = 2)         will show everywhere but will not make any disruption
        4.IMPORTNACE_DEFAULT ( = 3)     will show everywhere and make noise but will not make any disruption
        5.IMPORTNACE_HIGH ( = 4)        will show everywhere and make noise and also can use big banner
        6.IMPORTNACE_MAX ( = 5)         unused by android will use in future




To Set up Notification contains two steps
* Step 1 - Create a notification channel
* Step 2 - Create notification using notification builder(many same sound property notifications can be bind up with one channel)
* */
        setUpFirstNotification();

        setUpSecondNotification();

//        handling the clicking of buttons by using on click listners on the buttons by finding them by their id


            findViewById(R.id.bt_alternative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callAlternatively();

            }
        });


            findViewById(R.id.bt_workmanager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//              one time request for first worker to create notification one and set delay of one minute

                OneTimeWorkRequest oneTimeWorkRequestFirst = new OneTimeWorkRequest.Builder(FirstWorker.class)
                        .setInitialDelay(1, TimeUnit.MINUTES)
                        .build();

//                one time request for second worker to create notification two and set delay of one minute

                OneTimeWorkRequest oneTimeWorkRequestSecond = new OneTimeWorkRequest.Builder(SecondWorker.class)
                        .setInitialDelay(1,TimeUnit.MINUTES)
                        .build();

//              add one time request for first and second notification in the queue of work manager

                WorkManager.getInstance()
                        .beginWith(oneTimeWorkRequestFirst)
                        .then(oneTimeWorkRequestSecond)
                        .enqueue();


            }
        });






    }

    private void callAlternatively() {

        /*This method is used to create two notifications alternatively after one minute
        * in which first notification is with sound and vibration and second one is silent
        *
        * for achieving this create a seprate thread to prevent from ANR(application not responding)
        * and make an infinite loop to get the notification until application is stopped
        * and call notification alternatively for get the one minute diffrence between them i sleep the thread for one minute
        *
        *
        * */


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                    try {
                        mNotificationManager.notify(NOTIFICATION_FIRST_ID,mBuilderOne.build());
                        Thread.sleep(60000);
                        mNotificationManager.notify(NOTIFICATION_SECOND_ID,mBuilderTwo.build());
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }


            }
        }).start();

    }

    private void setUpFirstNotification() {

//        Step 1 (in Channel we describe the sound property of a channel by telling the priority of the channel)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            NotificationChannel channelOne = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_FIRST_TITLE, NotificationManager.IMPORTANCE_HIGH);
            channelOne.setDescription(CHANNEL_ONE_DESCRIPTION);
            channelOne.setVibrationPattern(new long[]{0,50,100,50});
            notificationManager.createNotificationChannel(channelOne);
        }

//        Step 2 - Build Notification give it title and message and choose a notification channel

        mBuilderOne = new NotificationCompat.Builder(this, CHANNEL_ONE_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(NOTIFICATION_FIRST_TITLE)
                .setContentText(NOTIFICATION_FIRST_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);


    }

    private void setUpSecondNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            NotificationChannel channelSecond = new NotificationChannel(CHANNEL_TWO_ID, CHANNEL_SECOND_TITLE, NotificationManager.IMPORTANCE_LOW);
            channelSecond.setDescription(CHANNEL_TWO_DESCRIPTION);
            notificationManager.createNotificationChannel(channelSecond);
        }

//        Step 2 - Build Notification give it title and message and choose a notification channel

        mBuilderTwo = new NotificationCompat.Builder(this, CHANNEL_TWO_ID)

                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(NOTIFICATION_SECOND_TITLE)
                .setContentText(NOTIFICATION_SECOND_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

    }
}
