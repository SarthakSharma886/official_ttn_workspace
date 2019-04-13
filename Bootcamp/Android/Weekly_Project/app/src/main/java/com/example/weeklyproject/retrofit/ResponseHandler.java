package com.example.weeklyproject.retrofit;

import android.util.Log;

import com.example.weeklyproject.POJO.GroupingModel;
import com.example.weeklyproject.interfaces.FailureAPICallback;
import com.example.weeklyproject.interfaces.SuccessAPICallback;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Response;

public class ResponseHandler<T extends GroupingModel> implements retrofit2.Callback<T> {
    private SuccessAPICallback<T> successAPICallback = null;
    private FailureAPICallback failureAPICallback = null;


    public ResponseHandler(SuccessAPICallback<T> successAPICallback, FailureAPICallback failureAPICallback) {
        this.failureAPICallback = failureAPICallback;
        this.successAPICallback = successAPICallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (successAPICallback != null)
                successAPICallback.onResponse(response.body());
        } else {
            if (failureAPICallback != null) {
                failureAPICallback.onFailure(response.body(), response.body());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        String errorMessage = throwable.toString();
        Log.i("onFailure", "error");
        if (throwable instanceof UnknownHostException || throwable instanceof ConnectException) {
            //showNetworkDialog();
        }
    }
}