package com.example.alarmmobileapp.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Period implements Serializable {

    private int id;
    private String name;
    private String startOfPeriod;
    private String endOfPeriod;
    private List<DayOfWeek> daysOfWeek;
    private boolean enabled;

    public Period(){

        this.name = "";
        this.startOfPeriod = "";
        this.endOfPeriod = "";
        this.daysOfWeek = new ArrayList<DayOfWeek>();
        this.enabled = false;
    }

    public Period(int id, String name, String start, String end, List<DayOfWeek> days, boolean enabled){
        this.id = id;
        this.name = name;
        this.startOfPeriod = start;
        this.endOfPeriod = end;
        this.daysOfWeek = days;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setStartOfPeriod(String startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public String getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setEndOfPeriod(String endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }

    public String getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public List<String> getDaysToString(List<DayOfWeek> daysOfWeek) {
        return daysOfWeek.stream()
                .map(day -> capitalize(day.name()))
                .collect(Collectors.toList());
    }

    public String capitalize(String day) {
        if (day == null || day.isEmpty()) {
            return day;
        }
        return day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();
    }

    public int parseHour(String time) {
        if (time != null && time.matches("\\d{1,2}:\\d{1,2}")) {
            return Integer.parseInt(time.split(":")[0]);
        }
        throw new IllegalArgumentException("Invalid time format: " + time);
    }

    public int parseMinute(String time) {
        if (time != null && time.matches("\\d{1,2}:\\d{1,2}")) {
            return Integer.parseInt(time.split(":")[1]);
        }
        throw new IllegalArgumentException("Invalid time format: " + time);
    }

}
