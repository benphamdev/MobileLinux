package com.example.bai97animationalpha;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewAlpha, imageViewRotate, imageViewScale, imgaViewTranslate;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        imageViewAlpha = findViewById(R.id.image_view_alpha);
        imageViewRotate = findViewById(R.id.image_view_rotate);
        imageViewScale = findViewById(R.id.imageView_scale);
        imgaViewTranslate = findViewById(R.id.imageView_translate);
        btnMain = findViewById(R.id.button_main);
    }

    public void process() {
        Animation animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha),
                animationRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate),
                animationScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale),
                animationTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        imageViewAlpha.setOnClickListener(v -> v.startAnimation(animationAlpha));

        imageViewRotate.setOnClickListener(v -> v.startAnimation(animationRotate));

        imageViewScale.setOnClickListener(v -> v.startAnimation(animationScale));

        imgaViewTranslate.setOnClickListener(v -> v.startAnimation(animationTranslate));

        btnMain.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        });
    }
}