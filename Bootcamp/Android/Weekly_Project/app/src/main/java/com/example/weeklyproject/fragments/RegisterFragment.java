package com.example.weeklyproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weeklyproject.R;
import com.example.weeklyproject.singletons.DataManager;

public class RegisterFragment extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Register User");
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final EditText etRegisterEmail = view.findViewById(R.id.et_register_name);
        final EditText etRegisterPass = view.findViewById(R.id.et_register_pass);


        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etRegisterEmail.getText().toString()) && !TextUtils.isEmpty(etRegisterPass.getText().toString())) {
                    DataManager.getInstance().doRegister(etRegisterEmail.getText().toString(), etRegisterPass.getText().toString());
                    dismiss();
                }
                else{
                    Toast.makeText(v.getContext(), "fill details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
