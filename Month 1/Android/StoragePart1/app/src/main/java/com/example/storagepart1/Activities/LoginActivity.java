package com.example.storagepart1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storagepart1.R;
import com.example.storagepart1.sharedpreferences.SharedPreferencesSingleton;

import static com.example.storagepart1.Constants.Constants.PASS;
import static com.example.storagepart1.Constants.Constants.USER_ID;

public class LoginActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etUserName = findViewById(R.id.et_username);
        final EditText etPass = findViewById(R.id.et_pass);
        Button btLogin = findViewById(R.id.bt_login);
        Button btClear = findViewById(R.id.bt_clear);
        final SharedPreferencesSingleton sharedPreferences = SharedPreferencesSingleton.init(getApplicationContext());

        if(sharedPreferences.contains(USER_ID)){
            etUserName.setText(sharedPreferences.readString(USER_ID));
            etPass.setText(sharedPreferences.readString(PASS));
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!TextUtils.isEmpty(etUserName.getText().toString())&&(!TextUtils.isEmpty(etPass.getText().toString()))){
                   sharedPreferences.write(USER_ID, etUserName.getText().toString());
                   sharedPreferences.write(PASS, etPass.getText().toString());
                   Toast.makeText(LoginActivity.this, "Details Saved", Toast.LENGTH_SHORT).show();
               }
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sharedPreferences.clear();
                etPass.setText("");
                etUserName.setText("");
                Toast.makeText(LoginActivity.this, "Details Cleared", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
