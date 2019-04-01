package com.example.storagepart2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.storagepart2.R;
import com.example.storagepart2.databinding.ActivityShareImageBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShareImage extends AppCompatActivity {
    private final int CAMERA_REQUEST = 201;
    private File mSaveDCIMFile, mSaveInternal;
    private final int CAMERA_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShareImageBinding activityShareImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_share_image);

        activityShareImageBinding.btCameraclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
                    } else {
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        mSaveDCIMFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera"
                                + File.separator
                                + "sarthak"
                                + new SimpleDateFormat("yyMMdd_HHmmss")
                                .format(new Date()) + ".jpg");

                        mSaveInternal = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + getPackageName() +"/Saved Images/"+ mSaveDCIMFile.getName());

                        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(v.getContext(), getPackageName() + ".provider", mSaveDCIMFile));

                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                    }
                }

            }
        });



        activityShareImageBinding.btShareOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareOutside = new Intent();
                shareOutside.setAction(Intent.ACTION_SEND);
                shareOutside.setType("image/*");
                shareOutside.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(v.getContext(), getPackageName() + ".provider", mSaveDCIMFile));
                shareOutside.putExtra(Intent.EXTRA_TEXT,"from outside");
                shareOutside.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareOutside,"Share Outside"));
            }
        });

        activityShareImageBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareInternal = new Intent();
                shareInternal.setAction(Intent.ACTION_SEND);
                shareInternal.setType("image/*");
                shareInternal.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(v.getContext(), getPackageName() + ".provider", mSaveInternal));
                shareInternal.putExtra(Intent.EXTRA_TEXT,"from private");
                shareInternal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareInternal,"Share Private"));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                FileUtils.copyFile(mSaveDCIMFile, mSaveInternal);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
