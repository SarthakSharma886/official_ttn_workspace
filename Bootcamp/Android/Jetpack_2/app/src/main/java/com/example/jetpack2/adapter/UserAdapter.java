package com.example.jetpack2.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetpack2.R;
import com.example.jetpack2.databinding.CardviewUserDetailsBinding;
import com.example.jetpack2.pojo.UserData;

public class UserAdapter extends ListAdapter<UserData, UserAdapter.UserHolder> {

    CardviewUserDetailsBinding mBinding;


    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    public UserData getUser(int position) {
        return getItem(position);
    }

    private static final DiffUtil.ItemCallback<UserData> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserData>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserData userData, @NonNull UserData t1) {
            return userData.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserData userData, @NonNull UserData t1) {
            return userData.getUserName().equals(t1.getUserName());
        }
    };

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_user_details, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {

        userHolder.textView.setText(getItem(i).getUserName());

//        mBinding.setUserdata(getItem(i));


    }


    class UserHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_userdetails);
//            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
