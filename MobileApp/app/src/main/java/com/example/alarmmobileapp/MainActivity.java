package com.example.alarmmobileapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private ImageButton imageButtonBell;
    private MediaPlayer bellSound;

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
        imageButtonBell = findViewById(R.id.ImageButtonBell);
        bellSound = MediaPlayer.create(this, R.raw.kolokol_v3);

    }
    public void OpenAlarmActivity(View v){
        PlayMusic();
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }
    public void PlayMusic(){
        if(bellSound.isPlaying())
        {
            bellSound.stop();
        }
        bellSound.start();
    }
}