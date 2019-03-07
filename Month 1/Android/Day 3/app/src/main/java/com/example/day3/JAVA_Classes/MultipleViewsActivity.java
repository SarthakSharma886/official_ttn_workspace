package com.example.day3.JAVA_Classes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.day3.Adapters.Custom_Adapter;
import com.example.day3.R;
import com.example.day3.pojo.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MultipleViewsActivity extends AppCompatActivity {

    private ArrayList<Models> data1;
    private Custom_Adapter multipleViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        data1 = new ArrayList<>();

        multipleViewAdapter = new Custom_Adapter(data1);
        recyclerView.setAdapter(multipleViewAdapter);

        putData();
    }

    public void putData() {
        Models dataModel;

        for (int i = 0; i < 5; i++) {
            dataModel = new Models();
            dataModel.setType(Models.NAME_IMAGE_TYPE);
            dataModel.setName("Ice Cream Sundae");
            dataModel.setAddress("145, Mall of India");
            dataModel.setCost("Rs. 180");
            dataModel.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
            dataModel.setRating(3.50f);
            dataModel.setImage(R.mipmap.recipe1);
            data1.add(dataModel);
        }

        for (int i = 0; i < 5; i++) {
            dataModel = new Models();
            dataModel.setType(Models.IMAGE_ONLY_TYPE);
            dataModel.setImage(R.mipmap.recipe1);
            data1.add(dataModel);
        }

        for (int i = 0; i < 5; i++) {
            dataModel = new Models();
            dataModel.setType(Models.NAME_ONLY_TYPE);
            dataModel.setName("Ice Cream Sundae");
            dataModel.setAddress("145, Mall of India");
            dataModel.setCost("Rs. 180");
            dataModel.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
            dataModel.setRating(3.50f);
            data1.add(dataModel);
        }
    }
}