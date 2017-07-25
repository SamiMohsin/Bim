package com.graphicalinfo.bim.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graphicalinfo.bim.R;
import com.graphicalinfo.bim.entities.Drugs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.graphicalinfo.bim.utils.MathUtils.adoldrops;


/**
 * Created by ayoob on 25/07/17.
 */

public class MedicneFragment  extends Fragment{


    RecyclerView recyclerView;
    ArrayList<Drugs> drugs = new ArrayList<>();

    RecyclerView.LayoutManager layoutManager;
    MedicineAdapter medicineAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        medicineAdapter = new MedicineAdapter(getData());



        recyclerView.setAdapter(medicineAdapter);
        //medicineAdapter.setDrugs(getData());
        return view;
    }


    public ArrayList<Drugs> getData(){
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Acetaminophen","Paracetamol Drops", 1.0,2.0));
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Sami","Sami", 1.0,2.0));
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Sami","Sami", 1.0,2.0));
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Sami","Sami", 1.0,2.0));
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Sami","Sami", 1.0,2.0));
        drugs.add(new Drugs(getResources().getDrawable(R.drawable.drops), "Sami","Sami", 1.0,2.0));
        return drugs;
    }



}
