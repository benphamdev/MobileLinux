package com.example.bai56customcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }
    public void init(){
        img = findViewById(R.id.imageView);
        btn = findViewById(R.id.btnClip);
    }
    public void process(){
//        img.setImageLevel(10000);

        ClipDrawable clipDrawable = (ClipDrawable) img.getDrawable();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        int cur = clipDrawable.getLevel();
                        if(cur >= 10000){
                            cur = 0;
                            return;
                        }
                        img.setImageLevel(cur + 1000);
                        handler.postDelayed(this,1000);
                    }
                }, 2000);
            }
        });
    }
}