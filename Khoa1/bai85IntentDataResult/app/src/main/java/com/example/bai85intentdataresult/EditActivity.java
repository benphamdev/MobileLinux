package com.example.bai85intentdataresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText etName;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
        process();
    }

    public void init() {
        etName = findViewById(R.id.edittext_name);
        btnConfirm = findViewById(R.id.button_confirm);
    }

    public void process() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etName.getText()
                                        .toString();
                Intent intent = new Intent();
                intent.putExtra("New Name", fullName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}