package com.example.introtojetpack.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.introtojetpack.R;
import com.example.introtojetpack.adapters.CustomAdapter;
import com.example.introtojetpack.pojo.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {
    ArrayList<Models> arrayList = new ArrayList<>();
    CustomAdapter customAdapter;
    private RecyclerView recyclerView;

    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customAdapter = new CustomAdapter(arrayList);
        setData();
        recyclerView.setAdapter(customAdapter);
    }

    private void setData() {

        Models models = new Models();
        models.setName("hello");
        models.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
        for (int i = 0; i < 10; i++) {
            arrayList.add(models);
        }
        customAdapter.notifyDataSetChanged();

    }


}
