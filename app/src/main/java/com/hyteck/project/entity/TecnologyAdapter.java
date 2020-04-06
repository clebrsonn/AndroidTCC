package com.hyteck.project.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hyteck.project.R;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnologyAdapter extends RecyclerView.Adapter<TecnologyAdapter.TecnologyViewHolder> {

    private final List<Tecnology> mValues;

    public TecnologyAdapter(List<Tecnology> items) {
        mValues = items;
    }

    @Override
    public TecnologyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new TecnologyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TecnologyViewHolder holder, int position) {
        if(mValues.get(position)!=null) {
            holder.mItem = mValues.get(position);
            holder.name.setText(mValues.get(position).getName());
            holder.batery.setText(mValues.get(position).getBatery());

            holder.distance.setText(mValues.get(position).getDistance());
            holder.energyConsumption.setText(mValues.get(position).getEnergyConsumption());
        }

    }

    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }

    public class TecnologyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Tecnology mItem;

        private final TextView name;
        private final TextView distance;
        private final TextView energyConsumption;
        private final TextView batery;


        public TecnologyViewHolder(View view) {
            super(view);
            mView = view;
            name = itemView.findViewById(R.id.tecnologyName);
            distance = itemView.findViewById(R.id.tecnologyDistance);
            energyConsumption = itemView.findViewById(R.id.energyConsumption);
            batery = itemView.findViewById(R.id.tecnolyBatery);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
