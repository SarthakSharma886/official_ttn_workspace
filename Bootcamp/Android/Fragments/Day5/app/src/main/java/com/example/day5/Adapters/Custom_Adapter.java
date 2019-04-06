package com.example.day5.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.day5.Interfaces.ICommunicator;
import com.example.day5.POJO.Models;
import com.example.day5.R;

import java.util.ArrayList;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.ViewHolder> {

    private ArrayList<Models> arraylist_models;
    private ICommunicator communicator;

    public Custom_Adapter(ArrayList<Models> arraylist_models){
    this.arraylist_models = arraylist_models;
    }


    public void setCommunicator(ICommunicator communicator){
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_text,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Models model = arraylist_models.get(i);

         viewHolder.name.setText(model.getName());
         viewHolder.timestamp.setText(model.getTimestamp());
         viewHolder.imageView.setImageResource(model.getImage());
         viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i("click","recyclerview image");
             }
         });
         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i("click","recyclerview");
                 if(communicator != null){
                     communicator.exchange(model);
                 }

             }
         });


    }

    @Override
    public int getItemCount() {
        return arraylist_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, timestamp;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            timestamp = itemView.findViewById(R.id.timestamp);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
