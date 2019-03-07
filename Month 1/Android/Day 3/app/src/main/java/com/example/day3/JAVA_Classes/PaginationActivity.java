package com.example.day3.JAVA_Classes;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.day3.Adapters.Custom_Adapter;
import com.example.day3.R;
import com.example.day3.pojo.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PaginationActivity extends AppCompatActivity {

    private ArrayList<Models> data;
    private Custom_Adapter myAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        data = new ArrayList<>();

        myAdapter = new Custom_Adapter(data);
        recyclerView.setAdapter(myAdapter);

        putData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItems = layoutManager.getItemCount();
                int visibleItems = layoutManager.getChildCount();
                int scrolledOutItems = layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && visibleItems + scrolledOutItems == totalItems) {
                    Toast.makeText(PaginationActivity.this, "Loading ...", Toast.LENGTH_SHORT).show();
                    isScrolling = false;
                    fetchData();
                }
            }
        });

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
            data.add(dataModel);
        }
    }

    public void fetchData(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Models dataModel = new Models();
                    dataModel.setType(Models.NAME_IMAGE_TYPE);
                    dataModel.setName("Ice Cream Sundae");
                    dataModel.setAddress("145, Mall of India");
                    dataModel.setCost("Rs. 180");
                    dataModel.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
                    dataModel.setRating(3.50f);
                    dataModel.setImage(R.mipmap.recipe1);
                    data.add(dataModel);

                    myAdapter.notifyDataSetChanged();
                }
            }
        }, 2000);

    }
}