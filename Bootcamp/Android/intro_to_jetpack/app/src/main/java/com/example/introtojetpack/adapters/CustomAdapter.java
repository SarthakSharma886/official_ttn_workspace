package com.example.introtojetpack.adapters;


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


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    CardviewRecyclerBinding mBinding;
    private ArrayList<Models> mArrayListModels;


    public CustomAdapter(ArrayList<Models> mArrayListModels) {
        this.mArrayListModels = mArrayListModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recycler, viewGroup, false));
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
