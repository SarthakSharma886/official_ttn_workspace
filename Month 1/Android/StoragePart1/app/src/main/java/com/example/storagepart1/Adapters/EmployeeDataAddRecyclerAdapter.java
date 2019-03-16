package com.example.storagepart1.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storagepart1.Interfaces.IAdapterActivityCommunicator;
import com.example.storagepart1.POJO.DataModel;
import com.example.storagepart1.R;

import java.util.ArrayList;

import static com.example.storagepart1.Constants.Constants.MODEL_EDIT;

public class EmployeeDataAddRecyclerAdapter extends RecyclerView.Adapter<EmployeeDataAddRecyclerAdapter.Viewholder> {

    ArrayList<DataModel> mmodels;
    IAdapterActivityCommunicator mIAdapterCommunicator;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_employee_details, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, int i) {
        final DataModel dataModel = mmodels.get(i);
        viewholder.tvName.setText(dataModel.getName());
        viewholder.tvMobileNum.setText(String.valueOf(dataModel.getMobileNumber()));
        viewholder.tvAddress.setText(dataModel.getAddress());
        viewholder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIAdapterCommunicator !=null){
                    dataModel.setActionToBePerformed(MODEL_EDIT);
                    mIAdapterCommunicator.editData(dataModel,viewholder.getAdapterPosition());
                }
                Toast.makeText(v.getContext(), "Edit", Toast.LENGTH_SHORT).show();
            }
        });
        viewholder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIAdapterCommunicator !=null){
                    mIAdapterCommunicator.deleteData(dataModel.getUniqueId(),viewholder.getAdapterPosition());
                }
                Toast.makeText(v.getContext(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mmodels.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvName,tvMobileNum,tvAddress;
        ImageButton ibEdit,ibDelete;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvMobileNum = itemView.findViewById(R.id.tv_mobile);
            tvName = itemView.findViewById(R.id.tv_name);
            ibDelete = itemView.findViewById(R.id.ib_delete);
            ibEdit = itemView.findViewById(R.id.ib_edit);

        }
    }

    public void setModels(ArrayList<DataModel> models){
        this.mmodels = models;
    }

    public void setiAdapterCommunicator(IAdapterActivityCommunicator iAdapterActivityCommunicator){
        this.mIAdapterCommunicator = iAdapterActivityCommunicator;
    }


}
