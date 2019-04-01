package com.example.jetpack2.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.jetpack2.R;
import com.example.jetpack2.databinding.FragmentDialogBinding;
import com.example.jetpack2.interfaces.IAddUser;
import com.example.jetpack2.pojo.UserData;


public class AddUserDialog extends DialogFragment {


    IAddUser mIAdduser = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final FragmentDialogBinding fragmentDialogBinding = DataBindingUtil.bind(view);

        fragmentDialogBinding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        fragmentDialogBinding.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(fragmentDialogBinding.etUserdata.getText().toString())) {
                    UserData userData = new UserData(fragmentDialogBinding.etUserdata.getText().toString());
                    if (mIAdduser != null) {
                        mIAdduser.addUser(userData);
                    }
                    dismiss();
                }
            }
        });


    }


    public void setActivity(IAddUser iAddUser) {

        mIAdduser = iAddUser;

    }


}
