package com.example.alarmmobileapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
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

public class PeriodItemActivity extends AppCompatActivity {

    private EditText editName;
    private TimePicker timePickerStart;
    private TimePicker timePickerEnd;


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


}
