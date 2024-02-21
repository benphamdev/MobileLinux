package com.example.bai97animationalpha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setUI();
        process();
    }

    public void setUI() {
        btnSecond = findViewById(R.id.button_second);
    }

    public void process() {
        btnSecond.setOnClickListener(v -> {
            startActivity(new Intent(SecondActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        });
    }
}