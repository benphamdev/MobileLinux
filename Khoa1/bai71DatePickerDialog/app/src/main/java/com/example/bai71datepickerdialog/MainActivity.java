package com.example.bai71datepickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    final Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat;
    TextView tvSetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        tvSetDate = findViewById(R.id.textview_select_date);
    }

    public void process() {
        tvSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getDate();
                getHour();
            }
        });
    }

    public void getDate() {
        final int day = calendar.get(Calendar.DATE);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                        tvSetDate.setText(dayOfMonth + "/" + month + '/' + year);

                        calendar.set(year, month, dayOfMonth);
                        simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
                        tvSetDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    public void getHour() {
        int hour = calendar.get(Calendar.HOUR),
                minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                        calendar.set(0, 0, 0, hourOfDay, minute);
                        tvSetDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }
}