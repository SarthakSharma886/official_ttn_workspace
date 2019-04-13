package com.example.weeklyproject.singletons;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.weeklyproject.POJO.AddUpdateUser;
import com.example.weeklyproject.POJO.LoginRegisterModel;
import com.example.weeklyproject.POJO.ResponseToken;
import com.example.weeklyproject.POJO.UserList;
import com.example.weeklyproject.interfaces.ApiInterface;
import com.example.weeklyproject.interfaces.FailureAPICallback;
import com.example.weeklyproject.interfaces.IDataManageActivity;
import com.example.weeklyproject.interfaces.ILoginRegister;
import com.example.weeklyproject.interfaces.SuccessAPICallback;
import com.example.weeklyproject.retrofit.ResponseHandler;
import com.example.weeklyproject.retrofit.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weeklyproject.constants.Constants.RETRO_TOKEN;


public class DataManager {

    private static final DataManager ourInstance = new DataManager();
    IDataManageActivity mIDataManageActivity = null;
    ILoginRegister mILoginRegister = null;
    ApiInterface mApiInterface = RetroInstance.getInstance().create(ApiInterface.class);

    private DataManager() {
    }

    public static DataManager getInstance() {
        return ourInstance;
    }

    public void checkLogin(String emailId, String pass) {

        LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setEmail(emailId);
        loginRegisterModel.setPassword(pass);


        Call<ResponseToken> data = mApiInterface.loginUser(loginRegisterModel);


        data.enqueue(new ResponseHandler<ResponseToken>(
                new SuccessAPICallback<ResponseToken>() {
                    @Override
                    public void onResponse(ResponseToken responseToken) {
                        if (responseToken.getToken().equals(RETRO_TOKEN)) {

                            if (mILoginRegister != null) {
                                mILoginRegister.doLogin(true);
                            }

                        }

                        Log.v("sarthak", responseToken.getToken());
                    }
                }, new FailureAPICallback() {
            @Override
            public void onFailure(Object errorCode, Object errorMessage) {
                if (mILoginRegister != null) {
                    mILoginRegister.doLogin(false);
                }
                Log.v("sarthak", errorMessage.toString());
            }
        }));

    }

    public void doRegister(final String emailId, final String pass) {

        LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setEmail(emailId);
        loginRegisterModel.setPassword(pass);


        Call<ResponseToken> data = mApiInterface.registerUser(loginRegisterModel);


        data.enqueue(new ResponseHandler<ResponseToken>(
                new SuccessAPICallback<ResponseToken>() {
                    @Override
                    public void onResponse(ResponseToken responseToken) {
                        if (responseToken.getToken().equals(RETRO_TOKEN)) {

                            if (mILoginRegister != null) {
                                mILoginRegister.doRegister(emailId, pass, true);
                            }

                        }
                        Log.v("sarthak", responseToken.getToken());
                    }
                }, new FailureAPICallback() {
            @Override
            public void onFailure(Object errorCode, Object errorMessage) {
                if (mILoginRegister != null) {
                    mILoginRegister.doRegister(emailId, pass, false);
                }
                Log.v("sarthak", errorMessage.toString());
            }
        }));

    }

    public void addUser(final Context context) {

        AddUpdateUser sendAddUser = new AddUpdateUser();
        sendAddUser.setName("sarthak");
        sendAddUser.setJob("developer");


        Call<AddUpdateUser> data = mApiInterface.addUser(sendAddUser);
        data.enqueue(new ResponseHandler<AddUpdateUser>(new SuccessAPICallback<AddUpdateUser>() {
            @Override
            public void onResponse(AddUpdateUser addUpdateUser) {

                Toast.makeText(context.getApplicationContext(), addUpdateUser.getName() + "     " + addUpdateUser.getJob() + "     " + addUpdateUser.getId() + "     " + addUpdateUser.getCreatedAt(), Toast.LENGTH_SHORT).show();

            }
        }, new FailureAPICallback() {
            @Override
            public void onFailure(Object errorCode, Object errorMessage) {

            }
        }));


    }

    public void editUser(final Context context) {
        AddUpdateUser sendUpdateUser = new AddUpdateUser();
        sendUpdateUser.setName("sharma");
        sendUpdateUser.setJob("developer");

        Call<AddUpdateUser> data = mApiInterface.updateUser(sendUpdateUser);

        data.enqueue(new ResponseHandler<AddUpdateUser>(new SuccessAPICallback<AddUpdateUser>() {
            @Override
            public void onResponse(AddUpdateUser addUpdateUser) {

                Toast.makeText(context.getApplicationContext(), addUpdateUser.getName() + "          " + addUpdateUser.getJob() + "          " + addUpdateUser.getUpdatedAt(), Toast.LENGTH_SHORT).show();

            }
        }, new FailureAPICallback() {
            @Override
            public void onFailure(Object errorCode, Object errorMessage) {


            }
        }));


    }

    public void deleteUser(final Context context) {

        Call<Void> data = mApiInterface.deleteUser();
        data.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context.getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void fetchUsers() {

    }

    public void fetchPage(int page) {

        Call<UserList> data = mApiInterface.getUserList(page);

        data.enqueue(new ResponseHandler<UserList>(new SuccessAPICallback<UserList>() {
            @Override
            public void onResponse(UserList userList) {

//                        mFetchUserDataArrayList = userList.getData();

                if (mIDataManageActivity != null)
                    if (!userList.getData().isEmpty()) {
                        Log.v("sarthak retro pass", userList.getData().toString());
                        mIDataManageActivity.retroResponse(userList, true);
                    } else {
                        Log.v("sarthak retro fail data", userList.getData().toString());
                        mIDataManageActivity.retroResponse(null, false);
                    }

            }
        }, new FailureAPICallback() {
            @Override
            public void onFailure(Object errorCode, Object errorMessage) {

                Log.v("sarthak retro fail", errorMessage.toString());
            }
        }));


    }

    public void setActivity(IDataManageActivity iDataManageActivity) {
        mIDataManageActivity = iDataManageActivity;
    }

    public void setILoginRegister(ILoginRegister iLoginRegister) {
        mILoginRegister = iLoginRegister;
    }

}
