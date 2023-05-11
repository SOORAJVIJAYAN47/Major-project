package com.android.pharmaapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AndroidUtil {

    /**
     * Displays a DatePickerDialog and sets the selected date in the specified TextInputEditText
     *
     * @param context   the current application context
     * @param dateInput the TextInputEditText to set the selected date in
     */
    public static void pickDate(Context context, final TextInputEditText dateInput) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Create a DatePickerDialog with the current date as the default selection
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Set the selected date in the TextInputEditText
                calendar.set(year, month, day);
                dateInput.setText(String.format("%1$tY-%1$tm-%1$td", calendar));
            }
        }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    public static void pickDateandTimeFormat(Context context, final EditText timeInput)
    {
        // Initialize a Calendar instance to the current date and time
        Calendar calendar = Calendar.getInstance();

// Initialize a DatePickerDialog to allow the user to pick a date
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Set the selected date on the calendar instance
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Initialize a TimePickerDialog to allow the user to pick a time
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        // Set the selected time on the calendar instance
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        calendar.set(Calendar.MINUTE, minute);

                                        // Format the selected date and time as a string in the desired format
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                        String formattedDateTime = dateFormat.format(calendar.getTime());

                                        // Use the formatted date and time as needed
                                        String startTime = formattedDateTime;
                                        timeInput.setText(startTime);
                                    }
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true);
                        timePickerDialog.show();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public static void pickTimeFormat(Context context, final EditText timeInput) {
        // Get the current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog with the current time as the default selection
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // Set the selected time in the TextInputEditText
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                timeInput.setText(String.format("%1$tH:%1$tM", calendar));
            }
        }, hour, minute, true);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
    /**
     * Displays a TimePickerDialog and sets the selected time in the specified TextInputEditText
     *
     * @param context   the current application context
     * @param timeInput the TextInputEditText to set the selected time in
     */
    public static void pickTime(Context context, final TextInputEditText timeInput) {
        // Get the current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog with the current time as the default selection
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // Set the selected time in the TextInputEditText
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                timeInput.setText(String.format("%1$tH:%1$tM", calendar));
            }
        }, hour, minute, true);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    public static int getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return Integer.parseInt(sharedPreferences.getString("userid", null));
    }
}

