package com.example.pushnotification.activities;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.pushnotification.R;
import com.example.pushnotification.databinding.ActivityItemScreenBinding;

public class ItemScreen extends AppCompatActivity {



    public String mImageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemScreenBinding activityItemScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_item_screen);
        activityItemScreenBinding.setItemScreen(this);
        Intent receivedIntent =  getIntent();
        Bundle bundle = receivedIntent.getExtras();
        activityItemScreenBinding.textView.setText(bundle.getString("price","default"));
        mImageUrl = bundle.getString("image");



    }
    @BindingAdapter({"app:imageResource"})
    public static void setImageResource(ImageView imageView, String resource) {
        Glide.with(imageView.getContext())
                .load(resource)
                .into(imageView);
    }

}
