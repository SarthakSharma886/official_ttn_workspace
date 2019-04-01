package com.example.jetpack2.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jetpack2.R;
import com.example.jetpack2.adapter.UserAdapter;
import com.example.jetpack2.databinding.ActivityMainBinding;
import com.example.jetpack2.fragments.AddUserDialog;
import com.example.jetpack2.interfaces.IAddUser;
import com.example.jetpack2.pojo.UserData;
import com.example.jetpack2.viewmodels.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IAddUser {

    UserViewModel mUserViewModel;
    UserAdapter mUserAdapter = new UserAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.setHasFixedSize(true);
        activityMainBinding.recyclerView.setAdapter(mUserAdapter);

        activityMainBinding.fabUserDataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserDialog addUserDialog = new AddUserDialog();
                addUserDialog.setActivity(MainActivity.this);
                addUserDialog.show(getSupportFragmentManager(), "Dialog Box");
            }
        });


        mUserViewModel.fetchAll().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(@Nullable List<UserData> userData) {
                mUserAdapter.submitList(userData);
                Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                mUserViewModel.delete(mUserAdapter.getUser(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(activityMainBinding.recyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mUserViewModel.deleteAll();

        return true;
    }

    @Override
    public void addUser(UserData userData) {

        mUserViewModel.insert(userData);

    }
}
