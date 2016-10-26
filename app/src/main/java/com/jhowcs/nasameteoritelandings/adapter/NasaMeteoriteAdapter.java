package com.jhowcs.nasameteoritelandings.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonathan_campos on 26/10/2016.
 */

public class NasaMeteoriteAdapter extends RecyclerView.Adapter<NasaMeteoriteAdapter.NasaMeteoriteViewHolder> {

    private List<NasaMeteoriteLandings> mMeteoriteLandings;

    public NasaMeteoriteAdapter() {
        this.mMeteoriteLandings = Collections.EMPTY_LIST;
    }

    @Override
    public NasaMeteoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_meteorite_list_row, parent, false);

        return new NasaMeteoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NasaMeteoriteViewHolder holder, int position) {
        NasaMeteoriteLandings nasaMeteoriteLandings = mMeteoriteLandings.get(position);

        holder.txtName.setText(nasaMeteoriteLandings.getName());
        holder.txtMass.setText(nasaMeteoriteLandings.getMass());
        holder.txtClass.setText(nasaMeteoriteLandings.getRecclass());
    }

    @Override
    public int getItemCount() {
        return mMeteoriteLandings.size();
    }

    public void setListMeteoriteLandings(List<NasaMeteoriteLandings> nasaMeteoriteLandings) {
        mMeteoriteLandings = nasaMeteoriteLandings;
        notifyDataSetChanged();
    }

    public class NasaMeteoriteViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtMass, txtClass;

        public NasaMeteoriteViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtMeteoriteName);
            txtMass = (TextView) itemView.findViewById(R.id.txtMeteoriteMass);
            txtClass= (TextView) itemView.findViewById(R.id.txtMeteoriteClass);
        }
    }
}
