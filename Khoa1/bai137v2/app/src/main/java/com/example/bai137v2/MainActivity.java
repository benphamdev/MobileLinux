package com.example.bai137v2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ImageDbHelper database;
    Button btnAdd;
    ListView listView;
    ItemAdapter itemAdapter;
    ArrayList<ItemObject> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        addEvent();
    }

    public void setUpUI() {
        database = new ImageDbHelper(MainActivity.this);
        btnAdd = findViewById(R.id.button_add);
        listView = findViewById(R.id.lv_item);
        listItems = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, R.layout.row_item, listItems);
        listView.setAdapter(itemAdapter);
    }

    public void addEvent() {
        btnAdd.setOnClickListener(v -> {
            // Mở màn hình thêm dữ liệu
            startActivity(new Intent(MainActivity.this, AddImageItem.class));
        });

        database.showData(listItems, itemAdapter);
    }
}