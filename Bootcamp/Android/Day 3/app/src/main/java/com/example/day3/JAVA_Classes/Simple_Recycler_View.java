package com.example.day3.JAVA_Classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.day3.Adapters.Custom_Adapter;
import com.example.day3.R;
import com.example.day3.pojo.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Simple_Recycler_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Models> arrayList = new ArrayList<>();
        Models models = new Models();
        models.setType(Models.NAME_IMAGE_TYPE);
        models.setName("Ice Cream Sundae");
        models.setAddress("145, Mall of India");
        models.setCost("Rs. 180");
        models.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
        models.setRating(3.50f);
        models.setImage(R.mipmap.recipe1);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        Custom_Adapter custom_adapter = new Custom_Adapter(arrayList);
        recyclerView.setAdapter(custom_adapter);
    }
}
