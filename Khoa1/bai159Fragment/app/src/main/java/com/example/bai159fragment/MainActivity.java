package com.example.bai159fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button btnAddFragmentA, getBtnAddFragmentB;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        manageFragment();
        setupUI();
        addEvent();
    }

    public void addEvent() {
        btnAddFragmentA.setOnClickListener(v -> addFragment(v));
        getBtnAddFragmentB.setOnClickListener(v -> addFragment(v));
    }

    public void setupUI() {
        btnAddFragmentA = findViewById(R.id.button_add_fragment_a);
        getBtnAddFragmentB = findViewById(R.id.button_add_fragment_b);
    }

    public void addFragment(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        if (v.getId() == R.id.button_add_fragment_a) {
            fragment = new FragmentA();
        } else if (v.getId() == R.id.button_add_fragment_b) {
            fragment = new FragmentB();
        }

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void manageFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();

        fragmentTransaction.add(R.id.frameLayout, fragmentA);
        fragmentTransaction.add(R.id.frameLayout, fragmentB);

        fragmentTransaction.commit();

    }
}