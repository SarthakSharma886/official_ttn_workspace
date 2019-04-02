package com.example.performingbackgroundtasknotificationpart1.Activity.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.performingbackgroundtasknotificationpart1.Activity.activities.MainActivity.IMAGE_URL;
import static com.example.performingbackgroundtasknotificationpart1.Activity.activities.MainActivity.PATH;

public class SNetCheck extends IntentService {


    IDownloadCancelService mIDownloadCancelService = null;


    public SNetCheck() {
        super("Net check service");
    }

    public static volatile Boolean stop = false;

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent intentSetProgress = new Intent("downloadProgress");

        try {


            File file = new File(PATH);

            URL url = new URL(IMAGE_URL);


            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection) url.openConnection();

            long fullSizeImage = urlConnection.getContentLength();
            long downloadImage = 0;
            if (file.length() != fullSizeImage) {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Range", "bytes=" + file.length() + "-");
                downloadImage = file.length();
            } else if (file.length() == fullSizeImage) {

                intentSetProgress.putExtra("progress", 100);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);

            }
            urlConnection.connect();

            int count;
            byte[] buffer = new byte[1];


            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(PATH, true);

            while ((count = inputStream.read(buffer)) != -1) {

                if (stop) {
                    stopSelf();
                    break;

                }
                downloadImage += count;
                int progress = (int) ((downloadImage * 100 / fullSizeImage));
                intentSetProgress.putExtra("progress", progress);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);

                fileOutputStream.write(buffer, 0, count);

            }

            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            urlConnection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    @Override
//    public ComponentName startService(Intent service) {
//        stop = false;
//        return super.startService(service);
//    }

//    @Override
//    public boolean stopService(Intent name) {
//
//        stop = true;
//
//        return true;
//
//    }


}
