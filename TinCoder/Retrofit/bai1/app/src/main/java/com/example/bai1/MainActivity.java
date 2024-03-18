package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Job job = new Job(1, "Developer");
        List<Favorite> favorites = List.of(
                new Favorite(1, "Reading"),
                new Favorite(2, "Traveling")
        );
        User user = new User(1, "John", true, job, favorites);
        Gson gson = new Gson();
        String json = gson.toJson(user);
    }
}