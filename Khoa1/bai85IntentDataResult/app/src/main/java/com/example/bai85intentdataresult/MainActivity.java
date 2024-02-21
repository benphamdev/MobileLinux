package com.example.bai85intentdataresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDis;
    Button btnNav;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        tvDis = findViewById(R.id.textview_display_name);
        btnNav = findViewById(R.id.button_navigate);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // Handle the result here
                        tvDis.setText(data.getStringExtra("New Name"));
                    }
                });
    }

    public void process() {
        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the activity using the launcher
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
}