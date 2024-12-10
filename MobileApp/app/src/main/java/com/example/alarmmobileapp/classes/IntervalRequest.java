package com.example.alarmmobileapp.classes;

public class IntervalRequest {
    private int day_of_week_on;
    private int hour_on;
    private int minute_on;
    private int day_of_week_off;
    private int hour_off;
    private int minute_off;

    public IntervalRequest(int day_of_week_on, int hour_on, int minute_on,
                           int day_of_week_off, int hour_off, int minute_off) {
        this.day_of_week_on = day_of_week_on;
        this.hour_on = hour_on;
        this.minute_on = minute_on;
        this.day_of_week_off = day_of_week_off;
        this.hour_off = hour_off;
        this.minute_off = minute_off;
    }
}
