package com.example.weeklyproject.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.weeklyproject.POJO.FetchUserData;
import com.example.weeklyproject.R;
import com.example.weeklyproject.interfaces.IActivityAdapterComm;

import java.io.File;
import java.util.ArrayList;

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.ViewHolder> {

    ArrayList<FetchUserData> mFetchUserDataArrayList = new ArrayList<>();
    IActivityAdapterComm mIActivityAdapterComm = null;


    public void setRetroAdapter(ArrayList<FetchUserData> postList) {
        this.mFetchUserDataArrayList = postList;
    }


    public void setAdapterActivity(IActivityAdapterComm iActivityAdapterComm) {
        mIActivityAdapterComm = iActivityAdapterComm;
    }

    @NonNull
    @Override
    public RetroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RetroAdapter.ViewHolder viewHolder, int i) {

        final FetchUserData fetchUserData = mFetchUserDataArrayList.get(i);

        viewHolder.tvFirstName.setText(fetchUserData.getFirstName());
        viewHolder.tvLastName.setText(fetchUserData.getLastName());

        final Context context = viewHolder.ivProfile.getContext();

        if (context != null) {
            Glide.with(context)
                    .load(fetchUserData.getAvatar())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(viewHolder.ivProfile);
        }

        viewHolder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIActivityAdapterComm != null) {
                    Glide.with(v.getContext())
                            .asFile()
                            .load(fetchUserData.getAvatar())
                            .into(new CustomTarget<File>() {
                                @Override
                                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                    mIActivityAdapterComm.shareImage(resource,fetchUserData.getFirstName());
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                }
                            });
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIActivityAdapterComm != null) {
                    mIActivityAdapterComm.showFragment(fetchUserData);
                }
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the Action");
            menu.add(this.getAdapterPosition(), 120, 0, "Delete");
            menu.add(this.getAdapterPosition(), 121, 1, "Update");
        }

    }


}
