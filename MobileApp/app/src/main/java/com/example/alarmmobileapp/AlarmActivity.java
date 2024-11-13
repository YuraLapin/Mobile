package com.example.alarmmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmmobileapp.classes.DayOfWeek;
import com.example.alarmmobileapp.classes.Period;
import com.example.alarmmobileapp.classes.PeriodAdapter;
import com.example.alarmmobileapp.interfaces.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends AppCompatActivity implements RecyclerViewInterface {


    ArrayList<Period> periods = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setPeriodData();
        recyclerView = findViewById(R.id.PeriodRecycleView);
        PeriodAdapter adapter = new PeriodAdapter(this,periods,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void ReturnToMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setPeriodData(){
        List<DayOfWeek>days = new ArrayList<DayOfWeek>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.FRIDAY);
        periods.add(new Period("ABC","19:20","20:00", days, true));
        periods.add(new Period("ABasdC","1:20","3:00", days, true));
        periods.add(new Period("sda","19:20","20:00", days, false));
        periods.add(new Period("qwea","1:20","10:00", days, true));
        periods.add(new Period("gaq","9:20","20:00", days, false));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(AlarmActivity.this, PeriodItemActivity.class);

        intent.putExtra("PERIOD",periods.get(position));

        startActivity(intent);
    }
}