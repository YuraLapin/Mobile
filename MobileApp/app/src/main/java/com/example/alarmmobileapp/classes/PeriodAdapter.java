package com.example.alarmmobileapp.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmmobileapp.AlarmActivity;
import com.example.alarmmobileapp.R;
import com.example.alarmmobileapp.interfaces.RecyclerViewInterface;

import java.util.List;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder> {

    List<Period> periods;
    private final Context context;
    private final LayoutInflater inflater;
    private final RecyclerViewInterface recyclerViewInterface;

    public PeriodAdapter(Context context, List<Period> periods, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.periods = periods;
        this.recyclerViewInterface = recyclerViewInterface;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PeriodAdapter.PeriodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new PeriodViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodAdapter.PeriodViewHolder holder, int position) {
        Period period = periods.get(position);
        holder.name.setText(period.getName());
        holder.start.setText(period.getStartOfPeriod());
        holder.end.setText(period.getEndOfPeriod());

        if (period.getDaysOfWeek() != null && !period.getDaysOfWeek().isEmpty()) {
            StringBuilder daysStringBuilder = new StringBuilder();
            for (DayOfWeek day : period.getDaysOfWeek()) {
                daysStringBuilder.append(DayOfWeek.dayToShortString(day)).append(", ");
            }
            if (daysStringBuilder.length() > 0) {
                daysStringBuilder.setLength(daysStringBuilder.length() - 2);
            }
            holder.daysOfWeek.setText(daysStringBuilder.toString());
        } else {
            holder.daysOfWeek.setText("Нет выбранных дней");
        }


        holder.enabled.setOnCheckedChangeListener(null);
        holder.enabled.setChecked(period.isEnabled());

        holder.enabled.setOnCheckedChangeListener((buttonView, isChecked) -> {

            period.setEnabled(isChecked);
            String message = period.isEnabled() ? "Включен" : "Выключен";
            Toast.makeText(context, "Период " + period.getName() + " " + message, Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    public static class PeriodViewHolder extends RecyclerView.ViewHolder{

        TextView name,start,end,daysOfWeek;
        SwitchCompat enabled;

        public PeriodViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            enabled = itemView.findViewById(R.id.enabled);
            daysOfWeek = itemView.findViewById(R.id.daysOfWeek);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
    public void updatePeriods(List<Period> newPeriods) {
        this.periods = newPeriods;
         notifyDataSetChanged();
    }

}
