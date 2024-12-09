package com.example.alarmmobileapp;
import static org.junit.Assert.*;

import com.example.alarmmobileapp.classes.DayOfWeek;

import org.junit.Test;
public class DayOfWeekTest {

    @Test
    public void testToString() {
        assertEquals("Понедельник", DayOfWeek.MONDAY.toString());
        assertEquals("Вторник", DayOfWeek.TUESDAY.toString());
        assertEquals("Среда", DayOfWeek.WEDNESDAY.toString());
        assertEquals("Четверг", DayOfWeek.THURSDAY.toString());
        assertEquals("Пятница", DayOfWeek.FRIDAY.toString());
        assertEquals("Суббота", DayOfWeek.SATURDAY.toString());
        assertEquals("Воскресенье", DayOfWeek.SUNDAY.toString());
    }

    @Test
    public void testFromString() {
        assertEquals(DayOfWeek.MONDAY, DayOfWeek.fromString("Понедельник"));
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.fromString("Вторник"));
        assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.fromString("Среда"));
        assertEquals(DayOfWeek.THURSDAY, DayOfWeek.fromString("Четверг"));
        assertEquals(DayOfWeek.FRIDAY, DayOfWeek.fromString("Пятница"));
        assertEquals(DayOfWeek.SATURDAY, DayOfWeek.fromString("Суббота"));
        assertEquals(DayOfWeek.SUNDAY, DayOfWeek.fromString("Воскресенье"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringInvalid() {
        DayOfWeek.fromString("Неизвестный день");
    }

    @Test
    public void testDayToShortString() {
        assertEquals("Пн", DayOfWeek.dayToShortString(DayOfWeek.MONDAY));
        assertEquals("Вт", DayOfWeek.dayToShortString(DayOfWeek.TUESDAY));
        assertEquals("Ср", DayOfWeek.dayToShortString(DayOfWeek.WEDNESDAY));
        assertEquals("Чт", DayOfWeek.dayToShortString(DayOfWeek.THURSDAY));
        assertEquals("Пт", DayOfWeek.dayToShortString(DayOfWeek.FRIDAY));
        assertEquals("Сб", DayOfWeek.dayToShortString(DayOfWeek.SATURDAY));
        assertEquals("Вс", DayOfWeek.dayToShortString(DayOfWeek.SUNDAY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDayToShortStringInvalid() {
        DayOfWeek.dayToShortString(null);
    }
}