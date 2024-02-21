package com.example.bai50gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView grTeacher;
    String[] nameTeacher = {"a", "b", "C", "d"};

    ImgAdapter adapter;
    ArrayList<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        grTeacher = findViewById(R.id.gridView);

        images = new ArrayList<>();

        images.add(new Image("itachi", R.drawable.amazon));
        images.add(new Image("amazon", R.drawable.images));
        images.add(new Image("sasuke", R.drawable.dang));
        images.add(new Image("naruto", R.drawable.itachi));
        images.add(new Image("boruto", R.drawable.linux));
        images.add(new Image("linux", R.drawable.ubuntu));
        images.add(new Image("ubuntu", R.drawable.sasuke));
        images.add(new Image("dang", R.drawable.itachi_alt));
        images.add(new Image("kakashi", R.drawable.linux1));
    }

    public void process() {
//        bai49();
        bai50();
    }

    public void bai49() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                nameTeacher
        );
        grTeacher.setAdapter(adapter);

        grTeacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, nameTeacher[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bai50() {
        adapter = new ImgAdapter(MainActivity.this, R.layout.line_img, images);

        grTeacher.setAdapter(adapter);

        grTeacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, images.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}