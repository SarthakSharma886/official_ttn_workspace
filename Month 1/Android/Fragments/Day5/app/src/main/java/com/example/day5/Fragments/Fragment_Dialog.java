package com.example.day5.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.day5.R;

public class Fragment_Dialog extends DialogFragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().setTitle("Dialog Fragment");

        View view = inflater.inflate(R.layout.fragment_dialog,container,false);
        Button dismis_bt= view.findViewById(R.id.bt_dismis);

        dismis_bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dismiss();
            }
        });
        return view;
    }
}
