package com.example.bai4p2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextTime, editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        editTextTime = findViewById(R.id.etTime);
        editTextDate = findViewById(R.id.etDate);
        editTextTime.setOnClickListener(this);
        editTextDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        if (v == editTextTime) {
            int hh = c.get(Calendar.HOUR_OF_DAY), mm =
                    c.get(Calendar.MINUTE);
            TimePickerDialog t1 = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            editTextTime.setText(hourOfDay + " : " + minute);
                        }
                    }, hh, mm, false);
            t1.show();
        }

        if (v == editTextDate) {
            int y = c.get(Calendar.YEAR),
                    m = c.get(Calendar.MONTH),
                    d = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog d1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, y, m, d);
            d1.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}