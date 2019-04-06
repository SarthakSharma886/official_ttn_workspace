package com.example.weeklyproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weeklyproject.POJO.FetchUserData;
import com.example.weeklyproject.R;
import com.example.weeklyproject.singletons.DataManager;

import java.util.ArrayList;

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.ViewHolder> {

    ArrayList<FetchUserData> mFetchUserDataArrayList = new ArrayList<>();


    public void setRetroAdapter(ArrayList<FetchUserData> postList) {
        this.mFetchUserDataArrayList = postList;
    }


    @NonNull
    @Override
    public RetroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RetroAdapter.ViewHolder viewHolder, int i) {

        final FetchUserData fetchUserData = mFetchUserDataArrayList.get(i);

        viewHolder.tvLastName.setText(fetchUserData.getFirstName());
        viewHolder.tvFirstName.setText(fetchUserData.getLastName());

        final Context context = viewHolder.ivProfile.getContext();

        if (context != null) {

            Glide.with(context)
                    .load(fetchUserData.getAvatar())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(viewHolder.ivProfile);
        }


//        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(v.getContext(), "long long", Toast.LENGTH_SHORT).show();
//
//
//                return true;
//            }
//        });


        viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            }





        });


    }

    @Override
    public int getItemCount() {
        return mFetchUserDataArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        ImageView ivProfile;
        TextView tvFirstName, tvLastName;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the Action");
            menu.add(this.getAdapterPosition(), 120, 0, "Delete");
            menu.add(this.getAdapterPosition(), 121, 1, "Update");
        }

    }



}
