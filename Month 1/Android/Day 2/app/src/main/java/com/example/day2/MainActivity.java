package com.example.day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView_1,textView_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_1 =findViewById(R.id.textView);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main2);
                textView_2 = findViewById(R.id.textView5);
                textView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main3);
                    }
                });
            }
        });
    }
}
