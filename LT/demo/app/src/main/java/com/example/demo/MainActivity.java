package com.example.demo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner sp, sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex1);

        sp = findViewById(R.id.sp2);
        sp1 = findViewById(R.id.sp1);

        String[] list = {"PTIT", "HUST", "NEU", "FTU"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item1, list);
        sp.setAdapter(adapter);

        String[] list1 = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.item1, list1);
        sp1.setAdapter(adapter1);
    }
}