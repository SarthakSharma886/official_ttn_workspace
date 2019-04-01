package com.example.performingbackgroundtasknotificationpart1.Activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelAsync;
import com.example.performingbackgroundtasknotificationpart1.Activity.services.SNetCheck;

public class BCheckConnectivity extends BroadcastReceiver {
    private ConnectivityManager connectivityManager;
    private IDownloadCancelAsync mIDownloadCancelAsync = null;
    private Boolean serviceStatus = false;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Intent i = new Intent(context, SNetCheck.class);
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && mIDownloadCancelAsync != null) {
            mIDownloadCancelAsync.download();
            if(serviceStatus){

                context.startService(i);
            }
        } else if (mIDownloadCancelAsync != null) {
            Toast.makeText(context, "Connectivity lost", Toast.LENGTH_SHORT).show();
            mIDownloadCancelAsync.cancel();
            context.stopService(i);
        }
    }

    public void setServiceStatus(Boolean status ){
        serviceStatus = status;
    }

    public void setInterface(IDownloadCancelAsync iDownloadCancelAsync) {
        mIDownloadCancelAsync = iDownloadCancelAsync;
    }
}
