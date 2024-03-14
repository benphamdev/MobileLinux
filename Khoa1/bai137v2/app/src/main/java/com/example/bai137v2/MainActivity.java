package com.example.bai137v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ImageDbHelper database;
    //    public static ItemContract itemContract;
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

        initDatabase();
        showData();

    }

    public void initDatabase() {
        database = new ImageDbHelper(this, "ImageManage.sqlite", null, 1);
        database.queryData(
                "CREATE TABLE IF NOT EXISTS Item (Id INTEGER PRIMARY KEY AUTOINCREMENT, NameItem VARCHAR(100),  Description VARCHAR(250), Image BLOB)");
    }

    public void showData() {
        // Get data from SQLite
        Cursor dataItem = database.getData("SELECT * FROM Item");
        while (dataItem.moveToNext()) {
            listItems.add(new ItemObject(
                    dataItem.getInt(0),
                    dataItem.getString(1),
                    dataItem.getString(2),
                    dataItem.getBlob(3)
            ));
        }
        itemAdapter.notifyDataSetChanged();
    }
}