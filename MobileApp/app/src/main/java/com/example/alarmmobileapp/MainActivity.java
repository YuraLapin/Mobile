package com.example.alarmmobileapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Button setAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        setAlarm= findViewById(R.id.alarmButton);
//
//        setAlarm.setOnClickListener(v->{
//            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
//                    .setTimeFormat(TimeFormat.CLOCK_24H)
//                    .setHour(12)
//                    .setMinute(0)
//                    .setTitleText("Выберите время для сигнализации")
//                    .build();
//
//            materialTimePicker.addOnPositiveButtonClickListener(view->{
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
//                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
//            });
//
//            materialTimePicker.show(getSupportFragmentManager(), "myTag");
//        });
    }
}