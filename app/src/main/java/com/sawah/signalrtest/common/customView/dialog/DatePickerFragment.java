package com.sawah.signalrtest.common.customView.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.sawah.vehicledashboardapp.R;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Turk
 * on 3/13/2018.
 */
@SuppressWarnings("unused")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String CURRENT_SELECTED_DATE = "selectedDate";
    private static final String DATE_FORMAT = "dateFormat";
    private static final String SEPARATOR = "separator";
    private static final String DEFAULT_DATE_LIMIT = "default_date_limit";

    private String format;

    public static DatePickerFragment getInstance(@Nullable String selectedDate,
                                                 int requestCode,
                                                 Fragment fragment, String format,
                                                 String separator,
                                                 int dateYearLimit) {

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle b = new Bundle();
        b.putString(DatePickerFragment.CURRENT_SELECTED_DATE,
                selectedDate);
        b.putString(DatePickerFragment.DATE_FORMAT,
                format);

        b.putString(DatePickerFragment.SEPARATOR,
                separator);
        if (dateYearLimit <= 0) {
            b.putInt(DatePickerFragment.DEFAULT_DATE_LIMIT,
                    0);
        } else {

            b.putInt(DatePickerFragment.DEFAULT_DATE_LIMIT,
                    dateYearLimit);
        }

        datePickerFragment.setArguments(b);
        datePickerFragment.setTargetFragment(fragment, requestCode);
        return datePickerFragment;
    }

    public static long getMinDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, -11);
        return cal.getTime().getTime();
    }

    public static String getCurrentTimeStamp(String format) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker


        String selectedDate = Objects.requireNonNull(getArguments()).getString(CURRENT_SELECTED_DATE);
        format = getArguments().getString(DATE_FORMAT);
        String separator = getArguments().getString(SEPARATOR);

        Calendar calendar = Calendar.getInstance();
        int dateYearLimit = getArguments().getInt(DEFAULT_DATE_LIMIT, 0);
        int year = calendar.get(Calendar.YEAR) - dateYearLimit;

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = getFormattedDate(day, month, year);
        }

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(selectedDate);
            calendar.setTime(Objects.requireNonNull(date));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
        DatePickerDialog datePickerDialog;
        // Create a new instance of DatePickerDialog and return it
        if (Build.VERSION.SDK_INT >= 21) {

            datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.DialogFragmentTheme, this, year, month, day);

        } else {
            datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);

        }

        try {

            if (dateYearLimit > 0) {
                Calendar calendar1 = Calendar.getInstance();
                String dateLimit = getFormattedDate(calendar1.get(Calendar.DAY_OF_MONTH),
                        calendar1.get(Calendar.MONTH)
                        , calendar1.get(Calendar.YEAR) - dateYearLimit);
                @SuppressLint("SimpleDateFormat") DateFormat givenDateFormat = new SimpleDateFormat(format);
                Date givenDateObj = givenDateFormat.parse(dateLimit);
                System.out.print("");
                datePickerDialog.getDatePicker().setMaxDate(Objects.requireNonNull(givenDateObj).getTime());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return datePickerDialog;
    }

    public Date getDateObject(String dateStr) throws Exception {
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateStr);
        cal.setTime(Objects.requireNonNull(date));

        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Intent intent = new Intent();
        //yyyy-MM-dd
        //monthOfYear = monthOfYear + 1;
        String day = String.valueOf(dayOfMonth);
        String month = String.valueOf(monthOfYear);
        if (day.length() == 1) {

            day = "0" + dayOfMonth;
        }
        if (month.length() == 1) {

            month = "0" + monthOfYear;
        }
        String dateRes = getFormattedDate(Integer.parseInt(day), Integer.parseInt(month), year);
        intent.putExtra("date", dateRes);
        Objects.requireNonNull(getTargetFragment()).onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();

    }

    private String getFormattedDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat(format);
        return df.format(cal.getTime());

    }

    @Override
    public void onCancel(@NotNull DialogInterface dialog) {
        super.onCancel(dialog);

        if (getParentFragment() instanceof DialogInterface.OnCancelListener)
            ((DialogInterface.OnCancelListener) getParentFragment()).onCancel(dialog);
    }

}
