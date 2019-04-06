package com.example.day3.JAVA_Classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.day3.R;

public class MainActivity extends AppCompatActivity {

    TextView simple_recycle,pagination,heterogeneous_recycle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simple_recycle = findViewById(R.id.simple);
        pagination =findViewById(R.id.page);
        heterogeneous_recycle = findViewById(R.id.hetro);
        simple_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,Simple_Recycler_View.class);
                startActivity(intent);
            }
        });
        pagination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,PaginationActivity.class);
                startActivity(intent);
            }
        });
        heterogeneous_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,MultipleViewsActivity.class);
                startActivity(intent);
            }
        });

    }
}
