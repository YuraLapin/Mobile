package com.example.alarmmobileapp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.example.alarmmobileapp.classes.Period;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PeriodUnitTest {

    private String inputTime;
    private int expectedHour;
    private int expectedMinute;
    private boolean shouldThrow;

    public PeriodUnitTest(String inputTime, int expectedHour, int expectedMinute, boolean shouldThrow) {
        this.inputTime = inputTime;
        this.expectedHour = expectedHour;
        this.expectedMinute = expectedMinute;
        this.shouldThrow = shouldThrow;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"12:30", 12, 30, false},
                {"00:00", 0, 0, false},
                {"23:59", 23, 59, false},
                {"09:15", 9, 15, false},
                {"", 0, 0, true},
                {" ", 0, 0, true},
                {"25:00", 0, 0, true},
                {"12:60", 0, 0, true},
                {null, 0, 0, true}
        });
    }

    @Test
    public void testParseHour(){
        Period period = new Period();
        if(shouldThrow){
            assertThrows(IllegalArgumentException.class, () -> period.parseHour(inputTime));
        } else {
            assertEquals(expectedHour, period.parseHour(inputTime));
        }
    }
    @Test
    public void testParseMinute() {
        Period period = new Period();
        if (shouldThrow) {
            assertThrows(IllegalArgumentException.class, () -> period.parseMinute(inputTime));
        } else {
            assertEquals(expectedMinute, period.parseMinute(inputTime));
        }
    }
}
