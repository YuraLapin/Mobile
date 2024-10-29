package com.example.alarmmobileapp.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmmobileapp.R;

import java.util.List;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder> {

    List<Period> periods;
    private final LayoutInflater inflater;

    public PeriodAdapter(Context context, List<Period> periods){
        this.periods = periods;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PeriodAdapter.PeriodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new PeriodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodAdapter.PeriodViewHolder holder, int position) {
        Period period = periods.get(position);
        holder.name.setText(period.getName());
        holder.start.setText(period.getStartOfPeriod());
        holder.end.setText(period.getEndOfPeriod());
        holder.enabled.setChecked(period.isEnabled());
    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    public static class PeriodViewHolder extends RecyclerView.ViewHolder{

        TextView name,start,end;
        SwitchCompat enabled;

        public PeriodViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            enabled = itemView.findViewById(R.id.enabled);
        }
    }

}
