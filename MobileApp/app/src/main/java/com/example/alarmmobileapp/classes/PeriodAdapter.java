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
import com.example.alarmmobileapp.interfaces.IntervalApi;
import com.example.alarmmobileapp.interfaces.RecyclerViewInterface;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder> {

    private static final String BASE_URL = "http://10.0.2.2:5000";
    public List<Period> periods;
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

        holder.itemView.setOnLongClickListener(v -> {
            if (context instanceof AlarmActivity) {
                ((AlarmActivity) context).showDeleteConfirmationDialog(position);
            }
            return true;
        });

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

            SendRequest(period);
        });

    }


    public static void  SendRequest(Period period)
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IntervalApi apiService = retrofit.create(IntervalApi.class);

        IntervalRequest intervalRequest = new IntervalRequest(
                DayOfWeek.dayToInt(period.getDaysOfWeek().get(0)),
                period.parseHour(period.getStartOfPeriod()),
                period.parseMinute(period.getStartOfPeriod()),
                DayOfWeek.dayToInt(period.getDaysOfWeek().get(period.getDaysOfWeek().size()-1)),
                period.parseHour(period.getEndOfPeriod()),
                period.parseMinute(period.getEndOfPeriod()));


        Call<Void> call = null;

        if ((period.isEnabled()))
        {
            call = apiService.addInterval(intervalRequest);
        }
        else
        {
            call = apiService.deleteInterval(intervalRequest);
        }

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("Interval deleted successfully");
                } else {
                    System.out.println("Failed to delete interval: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return periods.size();
    }

    public static class PeriodViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView start;
        public TextView end;
        TextView daysOfWeek;
        public SwitchCompat enabled;

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
