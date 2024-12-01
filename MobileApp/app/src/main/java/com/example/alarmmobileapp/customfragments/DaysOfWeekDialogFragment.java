    package com.example.alarmmobileapp.customfragments;

    import android.app.AlertDialog;
    import android.app.Dialog;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.DialogFragment;

    import com.example.alarmmobileapp.CreatePeriodItemActivity;
    import com.example.alarmmobileapp.PeriodItemActivity;
    import com.example.alarmmobileapp.R;
    import com.example.alarmmobileapp.classes.DayOfWeek;
    import com.example.alarmmobileapp.classes.DaysOfWeekAdapter;
    import com.example.alarmmobileapp.classes.Period;

    import java.util.Arrays;
    import java.util.List;

    public class DaysOfWeekDialogFragment extends DialogFragment {

        private ListView listOfDays;
        String[] daysArray = new String[] {DayOfWeek.MONDAY.toString(),
                DayOfWeek.TUESDAY.toString(),
                DayOfWeek.WEDNESDAY.toString(),
                DayOfWeek.THURSDAY.toString(),
                DayOfWeek.FRIDAY.toString(),
                DayOfWeek.SATURDAY.toString(),
                DayOfWeek.SUNDAY.toString(),
        };

        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.days_dialog, null);
            listOfDays = dialogView.findViewById(R.id.list_of_days);

            Period period = (Period) getArguments().getSerializable("period");

            DaysOfWeekAdapter adapter = new DaysOfWeekAdapter(getActivity(), Arrays.asList(daysArray),period);
            listOfDays.setAdapter(adapter);

            builder.setView(dialogView)
                    .setTitle("Выберите дни недели")
                    .setPositiveButton("OK", (dialog, which) -> {
                        List<String> selectedDays = adapter.getSelectedDays();
                        if (getActivity() instanceof PeriodItemActivity) {
                            ((PeriodItemActivity) getActivity()).updateSelectedDays(selectedDays);
                        }
                        if (getActivity() instanceof CreatePeriodItemActivity) {
                            ((CreatePeriodItemActivity) getActivity()).updateSelectedDays(selectedDays);
                        }
                    })
                    .setNegativeButton("Отмена", null);

            return builder.create();
        }




    }
