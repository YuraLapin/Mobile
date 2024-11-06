package com.example.alarmmobileapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alarmmobileapp.classes.Period;

public class PeriodItemActivity extends AppCompatActivity {

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



        Period period = (Period)getIntent().getSerializableExtra("PERIOD");

        TextView nameTextView = findViewById(R.id.textViewName);
        TextView startTextView = findViewById(R.id.textViewStart);
        TextView endTextView = findViewById(R.id.textViewEnd);
        //TextView enabledTextView = findViewById(R.id.textViewEnabled);

        nameTextView.setText(period.getName());
        startTextView.setText(period.getStartOfPeriod());
        endTextView.setText(period.getEndOfPeriod());


    }






}