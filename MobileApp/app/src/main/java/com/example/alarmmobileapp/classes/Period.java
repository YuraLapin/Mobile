package com.example.alarmmobileapp.classes;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Period implements Serializable {

    private String name;
    private String startOfPeriod;
    private String endOfPeriod;
    private List<DayOfWeek> daysOfWeek;
    private boolean enabled;


    public Period(String name, String start, String end, List<DayOfWeek> days, boolean enabled){
        this.name = name;
        this.startOfPeriod = start;
        this.endOfPeriod = end;
        this.daysOfWeek = days;
        this.enabled = enabled;
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

    private String capitalize(String day) {
        if (day == null || day.isEmpty()) {
            return day;
        }
        return day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();
    }

}
