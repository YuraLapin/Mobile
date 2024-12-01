package com.example.alarmmobileapp;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alarmmobileapp.classes.Period;

import java.util.ArrayList;
import java.util.List;

public class CreatePeriodItemActivity extends AppCompatActivity {

    private EditText editName;
    private TimePicker timePickerStart;
    private TimePicker timePickerEnd;
    private TextView daysOfWork;
    Period period;
    private ArrayAdapter<String> listAdapter;
    private List<String> selectedDays = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_period_item);
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

    }







}