package com.example.alarmmobileapp.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alarmmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class DaysOfWeekAdapter extends ArrayAdapter<String> {
    private final List<String> days;
    private final boolean[] checkedStates;

    public DaysOfWeekAdapter(@NonNull Context context, List<String> days) {
        super(context, R.layout.days_of_week_list_item, days);
        this.days = days;
        this.checkedStates = new boolean[days.size()];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.days_of_week_list_item, parent, false);
        }

        TextView dayName = convertView.findViewById(R.id.day_name);
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        dayName.setText(days.get(position));
        checkBox.setChecked(checkedStates[position]);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedStates[position] = isChecked;
        });

        return convertView;
    }

    public List<String> getSelectedDays() {
        List<String> selectedDays = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            if (checkedStates[i]) {
                selectedDays.add(days.get(i));
            }
        }
        return selectedDays;
    }
}