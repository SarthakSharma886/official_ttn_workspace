package com.example.performingbackgroundtasknotificationpart1.Activity.activities;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.performingbackgroundtasknotificationpart1.Activity.broadcast.BCheckConnectivity;
import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelAsync;
import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelService;
import com.example.performingbackgroundtasknotificationpart1.Activity.services.SNetCheck;
import com.example.performingbackgroundtasknotificationpart1.R;


public class MainActivity extends AppCompatActivity implements IDownloadCancelAsync, IDownloadCancelService {

    public static final String PATH = "/data/data/com.example.performingbackgroundtasknotificationpart1/theekh.png";
    public static final String IMAGE_URL = "https://www.gstatic.com/webp/gallery3/1.sm.png";

    private ProgressBar mProgressBar;
    private ImageView mIvDownload;
    private Async mAsync;
    private Context mContext;
    private BCheckConnectivity mCheckConnectivity = new BCheckConnectivity();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        registerReceiver(mCheckConnectivity,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        Button btDownload = findViewById(R.id.bt_download);
        Button btPause = findViewById(R.id.bt_pause);
        Button btService = findViewById(R.id.bt_service);
        mIvDownload = findViewById(R.id.iv_download);
        mProgressBar = findViewById(R.id.progressBar);
        SNetCheck sNetCheck = new SNetCheck();
        sNetCheck.setmIDownloadCancelService(this);

        findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(PATH);
                if(file.exists()) {
                    file.delete();
                    if (mIvDownload != null) {
                        mIvDownload.setImageResource(R.drawable.ic_launcher_background);
                    }
                }
            }
        });


        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(haveNetworkConnection()) {

                    download();
                    mCheckConnectivity.setInterface(MainActivity.this);

                }
                else {
                    Toast.makeText(MainActivity.this, "open wifi or mobile data", Toast.LENGTH_SHORT).show();
                }


            }
        });

        final Intent i = new Intent(this, SNetCheck.class);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
               stopService(i);
            }
        });

        btService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(haveNetworkConnection()) {


                    startService(i);

                }
                else {
                    Toast.makeText(MainActivity.this, "open wifi or mobile data", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }




    @Override
    public void download() {
        mAsync = new Async();
        mAsync.execute(IMAGE_URL);
    }

    @Override
    public void cancel() {
        if(mAsync !=null)
        mAsync.cancel(true);
    }

    @Override
    public void setImageProgress(int progress) {

        if (mProgressBar != null) {

            mProgressBar.setProgress(progress);
            if (progress == 100) {
                if (mIvDownload != null) {
                    mIvDownload.setImageDrawable(Drawable.createFromPath(PATH));
                }

            }

        }

    }


    class Async extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mProgressBar != null)
                mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mProgressBar != null) {

                mProgressBar.setProgress(values[0]);
                if (values[0] == 100) {
                    if (mIvDownload != null) {
                        mIvDownload.setImageDrawable(Drawable.createFromPath(PATH));
                    }

                }

            }

        }

        @Override
        protected Void doInBackground(String... strings) {


            try {

                File file = new File(PATH);

                URL url = new URL(strings[0]);


                HttpURLConnection urlConnection;
//                urlConnection.
                urlConnection = (HttpURLConnection) url.openConnection();

                long fullSizeImage = urlConnection.getContentLength();
                long downloadImage = 0;
                if (file.length() != fullSizeImage) {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestProperty("Range", "bytes=" + file.length() + "-");
                    downloadImage = file.length();
                } else if (file.length() == fullSizeImage) {

                    publishProgress(100);
                    return null;
                }
                urlConnection.connect();

                int count;
                byte[] buffer = new byte[1];


                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(PATH, true);

                while ((count = inputStream.read(buffer)) != -1) {
                    if (isCancelled()) {
//                        restartAsync();
                        break;

                    }
                    downloadImage += count;
                    int progress = (int) ((downloadImage * 100 / fullSizeImage));
                    publishProgress(progress);
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


            return null;
        }

    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }





}


