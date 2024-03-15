package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mbottomNavigationView;
    ViewPaperAdapter viewPaperAdapter;
    ViewPager mViewPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        addEvent();

    }

    public void setupUI() {
        mbottomNavigationView = findViewById(R.id.bottom_navigation);
        mViewPaper = findViewById(R.id.view_paper);
    }

    public void addEvent() {
        mbottomNavigationView.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.action_home) {
                        Toast.makeText(this, "Home", Toast.LENGTH_SHORT)
                             .show();
                    } else if (item.getItemId() == R.id.action_my_page) {
                        Toast.makeText(this, "My page", Toast.LENGTH_SHORT)
                             .show();
                    } else if (item.getItemId() == R.id.action_favorite) {
                        Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT)
                             .show();
                    }
                    return true;
                }
        );
    }

    public void setUpViewPager() {
        viewPaperAdapter =
                new ViewPaperAdapter(getSupportFragmentManager().getPrimaryNavigationFragment());
        mViewPaper.setAdapter(viewPaperAdapter);

    }
}