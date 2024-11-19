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

import com.example.alarmmobileapp.classes.DayOfWeek;
import com.example.alarmmobileapp.classes.Period;
import com.example.alarmmobileapp.customfragments.DaysOfWeekDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class PeriodItemActivity extends AppCompatActivity {

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

         period = (Period) getIntent().getSerializableExtra("PERIOD");
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
    public void updateSelectedDays(List<String> selectedDays) {

        List<DayOfWeek> daysOfWeek = new ArrayList<>();
        for (String day : selectedDays) {
            switch (day) {
                case "Понедельник":
                    daysOfWeek.add(DayOfWeek.MONDAY);
                    break;
                case "Вторник":
                    daysOfWeek.add(DayOfWeek.TUESDAY);
                    break;
                case "Среда":
                    daysOfWeek.add(DayOfWeek.WEDNESDAY);
                    break;
                case "Четверг":
                    daysOfWeek.add(DayOfWeek.THURSDAY);
                    break;
                case "Пятница":
                    daysOfWeek.add(DayOfWeek.FRIDAY);
                    break;
                case "Суббота":
                    daysOfWeek.add(DayOfWeek.SATURDAY);
                    break;
                case "Воскресенье":
                    daysOfWeek.add(DayOfWeek.SUNDAY);
                    break;
                default:
                    System.out.println("Некорректный день недели: " + day);
                    break;
            }
        }
        if (period != null) {
            period.setDaysOfWeek(daysOfWeek);
        }
        updateDaysOfWorkDisplay();
    }

    private void updateDaysOfWorkDisplay() {
        if (period != null && period.getDaysOfWeek() != null) {
            StringBuilder daysStringBuilder = new StringBuilder();
            for (DayOfWeek day : period.getDaysOfWeek()) {
                daysStringBuilder.append(day.toString()).append(", ");
            }
            if (daysStringBuilder.length() > 0) {
                daysStringBuilder.setLength(daysStringBuilder.length() - 2);
            }
            daysOfWork.setText(daysStringBuilder.toString());
        } else {
            daysOfWork.setText("Нет выбранных дней");
        }
    }


}
