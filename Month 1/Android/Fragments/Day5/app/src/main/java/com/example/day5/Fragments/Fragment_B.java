package com.example.day5.Fragments;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day5.POJO.Models;
import com.example.day5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_B extends Fragment {

    private Models mModels = new Models();
    TextView name, timestamp;
    ImageView imageView;




    public Fragment_B() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "on Attach Fragment A", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "on Create Fragment B", Toast.LENGTH_SHORT).show();
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "on Create View Fragment A", Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity(), "on View Created Fragment A", Toast.LENGTH_SHORT).show();

        imageView = view.findViewById(R.id.imageView_b);
        name = view.findViewById(R.id.textView_name_b);
        timestamp = view.findViewById(R.id.textView_time_b);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getActivity(), "on Activity Created Fragment B", Toast.LENGTH_SHORT).show();

        if(savedInstanceState!=null) {
            mModels = (Models) savedInstanceState.getSerializable("models");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "on start Fragment B", Toast.LENGTH_SHORT).show();
        if(mModels!=null) {
            imageView.setImageResource(mModels.getImage());
            name.setText(mModels.getName());
            timestamp.setText(mModels.getTimestamp());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "on Resume Fragment B", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "on Pause Fragment B", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "on stop Fragment B", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "on Destroy View Fragment B", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "on Destroy Fragment B", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "on Detach Fragment B", Toast.LENGTH_SHORT).show();
    }




    public void setModel(Models models){
        this.mModels =models;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("models",mModels);
    }
}
