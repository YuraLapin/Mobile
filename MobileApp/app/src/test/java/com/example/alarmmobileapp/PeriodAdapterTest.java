package com.example.alarmmobileapp;

import android.content.Context;
import android.view.View;

import com.example.alarmmobileapp.classes.Period;
import com.example.alarmmobileapp.classes.PeriodAdapter;
import com.example.alarmmobileapp.interfaces.RecyclerViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class PeriodAdapterTest {

    @Mock
    private Context mockContext;

    @Mock
    private RecyclerViewInterface mockRecyclerViewInterface;

    private PeriodAdapter periodAdapter;
    private List<Period> periods;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        periods = new ArrayList<>();
        periodAdapter = new PeriodAdapter(mockContext, periods, mockRecyclerViewInterface);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(0, periodAdapter.getItemCount());
        Period p1 = new Period(1, "Test Period1", "08:00", "09:00", new ArrayList<>(), true);
        Period p2 = new Period(2, "Test Period2", "10:00", "12:00", new ArrayList<>(), true);
        periods.add(p1);
        periods.add(p2);
        periodAdapter.updatePeriods(periods);
        assertEquals(2, periodAdapter.getItemCount());
    }

    @Test
    public void testUpdatePeriods() {
        List<Period> newPeriods = new ArrayList<>();
        newPeriods.add(new Period(2, "New Period", "10:00", "11:00", new ArrayList<>(), false));
        periodAdapter.updatePeriods(newPeriods);

        assertEquals(1, periodAdapter.getItemCount());
        assertEquals("New Period", periodAdapter.periods.get(0).getName());
    }

    @Test
    public void testOnBindViewHolder() { //проверка отображения данных
        periods.add(new Period(1, "Test Period", "08:00", "09:00", new ArrayList<>(), true));
        periodAdapter.updatePeriods(periods);

        PeriodAdapter.PeriodViewHolder mockViewHolder = new PeriodAdapter.PeriodViewHolder(new View(mockContext), mockRecyclerViewInterface);
        periodAdapter.onBindViewHolder(mockViewHolder, 0);

        assertEquals("Test Period", mockViewHolder.name.getText().toString());
        assertEquals("08:00", mockViewHolder.start.getText().toString());
        assertEquals("09:00", mockViewHolder.end.getText().toString());
    }

    @Test
    public void testOnClick() {
        periods.add(new Period(1, "Test Period", "08:00", "09:00", new ArrayList<>(), true));
        periodAdapter.updatePeriods(periods);

        PeriodAdapter.PeriodViewHolder mockViewHolder = new PeriodAdapter.PeriodViewHolder(new View(mockContext), mockRecyclerViewInterface);
        mockViewHolder.itemView.performClick();

        verify(mockRecyclerViewInterface).onItemClick(0);
    }

    @Test
    public void testOnSwitchCheckedChange() {
        periods.add(new Period(1, "Test Period", "08:00", "09:00", new ArrayList<>(), false));
        periodAdapter.updatePeriods(periods);

        PeriodAdapter.PeriodViewHolder mockViewHolder = new PeriodAdapter.PeriodViewHolder(new View(mockContext), mockRecyclerViewInterface);
        periodAdapter.onBindViewHolder(mockViewHolder, 0);

        mockViewHolder.enabled.setChecked(true);
        mockViewHolder.enabled.performClick();

        assertEquals(true, periods.get(0).isEnabled());
    }
}