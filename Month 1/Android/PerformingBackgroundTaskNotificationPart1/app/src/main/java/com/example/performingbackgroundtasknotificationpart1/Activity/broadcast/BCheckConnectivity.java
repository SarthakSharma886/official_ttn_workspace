package com.example.performingbackgroundtasknotificationpart1.Activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelAsync;
import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelService;


public class BCheckConnectivity extends BroadcastReceiver {
    private ConnectivityManager connectivityManager;
    private IDownloadCancelAsync mIDownloadCancelAsync = null;
    private IDownloadCancelService mIDownloadCancelService = null;

    public void setMainActivity(IDownloadCancelService iDownloadCancelService) {

        mIDownloadCancelService = iDownloadCancelService;

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show();

            if (mIDownloadCancelAsync != null)
                mIDownloadCancelAsync.download();
            if (mIDownloadCancelService != null) {

                mIDownloadCancelService.startbService();

            }
        } else if (mIDownloadCancelAsync != null) {

            Toast.makeText(context, "Network disconnected", Toast.LENGTH_SHORT).show();
            mIDownloadCancelAsync.cancel();
            if (mIDownloadCancelService != null) {
                mIDownloadCancelService.stopbService();
            }


        }
    }


    public void setInterface(IDownloadCancelAsync iDownloadCancelAsync) {
        mIDownloadCancelAsync = iDownloadCancelAsync;
    }
}
