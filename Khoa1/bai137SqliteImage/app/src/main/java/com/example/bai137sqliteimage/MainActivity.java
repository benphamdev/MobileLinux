package com.example.bai137sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        addEvent();
    }

    public void setUpUI(){
        btnAdd = findViewById(R.id.button_add);
    }

    public void addEvent(){
        btnAdd.setOnClickListener(v -> {
            // Mở màn hình thêm dữ liệu
            startActivity(new Intent(MainActivity.this, AddImageItem.class));
        });
    }
}