package com.graphicalinfo.bim.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.graphicalinfo.bim.R;
import com.graphicalinfo.bim.entities.Drugs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayoob on 25/07/17.
 */

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>{


    private ArrayList<Drugs> drugs;
    DecimalFormat df = new DecimalFormat("###.#");
    public MedicineAdapter(ArrayList<Drugs> drugs) {
        this.drugs = drugs;
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.medicine_items, null);
        MedicineViewHolder medicineViewHolder = new MedicineViewHolder(view);
        return medicineViewHolder;
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        Drugs drug = drugs.get(position);
        holder.image.setImageDrawable(drug.getImage());
        holder.name.setText("Generic Name: ");
        holder.trade.setText("Trade Name: ");
        holder.mg.setText("Dosage: ");

        holder.name.append(drug.getName());
        holder.trade.append(drug.getTrade());

        holder.mg.append(df.format(drug.getMg()));
        holder.mg.append(" mg");
        holder.mg.append(" / ");

        holder.ml.setText(df.format(drug.getMl()));
        holder.ml.append(" ml");


    }


    @Override
    public int getItemCount() {
        return drugs.size();
    }



    public class MedicineViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView trade;
        TextView mg;
        TextView ml;
        public MedicineViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            trade = (TextView) itemView.findViewById(R.id.trade);
            mg = (TextView) itemView.findViewById(R.id.mg);
            ml = (TextView) itemView.findViewById(R.id.ml);
        }
    }
}
