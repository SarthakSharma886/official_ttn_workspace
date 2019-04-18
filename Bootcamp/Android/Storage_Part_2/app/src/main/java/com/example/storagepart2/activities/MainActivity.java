package com.example.storagepart2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.storagepart2.R;
import com.example.storagepart2.adapters.FileAdapter;
import com.example.storagepart2.interfaces.IListDataExchange;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IListDataExchange {

    private final int PERMISSIONS_READ_WRITE_EXTERNAL = 1;
    private List<File> mFileArray;
    private RecyclerView mRecyclerView;
    private FileAdapter mFileAdapter = new FileAdapter();
    private File mRootDirectory, mCurrentDirectory;
    private boolean mPermisions = false;
    private Snackbar mSnackbar = null;


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


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onstart", "called");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            askingPermissions();



        } else {
            mPermisions = true;
            setupFileExplorer();


        }
    }

    private void setupFileExplorer() {

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


    @Override
    public void onBackPressed() {
        if (mPermisions && !mCurrentDirectory.getAbsolutePath().equals(mRootDirectory.getAbsolutePath())) {
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_READ_WRITE_EXTERNAL);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_READ_WRITE_EXTERNAL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You've granted the permissions!", Toast.LENGTH_SHORT).show();
                    mPermisions = true;
                    setupFileExplorer();
                    if (mSnackbar != null) {
                        mSnackbar.dismiss();
                    }

                }
                else{
                    mSnackbar = Snackbar.make((ConstraintLayout) findViewById(R.id.layout_explorer), "Storage Permission is Mandatory ", Snackbar.LENGTH_SHORT);
                    mSnackbar.getView().setBackgroundColor(Color.argb(255, 255, 0, 0));
                    mSnackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                    mSnackbar.setAction("GIVE PERMISSION", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("toastcheck", "helloboi");
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                    mSnackbar.setActionTextColor(Color.WHITE);
                    mSnackbar.show();
                }
                break;
        }
    }


}
