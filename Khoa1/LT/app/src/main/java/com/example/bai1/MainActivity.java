package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constrainlayout);

        String fullName = "pham duy chien";
        int n = 2003;
        if(true){
            Log.d("full Name", fullName + " "  + n);
        }
    }
}