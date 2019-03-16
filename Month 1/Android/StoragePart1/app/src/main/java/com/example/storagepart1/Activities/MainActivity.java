package com.example.storagepart1.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private ArrayList<DataModel> mmodels = new ArrayList<>();
//    ArrayList<DataModel> mNewmodels = new ArrayList<>();
    private EmployeeDataAddRecyclerAdapter memployeeDataAddRecyclerAdapter;
    private CustomDialog mEmployeeDetailsDialog;
    private DataBaseHelper mdataBaseHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memployeeDataAddDialogFab =findViewById(R.id.fab_Employee_Data_Add);
//        DataModel dataModel = new DataModel();
//        dataModel.setAddress("hjavdy");
//        dataModel.setMobileNumber(4561656);
//        dataModel.setName("hxcvgbndlkjkc");
//        DataModel dataModel1 = new DataModel();
//        dataModel1.setAddress("hjavdysuighugifds");
//        dataModel1.setMobileNumber(458844656);
//        dataModel1.setName("hxcvgbndlkjkcfsufugsifiudfiuds");
//        mmodels.add(dataModel);
//        mmodels.add(dataModel1);
//        mmodels.add(dataModel);
//        mmodels.add(dataModel1);
//        mmodels.add(dataModel);
//        mmodels.add(dataModel1);
//        mmodels.add(dataModel);
//        mmodels.add(dataModel1);
//        Gson gson = new Gson();
//
//        String inputString= gson.toJson(mmodels);
//        Type type = new TypeToken<ArrayList<DataModel>>() {}.getType();
//
//        mNewmodels = gson.fromJson(inputString, type);

//        System.out.println("inputString= " + inputString);

        memployeeDataAddDialogFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmployeeDetailsDialog = new CustomDialog();
                mEmployeeDetailsDialog.setInterface(MainActivity.this);
                mEmployeeDetailsDialog.show(getSupportFragmentManager(),"Dialog Box");
            }
        });


        mrecyclerView = findViewById(R.id.recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        memployeeDataAddRecyclerAdapter = new EmployeeDataAddRecyclerAdapter();
//        memployeeDataAddRecyclerAdapter.setModels(mNewmodels);
        memployeeDataAddRecyclerAdapter.setModels(mmodels);
        memployeeDataAddRecyclerAdapter.setiAdapterCommunicator(this);
        mrecyclerView.setAdapter(memployeeDataAddRecyclerAdapter);
        mdataBaseHelper = new DataBaseHelper(this);
        mDatabase = mdataBaseHelper.getWritableDatabase();
        LoadingPreExistingDataFromDB(mDatabase);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DATAMODEL_SERIALIZABLE_KEY,mmodels);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
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
        mdataBaseHelper.deleteModel(mDatabase,id);
        memployeeDataAddRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void editData(DataModel dataModel, int position) {

        Bundle bundleToDialog = new Bundle();
        bundleToDialog.putSerializable(DATAMODEL_SERIALIZABLE_KEY,dataModel);
        bundleToDialog.putInt(POSITION_KEY,position);
        mEmployeeDetailsDialog = new CustomDialog();
        mEmployeeDetailsDialog.setInterface(this);
        mEmployeeDetailsDialog.setArguments(bundleToDialog );
        mEmployeeDetailsDialog.show(getSupportFragmentManager(),"Dialog Box");


    }

    @Override
    public void DisplayDialogDataDatabase(DataModel model,int position) {


        if (model.getActionToBePerformed()== MODEL_ADD){

            mdataBaseHelper.insertModel(mDatabase,model);
            DataModel dataModel = mdataBaseHelper.fetchLast(mDatabase);
            Toast.makeText(this, dataModel.getUniqueId()+"", Toast.LENGTH_SHORT).show();
            mmodels.add(dataModel);

        }
        else if (model.getActionToBePerformed()== MODEL_EDIT){

            mdataBaseHelper.updateModel(mDatabase,model);
            mmodels.set(position,model);
        }
        memployeeDataAddRecyclerAdapter.notifyDataSetChanged();
    }


    private void LoadingPreExistingDataFromDB(SQLiteDatabase database) {
        Cursor cursor = mdataBaseHelper.fetchAllModel(database);
        if (cursor.getCount() != 0) {
            DataModel dataModel = new DataModel();
            while (!cursor.isLast()) {
                dataModel.setUniqueId(cursor.getInt(0));
                dataModel.setName(cursor.getString(1));
                dataModel.setMobileNumber(cursor.getInt(2));
                dataModel.setAddress(cursor.getString(3));
                mmodels.add(dataModel);
                cursor.moveToNext();
            }
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