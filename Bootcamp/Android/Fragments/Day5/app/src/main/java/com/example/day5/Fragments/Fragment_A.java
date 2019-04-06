package com.example.day5.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day5.Adapters.Custom_Adapter;
import com.example.day5.Interfaces.ICommunicator;
import com.example.day5.POJO.Models;
import com.example.day5.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_A extends Fragment {
    private RecyclerView recyclerView;
    private ICommunicator communicator;




    public void setCommunicator(ICommunicator communicator){
        this.communicator = communicator;
    }



    public Fragment_A() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(context, "on Attach Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "on Create Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Toast.makeText(getActivity(), "on CreateView Fragment A", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity(), "on ViewCreated Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getActivity(), "on Activity Created Fragment A", Toast.LENGTH_SHORT).show();

        ArrayList<Models> arrayList = new ArrayList<>();
        Models models = new Models();
        models.setName("Ice Cream Sundae");
        models.setTimestamp(new SimpleDateFormat("dd MMMM yyyy hh:mm:ss").format(new Date()));
        models.setImage(R.mipmap.recipe1);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        arrayList.add(models);
        Custom_Adapter custom_adapter = new Custom_Adapter(arrayList);
        custom_adapter.setCommunicator(communicator);
        recyclerView.setAdapter(custom_adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "on start Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "on Resume Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "on Pause Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "on stop Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "on Destroy View Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "on Destroy Fragment A", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "on Detach Fragment A", Toast.LENGTH_SHORT).show();
    }
}
