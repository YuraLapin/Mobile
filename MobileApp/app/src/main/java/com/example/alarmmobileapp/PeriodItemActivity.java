package com.example.alarmmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alarmmobileapp.classes.Period;
import com.example.alarmmobileapp.customfragments.DaysOfWeekDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class PeriodItemActivity extends AppCompatActivity {

    private EditText editName;
    private TimePicker timePickerStart;
    private TimePicker timePickerEnd;
    private TextView daysOfWork;

    private ArrayAdapter<String> listAdapter;
    private List<String> selectedDays = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_period_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            editName = findViewById(R.id.editTextName);
            timePickerStart = findViewById(R.id.timePickerStart);
            timePickerEnd = findViewById(R.id.timePickerEnd);
            daysOfWork = findViewById(R.id.daysOfWorklist);

            timePickerStart.setIs24HourView(DateFormat.is24HourFormat(this));
            timePickerEnd.setIs24HourView(DateFormat.is24HourFormat(this));

            timePickerStart.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            timePickerEnd.setHour(hourOfDay);
            timePickerEnd.setMinute(minute);

        });

        Period period = (Period) getIntent().getSerializableExtra("PERIOD");
        if (period != null) {
            editName.setText(period.getName());
            timePickerStart.setHour(period.parseHour(period.getStartOfPeriod()));
            timePickerStart.setMinute(period.parseMinute(period.getStartOfPeriod()));
            timePickerEnd.setHour(period.parseHour(period.getEndOfPeriod()));
            timePickerEnd.setMinute(period.parseMinute(period.getEndOfPeriod()));

        }
    }

    public void OpenDaysDialog(View v){
        DaysOfWeekDialogFragment dialog = new DaysOfWeekDialogFragment();
        dialog.show(getSupportFragmentManager(), "days");
    }
    public void updateSelectedDays(List<String> days) {
        selectedDays.clear(); // Очищаем текущий список
        selectedDays.addAll(days); // Добавляем новые выбранные дни
        listAdapter.notifyDataSetChanged(); // Уведомляем адаптер об изменениях
    }




}
