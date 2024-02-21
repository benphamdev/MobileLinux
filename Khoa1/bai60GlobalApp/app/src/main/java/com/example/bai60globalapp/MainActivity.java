package com.example.bai60globalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textViewInfor1, textViewInfor2;
    Button btnConfirm;
    EditText editTextName, editTextPhone, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        textViewInfor1 = findViewById(R.id.textViewInfor1);
        textViewInfor2 = findViewById(R.id.textViewInfor2);
        btnConfirm = findViewById(R.id.buttonConfirm);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
    }

    private void process() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString(),
                        phone = editTextPhone.getText().toString(),
                        email = editTextEmail.getText().toString();

                textViewInfor2.setText(getResources().getString(R.string.text_Greet) + " " + name + "\n"
                        + getResources().getString(R.string.text_Phone) + ":" + phone + "\n"
                        + getResources().getString(R.string.text_Email) + ":" + email);
            }
        });
    }
}