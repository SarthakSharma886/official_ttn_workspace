package com.example.sociallogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ShowDetailsActivity extends AppCompatActivity {
    ImageView mIvProfile;
    TextView mTvName;
    TextView mTvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        mTvEmail = findViewById(R.id.tv_email);
        mTvName = findViewById(R.id.tv_name);
        mIvProfile = findViewById(R.id.iv_profile);


        Intent intent = getIntent();
        int status = intent.getIntExtra("status", 0);
        if (status == 1) {
            GoogleSignInAccount account = intent.getParcelableExtra("google_account");
            showData(account.getDisplayName(), account.getEmail(), account.getPhotoUrl().toString());

        } else if (status == 2) {
            showData(intent.getStringExtra("Name"), intent.getStringExtra("Email"), intent.getStringExtra("Profile"));

        } else {
            Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show();
        }


    }

    void showData(String name, String email, String imgUrl) {

        if (name != "") {
            mTvName.setText(name);
        } else {
            Toast.makeText(this, "No User Name", Toast.LENGTH_SHORT).show();
        }


        if (email != "") {
            mTvEmail.setText(email);
        } else {
            Toast.makeText(this, "No Email", Toast.LENGTH_SHORT).show();
        }


        if (imgUrl != "") {

            Glide.with(this)
                    .load(imgUrl)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(mIvProfile);
        } else {
            Toast.makeText(this, "No Profile Pic", Toast.LENGTH_SHORT).show();
        }


    }
}
