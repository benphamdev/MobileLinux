package com.example.bai70calendar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void process() {
        Calendar calendar = Calendar.getInstance();
        tvTime.append(calendar.getTime() + "\n");
        tvTime.append(calendar.get(Calendar.DATE) + "\n");
        tvTime.append(calendar.get(Calendar.MONTH) + 1 + "\n");
        tvTime.append(calendar.get(Calendar.YEAR) + "\n");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd / MM / YYYY");
        tvTime.append(simpleDateFormat.format(calendar.getTime()) + "\n");

        tvTime.append(calendar.get(Calendar.HOUR) + "\n");
        tvTime.append(calendar.get(Calendar.HOUR_OF_DAY) + "\n");

        simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        tvTime.append(simpleDateFormat.format(calendar.getTime()));
    }

    public void init() {
        tvTime = findViewById(R.id.textview_time);
    }
}