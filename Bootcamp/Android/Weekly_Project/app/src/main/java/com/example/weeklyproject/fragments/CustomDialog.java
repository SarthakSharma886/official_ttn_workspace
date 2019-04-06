package com.example.weeklyproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.weeklyproject.POJO.DataModel;
import com.example.weeklyproject.R;


import static com.example.weeklyproject.constants.Constants.*;

public class CustomDialog extends DialogFragment {

    int mPosition = -1;
    //    String mName,mAddress;
    DataModel mDataModel;
//    IDialogFragmentCommunicator mIDialogFragmentCommunicator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundleFromActivity;
        bundleFromActivity = getArguments();
        if (bundleFromActivity != null) {
            DataModel dataModel = (DataModel) bundleFromActivity.getSerializable(DATAMODEL_SERIALIZABLE_KEY);
            mPosition = bundleFromActivity.getInt(POSITION_KEY);
            if (dataModel != null) {
                getDialog().setTitle("Edit Details");
                mDataModel = dataModel;
            }
        } else {
            getDialog().setTitle("Add Data");
        }
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText etFirstName, etLastName, etImage;
        Button btOk, btCancel;
        etFirstName = view.findViewById(R.id.et_first_name);
        etLastName = view.findViewById(R.id.et_last_name);
        etImage = view.findViewById(R.id.et_address);
        btOk = view.findViewById(R.id.bt_ok);
        btCancel = view.findViewById(R.id.bt_cancel);


        if (mDataModel != null) {
            etFirstName.setText(mDataModel.getFirstName());
            etLastName.setText(mDataModel.getLastName());
            etImage.setText(String.valueOf(mDataModel.getAvatar()));
        }
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataModel dataModel = new DataModel();
                if (mDataModel != null) {
                    dataModel.setActionToBePerformed(MODEL_EDIT);
                    dataModel.setId(mDataModel.getId());
                } else {
                    dataModel.setActionToBePerformed(MODEL_ADD);
                }
                if ((!TextUtils.isEmpty(etFirstName.getText().toString())) && (!TextUtils.isEmpty(etImage.getText().toString())) && (!TextUtils.isEmpty(etLastName.getText().toString())) && (Long.parseLong(etImage.getText().toString()) <= 9999999999L)) {
                    dataModel.setFirstName(etFirstName.getText().toString());
                    dataModel.setAvatar(etImage.getText().toString());
                    dataModel.setLastName(etLastName.getText().toString());
//                    if (mIDialogFragmentCommunicator != null) {
//                        mIDialogFragmentCommunicator.DisplayDialogDataDatabase(dataModel, mPosition);
//                    }
                    dismiss();
                }
            }
        });


    }

//    public void setInterface(IDialogFragmentCommunicator iDialogFragmentCommunicator){
//        this.mIDialogFragmentCommunicator = iDialogFragmentCommunicator;
//    }
}
