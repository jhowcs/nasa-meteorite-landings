package com.jhowcs.nasameteoritelandings.adapter;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.repository.MeteoriteLandingsRepository;
import com.jhowcs.nasameteoritelandings.util.ClickListener;

/**
 * Created by jonathan_campos on 26/10/2016.
 */

public class NasaMeteoriteAdapter extends RecyclerView.Adapter<NasaMeteoriteAdapter.NasaMeteoriteViewHolder> implements Parcelable {

    private Cursor mCursor;

    private ClickListener<NasaMeteoriteLandings> mListener;

    public static final Creator<NasaMeteoriteAdapter> CREATOR = new Creator<NasaMeteoriteAdapter>() {
        @Override
        public NasaMeteoriteAdapter createFromParcel(Parcel in) {
            return new NasaMeteoriteAdapter(in);
        }

        @Override
        public NasaMeteoriteAdapter[] newArray(int size) {
            return new NasaMeteoriteAdapter[size];
        }
    };

    public NasaMeteoriteAdapter() {
    }

    protected NasaMeteoriteAdapter(Parcel in) {
    }

    @Override
    public NasaMeteoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_meteorite_list_row, parent, false);

        return new NasaMeteoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NasaMeteoriteViewHolder holder, int position) {
        NasaMeteoriteLandings nasaMeteoriteLandings = getItem(position);

        holder.txtName.setText(nasaMeteoriteLandings.getName());
        holder.txtMass.setText(String.valueOf(nasaMeteoriteLandings.getMass()));
    }

    @Override
    public int getItemCount() {
        return (mCursor == null) ? 0 : mCursor.getCount();
    }

    public void setClickListener(ClickListener listener) {
        mListener = listener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public class NasaMeteoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtName, txtMass;

        public NasaMeteoriteViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            txtName = (TextView) itemView.findViewById(R.id.txtMeteoriteName);
            txtMass = (TextView) itemView.findViewById(R.id.txtMeteoriteMass);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null) {
                mListener.onItemClicked(getItem(getAdapterPosition()));
            }
        }
    }

    public void setCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    private NasaMeteoriteLandings getItem(int position) {
        mCursor.moveToPosition(position);

        return MeteoriteLandingsRepository.nasaMeteoriteLandingsFromCursor(mCursor);
    }
}
