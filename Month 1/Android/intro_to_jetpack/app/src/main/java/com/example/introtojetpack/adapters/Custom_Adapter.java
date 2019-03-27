package com.example.introtojetpack.adapters;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.introtojetpack.R;
import com.example.introtojetpack.databinding.CardviewRecyclerBinding;
import com.example.introtojetpack.pojo.Models;

import java.util.ArrayList;


public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.ViewHolder> {

    private ArrayList<Models> mArrayListModels;
    CardviewRecyclerBinding mBinding;


    public Custom_Adapter(ArrayList<Models> mArrayListModels){
    this.mArrayListModels = mArrayListModels;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=(LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.cardview_recycler, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recycler,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

      Models model = mArrayListModels.get(i);

        mBinding.setRecyclermodel(model);



    }

    @Override
    public int getItemCount() {
        return mArrayListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

        }
    }


}
