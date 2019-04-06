package com.example.day4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 201;
    private Bitmap bitmap;
    private static final int CAMERA_CODE = 101;
    private static final int VIEW_CODE = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Exercise4", "Activity 1: onCreate");

        Button captureImage = findViewById(R.id.capture_image);
        captureImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
                } else {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, CAMERA_REQUEST);
                }
            }
        });

        final Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);
                EditText email_id = findViewById(R.id.email_id);
                EditText phone_no = findViewById(R.id.phone_no);

                Intent submitIntent = new Intent(MainActivity.this,Result.class);
                submitIntent.putExtra("name", name.getText().toString());
                submitIntent.putExtra("email_id", email_id.getText().toString());
                submitIntent.putExtra("phone_no", phone_no.getText().toString());
                submitIntent.putExtra("image", bitmap);


                if((!TextUtils.isEmpty(email_id.getText()) && Patterns.EMAIL_ADDRESS.matcher(email_id.getText()).matches())){
                    name.setText("");
                    email_id.setText("");
                    phone_no.setText("");
                    startActivityForResult(submitIntent, VIEW_CODE);
                }
                else{
                    Toast.makeText(MainActivity.this, "invalid E-mail", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Camera Permission Granted!", Toast.LENGTH_SHORT).show();
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_REQUEST);
            }
            else {
                Toast.makeText(MainActivity.this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Exercise4", "Activity 1: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Exercise4", "Activity 1: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Exercise4", "Activity 1: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Exercise4", "Activity 1: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Exercise4", "Activity 1: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Exercise4", "Activity 1: onDestroy");
    }
}
