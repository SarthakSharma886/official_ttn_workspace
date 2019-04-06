package com.example.weeklyproject.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weeklyproject.POJO.FetchUserData;
import com.example.weeklyproject.POJO.UserList;
import com.example.weeklyproject.R;
import com.example.weeklyproject.adapters.RetroAdapter;
import com.example.weeklyproject.interfaces.IDataManageActivity;
import com.example.weeklyproject.interfaces.IInternetStatus;
import com.example.weeklyproject.receivers.BCheckConnectivity;
import com.example.weeklyproject.singletons.DataManager;
import com.example.weeklyproject.singletons.SharedPreferences;

import java.util.ArrayList;

import static com.example.weeklyproject.constants.Constants.USER_ID;

public class AfterLoginActivity extends AppCompatActivity implements IDataManageActivity, IInternetStatus {

    private SharedPreferences mSharedPreferences;
    private ArrayList<FetchUserData> mFetchUserDataArrayList = new ArrayList<>();
    private RetroAdapter mRetroAdapter = new RetroAdapter();
    private boolean isScrolling = false;
    private int pageCounter = 1;
    private BCheckConnectivity mCheckConnectivity = new BCheckConnectivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        DataManager.getInstance().setActivity(this);


        mSharedPreferences = SharedPreferences.init(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.rv_userdata);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mRetroAdapter);

        pageCounter = 1;
        DataManager.getInstance().fetchPage(pageCounter);
        pageCounter++;
        DataManager.getInstance().fetchPage(pageCounter);

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
                    if (pageCounter < 4) {
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

//                AddUpdateDialog addDialog = new AddUpdateDialog();
//                addDialog.show(getSupportFragmentManager(), "Add User");
//                Toast.makeText(this, "add user", Toast.LENGTH_SHORT).show();
//
//                LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
//                loginRegisterModel.setEmail(mSharedPreferences.readString(USER_ID));
//                loginRegisterModel.setPassword("");


                break;


            case R.id.menu_logout:
                mSharedPreferences.clear(USER_ID);
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
        finishAffinity();
    }

    @Override
    public void retroResponse(UserList userList) {

        mFetchUserDataArrayList.addAll(userList.getData());

        mRetroAdapter.setRetroAdapter(mFetchUserDataArrayList);
        mRetroAdapter.notifyDataSetChanged();


    }


    public void sendStatus(String status) {


        Snackbar snackbar = Snackbar.make(findViewById(R.id.constraint_afterlogin), status, Snackbar.LENGTH_SHORT);
        if(status.equals("Online")) {
            snackbar.getView().setBackgroundColor(Color.argb(195, 0, 255, 0));
        }else if(status.equals("Offline")){
            snackbar.getView().setBackgroundColor(Color.argb(255, 255, 0, 0));
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }

        snackbar.show();


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


}
