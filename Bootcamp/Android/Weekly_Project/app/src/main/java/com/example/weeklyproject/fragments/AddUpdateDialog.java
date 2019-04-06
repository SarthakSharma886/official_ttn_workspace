package com.example.weeklyproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weeklyproject.R;
import com.example.weeklyproject.singletons.DataManager;

public class AddUpdateDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Register User");
        return inflater.inflate(R.layout.dialog_add_update, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
////        final EditText etRegisterEmail = view.findViewById(R.id.et_register_name);
////        final EditText etRegisterPass = view.findViewById(R.id.et_register_pass);
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Registered", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DataManager.getInstance().doRegister(etRegisterEmail.getText().toString(),etRegisterPass.getText().toString());
//            }
//        });
//
//        view.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        super.onViewCreated(view, savedInstanceState);
//    }
}
