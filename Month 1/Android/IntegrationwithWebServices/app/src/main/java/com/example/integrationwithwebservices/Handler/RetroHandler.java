package com.example.integrationwithwebservices.Handler;

import android.content.Context;
import android.widget.Toast;

import com.example.integrationwithwebservices.Interfaces.FailureAPICallback;
import com.example.integrationwithwebservices.Interfaces.SuccessAPICallback;
import com.example.integrationwithwebservices.POJO.RetroModel;

import retrofit2.Call;
import retrofit2.Response;

public class RetroHandler<Post> implements retrofit2.Callback<RetroModel> {

    private SuccessAPICallback mSuccessAPICallback = null;
    private FailureAPICallback mFailureAPICallback = null;
    private Context mContext;

    public RetroHandler( Context context , SuccessAPICallback successAPICallback , FailureAPICallback failureAPICallback ){
       mFailureAPICallback = failureAPICallback;
       mSuccessAPICallback = successAPICallback;
       mContext = context;
    }


    @Override
    public void onResponse(Call call, Response response) {

        if(response.isSuccessful() && mSuccessAPICallback!=null){
            mSuccessAPICallback.onResponse(response.body());
        }
        else {
            mFailureAPICallback.onFaliure(response.body(),response.errorBody());
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Toast.makeText(mContext, t.toString(), Toast.LENGTH_SHORT).show();
    }
}
