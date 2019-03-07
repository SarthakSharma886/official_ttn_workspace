package com.example.day3.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.day3.R;
import com.example.day3.pojo.Models;

import java.util.ArrayList;

import static com.example.day3.pojo.Models.IMAGE_ONLY_TYPE;
import static com.example.day3.pojo.Models.NAME_ONLY_TYPE;

public class Custom_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Models> models_array_list;
    public Custom_Adapter(ArrayList<Models> models_array_list){
        this.models_array_list = models_array_list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i ==  NAME_ONLY_TYPE){
            return new OnlyNameViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text, viewGroup, false));
        }
        else if(i == IMAGE_ONLY_TYPE){
            return new OnlyImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image, viewGroup, false));
        }
        else{
            return new NameImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_text, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Models multipleViewModel = models_array_list.get(i);
        if(viewHolder instanceof OnlyNameViewHolder){
            ((OnlyNameViewHolder) viewHolder).name.setText(multipleViewModel.getName());
            ((OnlyNameViewHolder) viewHolder).address.setText(multipleViewModel.getAddress());
            ((OnlyNameViewHolder) viewHolder).timestamp.setText(multipleViewModel.getTimestamp());
            ((OnlyNameViewHolder) viewHolder).cost.setText(multipleViewModel.getCost());
            ((OnlyNameViewHolder) viewHolder).ratingBar.setRating(multipleViewModel.getRating());
        }
        else if(viewHolder instanceof OnlyImageViewHolder){
            ((OnlyImageViewHolder) viewHolder).imageView.setImageResource(multipleViewModel.getImage());
        }
        else {
                         ((NameImageViewHolder) viewHolder).name.setText(multipleViewModel.getName());
             ((NameImageViewHolder) viewHolder).address.setText(multipleViewModel.getAddress());
             ((NameImageViewHolder) viewHolder).timestamp.setText(multipleViewModel.getTimestamp());
             ((NameImageViewHolder) viewHolder).cost.setText(multipleViewModel.getCost());
             ((NameImageViewHolder) viewHolder).ratingBar.setRating(multipleViewModel.getRating());
             ((NameImageViewHolder) viewHolder).imageView.setImageResource(multipleViewModel.getImage());
        }

    }


    @Override
    public int getItemCount() {
        return models_array_list.size();
    }









    @Override
    public int getItemViewType(int position) {
        return models_array_list.get(position).getType();
    }

    class OnlyNameViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, timestamp, cost;
        RatingBar ratingBar;
        public OnlyNameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            timestamp = itemView.findViewById(R.id.timestamp);
            cost = itemView.findViewById(R.id.cost);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    class OnlyImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;


        public OnlyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    class NameImageViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, timestamp, cost;
        RatingBar ratingBar;
        ImageView imageView;
        public NameImageViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            timestamp = itemView.findViewById(R.id.timestamp);
            cost = itemView.findViewById(R.id.cost);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }






}
