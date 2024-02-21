package com.example.bai45listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewFruit;
    ArrayList<Fruit> listFruit;
    FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        listViewFruit = findViewById(R.id.listViewFruit);
        listFruit = new ArrayList<>();

        listFruit.add(new Fruit("Dau tay ", "dau tay da lat", R.drawable.img));
        listFruit.add(new Fruit("Cam", "cam sanh ngon lam", R.drawable.img_1));
        listFruit.add(new Fruit("Tao", "tao phu quoc", R.drawable.img_2));
        listFruit.add(new Fruit("Thanh long ", "thanh long binh thuan ", R.drawable.img_3));
        listFruit.add(new Fruit("Le ki ma", "le ki ma nhap ngoai", R.drawable.img_4));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));
        listFruit.add(new Fruit("trai la", "chua biet ten", R.drawable.img_5));

        adapter = new FruitAdapter(this, R.layout.line_fruit, listFruit);
        listViewFruit.setAdapter(adapter);
    }

    public void process() {

    }
}