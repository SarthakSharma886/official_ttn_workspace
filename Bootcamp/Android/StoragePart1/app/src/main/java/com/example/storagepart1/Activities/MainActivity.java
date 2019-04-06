package com.example.storagepart1.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.storagepart1.Adapters.EmployeeDataAddRecyclerAdapter;
import com.example.storagepart1.Database.DataBaseHelper;
import com.example.storagepart1.Fragments.CustomDialog;
import com.example.storagepart1.Interfaces.IAdapterActivityCommunicator;
import com.example.storagepart1.Interfaces.IDialogFragmentCommunicator;
import com.example.storagepart1.POJO.DataModel;
import com.example.storagepart1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.storagepart1.Constants.Constants.DATAMODEL_SERIALIZABLE_KEY;
import static com.example.storagepart1.Constants.Constants.MODEL_ADD;
import static com.example.storagepart1.Constants.Constants.MODEL_EDIT;
import static com.example.storagepart1.Constants.Constants.POSITION_KEY;


public class MainActivity extends AppCompatActivity implements IAdapterActivityCommunicator, IDialogFragmentCommunicator {

    private FloatingActionButton memployeeDataAddDialogFab;
    private RecyclerView mrecyclerView;
    private ArrayList<DataModel> mmodels;
    private EmployeeDataAddRecyclerAdapter memployeeDataAddRecyclerAdapter;
    private CustomDialog mEmployeeDetailsDialog;
    private DataBaseHelper mdataBaseHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mmodels = new ArrayList<>();
        memployeeDataAddDialogFab = findViewById(R.id.fab_Employee_Data_Add);

        memployeeDataAddDialogFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmployeeDetailsDialog = new CustomDialog();
                mEmployeeDetailsDialog.setInterface(MainActivity.this);
                mEmployeeDetailsDialog.show(getSupportFragmentManager(), "Dialog Box");
            }
        });


        mrecyclerView = findViewById(R.id.recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        memployeeDataAddRecyclerAdapter = new EmployeeDataAddRecyclerAdapter();
        memployeeDataAddRecyclerAdapter.setiAdapterCommunicator(this);
        mrecyclerView.setAdapter(memployeeDataAddRecyclerAdapter);
        memployeeDataAddRecyclerAdapter.setModels(mmodels);
        mdataBaseHelper = new DataBaseHelper(MainActivity.this);
        mDatabase = mdataBaseHelper.getWritableDatabase();
        LoadingPreExistingDataFromDB(mDatabase);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DATAMODEL_SERIALIZABLE_KEY, mmodels);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mmodels = (ArrayList<DataModel>) savedInstanceState.getSerializable(DATAMODEL_SERIALIZABLE_KEY);
//            memployeeDataAddRecyclerAdapter.setModels(mNewmodels);
            memployeeDataAddRecyclerAdapter.setModels(mmodels);
            memployeeDataAddRecyclerAdapter.setiAdapterCommunicator(this);
            memployeeDataAddRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteData(int id, int position) {
        mmodels.remove(position);
        mdataBaseHelper.deleteModel(mDatabase, id);
        memployeeDataAddRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void editData(DataModel dataModel, int position) {

        Bundle bundleToDialog = new Bundle();
        bundleToDialog.putSerializable(DATAMODEL_SERIALIZABLE_KEY, dataModel);
        bundleToDialog.putInt(POSITION_KEY, position);
        mEmployeeDetailsDialog = new CustomDialog();
        mEmployeeDetailsDialog.setInterface(this);
        mEmployeeDetailsDialog.setArguments(bundleToDialog);
        mEmployeeDetailsDialog.show(getSupportFragmentManager(), "Dialog Box");


    }

    @Override
    public void DisplayDialogDataDatabase(DataModel model, int position) {


        if (model.getActionToBePerformed() == MODEL_ADD) {

            model.setUniqueId(mdataBaseHelper.insertModel(mDatabase, model));
            Toast.makeText(this, model.getUniqueId() + "", Toast.LENGTH_SHORT).show();
            mmodels.add(model);

        } else if (model.getActionToBePerformed() == MODEL_EDIT) {

            mdataBaseHelper.updateModel(mDatabase, model);
            mmodels.set(position, model);
        }
        memployeeDataAddRecyclerAdapter.notifyDataSetChanged();
    }


    private void LoadingPreExistingDataFromDB(SQLiteDatabase database) {
        Cursor cursor = mdataBaseHelper.fetchAllModel(database);
        if (cursor.getCount() != 0) {
            DataModel dataModel = null;
            while (!cursor.isLast()) {
                dataModel = new DataModel();
                Log.v("sarthak", cursor.getInt(0)+cursor.getString(1)+cursor.getInt(2)+cursor.getString(3));
                dataModel.setUniqueId(cursor.getInt(0));
                dataModel.setName(cursor.getString(1));
                dataModel.setMobileNumber(cursor.getInt(2));
                dataModel.setAddress(cursor.getString(3));
                mmodels.add(dataModel);
                cursor.moveToNext();
            }
            dataModel = new DataModel();
            dataModel.setUniqueId(cursor.getInt(0));
            dataModel.setName(cursor.getString(1));
            dataModel.setMobileNumber(cursor.getInt(2));
            dataModel.setAddress(cursor.getString(3));
            mmodels.add(dataModel);
        }
        memployeeDataAddRecyclerAdapter.notifyDataSetChanged();
        cursor.close();
    }
}