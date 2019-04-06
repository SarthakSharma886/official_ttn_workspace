package com.example.weeklyproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.weeklyproject.interfaces.IInternetStatus;


public class BCheckConnectivity extends BroadcastReceiver {
    private ConnectivityManager connectivityManager;
    private IInternetStatus mIInternetStatus = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            if (mIInternetStatus != null) {
                mIInternetStatus.sendStatus("Online");
            }

            Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show();

        } else {
            if (mIInternetStatus != null) {
                mIInternetStatus.sendStatus("Offline");
            }
            Toast.makeText(context, "Network disconnected", Toast.LENGTH_SHORT).show();


        }
    }

    public void setmIInternetStatus(IInternetStatus iInternetStatus) {
        mIInternetStatus = iInternetStatus;
    }

}
