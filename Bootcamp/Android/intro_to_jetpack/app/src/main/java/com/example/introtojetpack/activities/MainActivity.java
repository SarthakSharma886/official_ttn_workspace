package com.example.introtojetpack.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.introtojetpack.R;
import com.example.introtojetpack.fragments.RecyclerFragment;
import com.example.introtojetpack.utils.ColorUtils;
import com.example.introtojetpack.viewmodel.MyViewModel;


public class MainActivity extends AppCompatActivity {
    private MyViewModel mModel;
    private ConstraintLayout mChangeColor;
    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChangeColor = findViewById(R.id.colorchange);
        mModel = ViewModelProviders.of(this).get(MyViewModel.class);

        findViewById(R.id.bt_color_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ColorUtils.getRandomColor();
                mChangeColor.setBackgroundColor(color);
                mModel.saveColor = color;

            }
        });


        mChangeColor.setBackgroundColor(mModel.saveColor);

        RecyclerFragment recyclerFragment = new RecyclerFragment();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.recyclerView_replacing, recyclerFragment);
        fragmentTransaction.commit();


    }


}
