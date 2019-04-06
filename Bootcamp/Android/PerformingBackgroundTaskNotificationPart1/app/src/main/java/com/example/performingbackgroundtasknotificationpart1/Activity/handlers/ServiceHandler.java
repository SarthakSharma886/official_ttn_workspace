package com.example.performingbackgroundtasknotificationpart1.Activity.handlers;

import android.content.Context;
import android.content.Intent;

import com.example.performingbackgroundtasknotificationpart1.Activity.interfaces.IDownloadCancelService;
import com.example.performingbackgroundtasknotificationpart1.Activity.services.SNetCheck;

public class ServiceHandler {

    private IDownloadCancelService mIDownloadCancelService;
    public Boolean serviceStatus = false;
    Intent i = null;


    private static final ServiceHandler ourInstance = new ServiceHandler();

    public static ServiceHandler getInstance() {
        return ourInstance;
    }

    private ServiceHandler() {
    }

    public void setServiceHandler(IDownloadCancelService iDownloadCancelService){
        mIDownloadCancelService = iDownloadCancelService;
    }


    public void startService(Context context){
        if(mIDownloadCancelService!=null && serviceStatus){
            i = new Intent(context, SNetCheck.class);
            context.startService(i);
        }
    }

    public void stopService(Context context){
        if(i!=null)
            context.stopService(i);
    }

}
