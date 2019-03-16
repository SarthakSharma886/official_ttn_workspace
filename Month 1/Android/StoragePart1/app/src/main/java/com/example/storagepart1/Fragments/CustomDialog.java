package com.example.storagepart1.Fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.storagepart1.Interfaces.IDialogFragmentCommunicator;
import com.example.storagepart1.POJO.DataModel;
import com.example.storagepart1.R;

import static com.example.storagepart1.Constants.Constants.DATAMODEL_SERIALIZABLE_KEY;
import static com.example.storagepart1.Constants.Constants.MODEL_ADD;
import static com.example.storagepart1.Constants.Constants.MODEL_EDIT;
import static com.example.storagepart1.Constants.Constants.POSITION_KEY;

public class CustomDialog extends DialogFragment {

    int mPosition = -1;
//    String mName,mAddress;
    DataModel mDataModel;
    IDialogFragmentCommunicator mIDialogFragmentCommunicator;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

        final EditText etName, etAddress, etMobileNum;
        Button btOk, btCancel;
        etName = view.findViewById(R.id.et_name);
        etAddress = view.findViewById(R.id.et_address);
        etMobileNum = view.findViewById(R.id.et_mobile);
        btOk = view.findViewById(R.id.bt_ok);
        btCancel = view.findViewById(R.id.bt_cancel);
        if (mDataModel != null) {
            etName.setText(mDataModel.getName());
            etAddress.setText(mDataModel.getAddress());
            etMobileNum.setText(String.valueOf(mDataModel.getMobileNumber()));
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
                    dataModel.setUniqueId(mDataModel.getUniqueId());
                }
                else{
                    dataModel.setActionToBePerformed(MODEL_ADD);
                }
                if((!TextUtils.isEmpty(etName.getText().toString()))&&(!TextUtils.isEmpty(etMobileNum.getText().toString()))&&(!TextUtils.isEmpty(etAddress.getText().toString()))&&(Long.parseLong(etMobileNum.getText().toString())<=9999999999L)) {
                    dataModel.setName(etName.getText().toString());
                    dataModel.setMobileNumber(Long.parseLong(etMobileNum.getText().toString()));
                    dataModel.setAddress(etAddress.getText().toString());
                    if (mIDialogFragmentCommunicator != null) {
                        mIDialogFragmentCommunicator.DisplayDialogDataDatabase(dataModel, mPosition);
                    }
                    dismiss();
                }
            }
        });


    }

    public void setInterface(IDialogFragmentCommunicator iDialogFragmentCommunicator){
        this.mIDialogFragmentCommunicator = iDialogFragmentCommunicator;
    }
}
