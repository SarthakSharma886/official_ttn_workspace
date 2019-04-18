package com.example.storagepart2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.storagepart2.R;
import com.example.storagepart2.databinding.ActivityShareImageBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShareImage extends AppCompatActivity {
    private final int CAMERA_REQUEST = 201;
    private final int CAMERA_CODE = 101;
    private final String CONTENT_PROVIDER = ".provider";
    private File mSaveDCIMFile, mSaveInternal;
    private boolean imageClicked = false;


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

                        mSaveInternal = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + getPackageName() + "/Saved Images/" + mSaveDCIMFile.getName());

                        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(v.getContext(), getPackageName() + CONTENT_PROVIDER, mSaveDCIMFile));

                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                    }
                }

            }
        });


        activityShareImageBinding.btShareOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageClicked) {

                    Intent shareOutside = new Intent();
                    shareOutside.setAction(Intent.ACTION_SEND);
                    shareOutside.setType("image/*");
                    shareOutside.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(v.getContext(), getPackageName() + ".provider", mSaveDCIMFile));
                    shareOutside.putExtra(Intent.EXTRA_TEXT, "from outside");
                    shareOutside.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareOutside, "Share Outside"));
                } else {
                    Toast.makeText(ShareImage.this, "First Capture Image", Toast.LENGTH_SHORT).show();
                }
            }

        });

        activityShareImageBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageClicked) {
                    Intent shareInternal = new Intent();
                    shareInternal.setAction(Intent.ACTION_SEND);
                    shareInternal.setType("image/*");
                    shareInternal.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(v.getContext(), getPackageName() + ".provider", mSaveInternal));
                    shareInternal.putExtra(Intent.EXTRA_TEXT, "from private");
                    shareInternal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareInternal, "Share Private"));
                } else {
                    Toast.makeText(ShareImage.this, "First Capture Image", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You've granted the permissions!", Toast.LENGTH_SHORT).show();


                } else {
                    Snackbar snackbar = Snackbar.make((ConstraintLayout) findViewById(R.id.layout_sharing), "Camera permission required ", Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.argb(255, 255, 0, 0));
                    snackbar.setDuration(Snackbar.LENGTH_LONG);
                    snackbar.setAction("GIVE PERMISSION", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                FileUtils.copyFile(mSaveDCIMFile, mSaveInternal);
                imageClicked = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
