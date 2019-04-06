package com.example.weeklyproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weeklyproject.POJO.LoginRegisterModel;
import com.example.weeklyproject.R;
import com.example.weeklyproject.fragments.RegisterFragment;
import com.example.weeklyproject.interfaces.ILoginRegisterCheck;
import com.example.weeklyproject.singletons.DataManager;
import com.example.weeklyproject.singletons.SharedPreferences;

import static com.example.weeklyproject.constants.Constants.PASS;
import static com.example.weeklyproject.constants.Constants.USER_ID;

public class RegisterLoginActivity extends AppCompatActivity implements ILoginRegisterCheck {

    SharedPreferences mSharedPreferences;

    //    ProgressBar pbLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        pbLoading = findViewById(R.id.pb_loading);

        mSharedPreferences = SharedPreferences.init(getApplicationContext());

        if (!mSharedPreferences.contains(USER_ID)) {

            if (!mSharedPreferences.contains("splash")) {
                setContentView(R.layout.splash_screen);
                mSharedPreferences.write("splash", true);
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {

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

    private void setLogin() {
        setContentView(R.layout.activity_main);
        final EditText etUserName = findViewById(R.id.et_username);
        final EditText etPass = findViewById(R.id.et_pass);
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etUserName.getText().toString()) && (!TextUtils.isEmpty(etPass.getText().toString()))) {
                    findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);


                    DataManager.getInstance().checkLogin(etUserName.getText().toString(),etPass.getText().toString());

                    mSharedPreferences.write(USER_ID, etUserName.getText().toString());
                    mSharedPreferences.write(PASS, etPass.getText().toString());
                    Toast.makeText(RegisterLoginActivity.this, "Details Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterLoginActivity.this, AfterLoginActivity.class));
                }
            }
        });

        findViewById(R.id.bt_register_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterFragment registerFragment = new RegisterFragment();
                registerFragment.show(getSupportFragmentManager(), "Register dialog");
            }
        });

    }

    @Override
    public void onBackPressed() {
        mSharedPreferences.clear("splash");
        finishAffinity();
    }

    @Override
    public void receivedToken(String token) {

    }
}
