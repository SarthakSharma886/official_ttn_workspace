package com.example.weeklyproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weeklyproject.R;
import com.example.weeklyproject.fragments.RegisterFragment;
import com.example.weeklyproject.interfaces.IInternetStatus;
import com.example.weeklyproject.interfaces.ILoginRegister;
import com.example.weeklyproject.receivers.BCheckConnectivity;
import com.example.weeklyproject.singletons.DataManager;
import com.example.weeklyproject.singletons.SharedPreferences;

import static com.example.weeklyproject.constants.Constants.PASS;
import static com.example.weeklyproject.constants.Constants.SESSION;
import static com.example.weeklyproject.constants.Constants.USER_ID;

public class RegisterLoginActivity extends AppCompatActivity implements ILoginRegister, IInternetStatus {

    SharedPreferences mSharedPreferences;

    ProgressDialog pdLoading;
    Boolean online = true;

    private BCheckConnectivity mCheckConnectivity = new BCheckConnectivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        pdLoading = new ProgressDialog(this);
        DataManager.getInstance().setILoginRegister(this);
        mSharedPreferences = SharedPreferences.init(this);

        if (!mSharedPreferences.readBoolean(SESSION)) {

            if (!mSharedPreferences.readBoolean("splash")) {
                setContentView(R.layout.splash_screen);

                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        mSharedPreferences.write("splash", true);
                        setLogin();

                    }
                }.start();
            } else {
                setLogin();
            }
        } else {
            startActivity(new Intent(RegisterLoginActivity.this, AfterLoginActivity.class));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mCheckConnectivity.setmIInternetStatus(this);
        registerReceiver(mCheckConnectivity, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

    }

    private void setLogin() {
        setContentView(R.layout.activity_main);


        final EditText etUserName = findViewById(R.id.et_username);
        final EditText etPass = findViewById(R.id.et_pass);
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (online) {
                    if (!TextUtils.isEmpty(etUserName.getText().toString()) && (!TextUtils.isEmpty(etPass.getText().toString()))) {

                        pdLoading.setMessage("Loging in");
                        pdLoading.show();
                        String email = mSharedPreferences.readString(USER_ID);
                        String pass = mSharedPreferences.readString(PASS);
                        if (email.equals(etUserName.getText().toString()) && pass.equals(etPass.getText().toString())) {
                            DataManager.getInstance().checkLogin(email, pass);
                        } else {
                            pdLoading.hide();
                            Toast.makeText(RegisterLoginActivity.this, "Register First", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {
                    Toast.makeText(RegisterLoginActivity.this, "Please turn on your Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.bt_register_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (online) {
                    RegisterFragment registerFragment = new RegisterFragment();
                    registerFragment.show(getSupportFragmentManager(), "Register dialog");
                } else {
                    Toast.makeText(RegisterLoginActivity.this, "Please turn on your Internet", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        mSharedPreferences.write("splash",false);
        finishAffinity();
    }


    @Override
    public void doLogin(Boolean status) {


        if (status) {

            pdLoading.hide();
            mSharedPreferences.write(SESSION, true);
            startActivity(new Intent(RegisterLoginActivity.this, AfterLoginActivity.class));
        } else {
            pdLoading.hide();
            Toast.makeText(this, "invalid details", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void doRegister(String email, String pass, Boolean status) {

        if (status) {
            mSharedPreferences.write(USER_ID, email);
            mSharedPreferences.write(PASS, pass);
            Toast.makeText(RegisterLoginActivity.this, "Details Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "not valid details", Toast.LENGTH_SHORT).show();
        }
    }


    public void sendStatus(String status) {

        if (mSharedPreferences.readBoolean("splash") && !mSharedPreferences.readBoolean(SESSION)) {
            Snackbar snackbar = Snackbar.make((ConstraintLayout) findViewById(R.id.layout_register_login), status, Snackbar.LENGTH_SHORT);
            if (status.equals("Online")) {
                online = true;
                snackbar.getView().setBackgroundColor(Color.argb(195, 0, 255, 0));
            } else if (status.equals("Offline")) {
                online = false;
                snackbar.getView().setBackgroundColor(Color.argb(255, 255, 0, 0));
                snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
            }

            snackbar.show();

        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mCheckConnectivity);
    }

}
