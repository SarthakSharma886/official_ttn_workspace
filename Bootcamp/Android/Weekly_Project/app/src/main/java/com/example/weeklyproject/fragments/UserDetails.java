package com.example.weeklyproject.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weeklyproject.POJO.DataModel;
import com.example.weeklyproject.POJO.FetchUserData;
import com.example.weeklyproject.R;

public class UserDetails extends Fragment {

ImageView ivProfilePic;
TextView tvFirstName,tvLastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivProfilePic= view.findViewById(R.id.iv_profile_pic);
        tvFirstName= view.findViewById(R.id.tv_first_name);
        tvLastName= view.findViewById(R.id.tv_last_name);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        FetchUserData dataModel = (FetchUserData) bundle.getSerializable("DATAMODEL");
        Glide.with(getActivity())
                .load(dataModel.getAvatar())
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(ivProfilePic);
        tvFirstName.setText(dataModel.getFirstName());
        tvLastName.setText(dataModel.getLastName());


    }
}
