package com.example.weeklyproject.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weeklyproject.BuildConfig;
import com.example.weeklyproject.POJO.FetchUserData;
import com.example.weeklyproject.POJO.UserList;
import com.example.weeklyproject.R;
import com.example.weeklyproject.adapters.RetroAdapter;
import com.example.weeklyproject.fragments.UserDetails;
import com.example.weeklyproject.interfaces.IActivityAdapterComm;
import com.example.weeklyproject.interfaces.IDataManageActivity;
import com.example.weeklyproject.interfaces.IInternetStatus;
import com.example.weeklyproject.receivers.BCheckConnectivity;
import com.example.weeklyproject.singletons.DataManager;
import com.example.weeklyproject.singletons.SharedPreferences;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.weeklyproject.constants.Constants.SESSION;

public class AfterLoginActivity extends AppCompatActivity implements IDataManageActivity, IInternetStatus, IActivityAdapterComm {

    private SharedPreferences mSharedPreferences;
    private ArrayList<FetchUserData> mFetchUserDataArrayList = new ArrayList<>();
    private RetroAdapter mRetroAdapter = new RetroAdapter();
    private boolean isScrolling = false;
    private int pageCounter = 1;
    private BCheckConnectivity mCheckConnectivity = new BCheckConnectivity();
    private UserDetails mUserDetails = null;
    private boolean haveMorePage = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        DataManager.getInstance().setActivity(this);


        mSharedPreferences = SharedPreferences.init(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.rv_userdata);
        mRetroAdapter.setAdapterActivity(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mRetroAdapter);

        registerForContextMenu(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItems = linearLayoutManager.getItemCount();
                int visibleItems = linearLayoutManager.getChildCount();
                int scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && visibleItems + scrolledOutItems == totalItems) {

                    isScrolling = false;
                    if (haveMorePage) {
                        pageCounter++;
                        DataManager.getInstance().fetchPage(pageCounter);
                        Toast.makeText(AfterLoginActivity.this, "Loading ...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mCheckConnectivity.setmIInternetStatus(this);
        registerReceiver(mCheckConnectivity, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mCheckConnectivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_add_user:
                DataManager.getInstance().addUser(this);
                break;


            case R.id.menu_logout:
                mSharedPreferences.write(SESSION, false);
                startActivity(new Intent(AfterLoginActivity.this, RegisterLoginActivity.class));
                Toast.makeText(AfterLoginActivity.this, "logout", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        if (mUserDetails != null) {
            mUserDetails = null;
        } else {
            finishAffinity();
        }
    }

    @Override
    public void retroResponse(UserList userList, Boolean status) {

        if (userList != null) {
            mFetchUserDataArrayList.addAll(userList.getData());
            mRetroAdapter.setRetroAdapter(mFetchUserDataArrayList);
            mRetroAdapter.notifyDataSetChanged();
        }
        haveMorePage = status;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case 120:
                DataManager.getInstance().deleteUser(this);
                break;

            case 121:
                DataManager.getInstance().editUser(this);
                break;


        }


        return true;
    }


    @Override
    public void showFragment(FetchUserData dataModel) {
        mUserDetails = new UserDetails();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATAMODEL", dataModel);
        mUserDetails.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_afterlogin, mUserDetails).commit();
        fragmentTransaction.addToBackStack("user details");

    }

    @Override
    public void shareImage(File image,String name) {
        File mediaStorageFile = new File(getFilesDir(), "share_profile.jpg");
        try {
            FileUtils.copyFile(image, mediaStorageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent sendingIntent = new Intent(Intent.ACTION_SEND);
        sendingIntent.setType("image/*");
        sendingIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mediaStorageFile));
        sendingIntent.putExtra(Intent.EXTRA_TEXT,name+" image ");
        sendingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(sendingIntent, "share Using"));
    }

    private void fetchStartPages() {
        pageCounter = 1;
        DataManager.getInstance().fetchPage(pageCounter);
        pageCounter++;
        DataManager.getInstance().fetchPage(pageCounter);
    }


    public void sendStatus(String status) {


        Snackbar snackbar = Snackbar.make(findViewById(R.id.frame_afterlogin), status, Snackbar.LENGTH_SHORT);
        if (status.equals("Online")) {
            haveMorePage = true;
            if (mFetchUserDataArrayList.isEmpty()) {
                fetchStartPages();
            }
            snackbar.getView().setBackgroundColor(Color.argb(195, 0, 255, 0));
        } else if (status.equals("Offline")) {
            haveMorePage = false;
            snackbar.getView().setBackgroundColor(Color.argb(255, 255, 0, 0));
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }

        snackbar.show();


    }

}
