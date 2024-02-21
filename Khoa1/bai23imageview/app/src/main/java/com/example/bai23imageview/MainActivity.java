package com.example.bai23imageview;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    ConstraintLayout manhinh;
    ArrayList<Integer> arrayImage;

    Switch swWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        imgView = findViewById(R.id.imgview);
        manhinh = findViewById(R.id.manhinh);
        swWifi = findViewById(R.id.switchWifi);
        swWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "bat wifi", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "tat wifi ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void process() {
//        bai26();
        bai27();
    }

    public void bai26() {
        //        manhinh.setBackgroundColor(Color.rgb(123, 33, 200));

        imgView.setImageResource(R.drawable.img);

        arrayImage = new ArrayList<>();
        arrayImage.add(R.drawable.img_20240124_204844);
        arrayImage.add(R.drawable.img_20240124_205218);
        arrayImage.add(R.drawable.img_20240124_205652);
        arrayImage.add(R.drawable.img_20240124_205712);

        Random rd = new Random();

        int idx = rd.nextInt(arrayImage.size());

//        manhinh.setBackgroundResource(R.drawable.img_1);
        manhinh.setBackgroundResource(arrayImage.get(idx));
    }

    public void bai27() {
        swWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "bat wifi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "tat wifi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}