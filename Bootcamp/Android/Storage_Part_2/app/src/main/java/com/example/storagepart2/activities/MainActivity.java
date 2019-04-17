package com.example.storagepart2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.storagepart2.R;
import com.example.storagepart2.adapters.FileAdapter;
import com.example.storagepart2.interfaces.IListDataExchange;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IListDataExchange {

    private final int PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE_CAMERA = 1;
    private List<File> mFileArray;
    private RecyclerView mRecyclerView;
    private FileAdapter mFileAdapter = new FileAdapter();
    private File mRootDirectory, mCurrentDirectory;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(MainActivity.this, ShareImage.class);
        startActivity(intent);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED )
                {
            askingPermissions();

        }
        else{
            mRootDirectory = Environment.getExternalStorageDirectory();
            mCurrentDirectory = mRootDirectory;

            mFileAdapter.setmIListDataExchange(this);

            mFileArray = new ArrayList<>();
            mFileArray = Arrays.asList(mRootDirectory.listFiles());

            mRecyclerView = findViewById(R.id.rv_file);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mFileAdapter.setmFileArrayList(mFileArray);
            mRecyclerView.setAdapter(mFileAdapter);
        }

        }






    @Override
    public void onBackPressed() {
        if (!mCurrentDirectory.getAbsolutePath().equals(mRootDirectory.getAbsolutePath())) {
            mFileAdapter.setmFileArrayList(Arrays.asList(mCurrentDirectory.getParentFile().listFiles()));
            mCurrentDirectory = mCurrentDirectory.getParentFile();
            mFileAdapter.notifyDataSetChanged();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void listExchange(File currentFile) {

        mCurrentDirectory = currentFile;
        mFileArray = Arrays.asList(currentFile.listFiles());
        mFileAdapter.setmFileArrayList(mFileArray);
        mFileAdapter.notifyDataSetChanged();


    }

    private void askingPermissions() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE_CAMERA);
            }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You've granted the permissions!", Toast.LENGTH_SHORT).show();
                    mRootDirectory = Environment.getExternalStorageDirectory();
                    mCurrentDirectory = mRootDirectory;

                    mFileAdapter.setmIListDataExchange(this);

                    mFileArray = new ArrayList<>();
                    mFileArray = Arrays.asList(mRootDirectory.listFiles());

                    mRecyclerView = findViewById(R.id.rv_file);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    mFileAdapter.setmFileArrayList(mFileArray);
                    mRecyclerView.setAdapter(mFileAdapter);
                } else {
                    askingPermissions();
                }
                break;
        }
    }


}
