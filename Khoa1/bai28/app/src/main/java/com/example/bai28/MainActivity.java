package com.example.bai28;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1, cb2, cb3;
    Button btnConfirm, btnConfirm2;
    RadioGroup rdgrBuoi;

    RadioButton rdBtnSang, rdBtnTrua, rdBtnChieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        btnConfirm = findViewById(R.id.buttonConfirm);
        rdgrBuoi = findViewById(R.id.RdGrBuoi);
        rdBtnSang = findViewById(R.id.radioBtnSang);
        rdBtnTrua = findViewById(R.id.radioButtonTrua);
        rdBtnChieu = findViewById(R.id.radioButtonChieu);
        btnConfirm2 = findViewById(R.id.buttonConfirm2);
    }

    public void process() {
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "da chon ios", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "da bo chon", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = "";
                if (cb1.isChecked())
                    ans += cb1.getText() + " ";
                if (cb2.isChecked())
                    ans += cb2.getText() + " ";
                if (cb3.isChecked())
                    ans += cb3.getText() + " ";

                Toast.makeText(MainActivity.this, ans, Toast.LENGTH_LONG).show();
            }
        });

        bai29();
    }
    public void bai29(){
        rdgrBuoi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int bs = R.id.radioBtnSang,bt = R.id.radioButtonTrua, bc = R.id.radioButtonChieu;
                if (checkedId == bs) {
                    Toast.makeText(MainActivity.this, "sang", Toast.LENGTH_SHORT).show();
                } else if (checkedId == bt) {
                    Toast.makeText(MainActivity.this, "Trua", Toast.LENGTH_SHORT).show();
                } else if (checkedId == bc) {
                    Toast.makeText(MainActivity.this, "Chieu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdBtnSang.isChecked()){
                    Toast.makeText(MainActivity.this, "buoi sang",Toast.LENGTH_SHORT ).show();
                }
                else if(rdBtnTrua.isChecked()){
                    Toast.makeText(MainActivity.this, "buoi trua", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "buoi chieu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
