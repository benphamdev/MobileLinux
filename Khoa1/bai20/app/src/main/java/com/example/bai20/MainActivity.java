package com.example.bai20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtNum;
    EditText txtNum1, txtNum2;
    Button btnRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init(){
        txtNum = (TextView) findViewById(R.id.textView);
        btnRandom = (Button) findViewById(R.id.button);
        txtNum1 = (EditText) findViewById(R.id.editTextNum1);
        txtNum2 = (EditText) findViewById(R.id.editTextNum2);
    }
    public void process(){
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();
                try {
                    int tmp1 = Integer.parseInt(txtNum1.getText().toString()),
                            tmp2 = Integer.parseInt(txtNum2.getText().toString());
                    if(tmp1 > tmp2){
                        Toast.makeText(MainActivity.this, "nhap lai", Toast.LENGTH_LONG).show();
                    }
                    else{
                        int num = rd.nextInt((tmp2 - tmp1 + 1) + tmp1);
                        txtNum.setText(num + "");// String.valueOf(num)
                    }
                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this, "nhap lai", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}