package com.example.storagepart2.activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.example.storagepart2.R;
import com.example.storagepart2.adapters.FileAdapter;
import com.example.storagepart2.interfaces.IListDataExchange;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IListDataExchange {

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

        Intent intent = new Intent(MainActivity.this,ShareImage.class);
        startActivity(intent);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        if(!mCurrentDirectory.getAbsolutePath().equals(mRootDirectory.getAbsolutePath()) ){
            mFileAdapter.setmFileArrayList(Arrays.asList(mCurrentDirectory.getParentFile().listFiles()));
            mCurrentDirectory = mCurrentDirectory.getParentFile();
            mFileAdapter.notifyDataSetChanged();
        }
        else {
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


}
