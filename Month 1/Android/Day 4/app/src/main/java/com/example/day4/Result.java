package com.example.day4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.i("Exercise4", "Activity 2: onCreate");

        Intent receiveIntent = getIntent();
        TextView name = findViewById(R.id.name);
        TextView email_id = findViewById(R.id.email_id);
        TextView phone_no = findViewById(R.id.phone_no);
        ImageView imageView = findViewById(R.id.imageView);

        name.setText(receiveIntent.getExtras().get("name").toString());
        email_id.setText(receiveIntent.getExtras().get("email_id").toString());
        phone_no.setText(receiveIntent.getExtras().get("phone_no").toString());
        imageView.setImageBitmap((Bitmap) receiveIntent.getExtras().getParcelable("image"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Exercise4", "Activity 2: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Exercise4", "Activity 2: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Exercise4", "Activity 2: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Exercise4", "Activity 2: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Exercise4", "Activity 2: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Exercise4", "Activity 2: onDestroy");
    }
}