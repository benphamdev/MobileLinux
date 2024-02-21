package com.example.bai33cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvPoint;
    SeekBar []s = new SeekBar[3];
    CheckBox []c =  new CheckBox[3];
    ImageButton imgBtn;
    private  CountDownTimer countDownTimer;
    int point = 100, k = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init(){
        tvPoint = findViewById(R.id.textView);
        c[0] = findViewById(R.id.checkBoxNV1);
        c[1] = findViewById(R.id.checkBoxNV2);
        c[2] = findViewById(R.id.checkBoxNV3);
        s[0] = findViewById(R.id.seekBarNV1);
        s[1] = findViewById(R.id.seekBarNV2);
        s[2] = findViewById(R.id.seekBarNV3);
        imgBtn = findViewById(R.id.imgBtn);

        for(int i = 0; i < 3; i++)
            s[i].setEnabled(false);
        tvPoint.setText(point + "");
    }
    public void process(){
            countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random rd = new Random();
                int num = 15;
                int []a = {1, 2, 3};
                for(int i = 0; i < 3; i++){
                    a[i] = rd.nextInt(num);
                }

                for(int i = 0; i < 3; i++){
                    notifyFinish(s[i].getProgress(), s[i], countDownTimer, i, k);
                }

                for(int i = 0; i < 3; i++){
                    s[i].setProgress(s[i].getProgress() + a[i]);
                }
            }

            @Override
            public void onFinish() {

            }
        };

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                for(int i = 0; i < 3; i++){
                    if(c[i].isChecked()){
                        flag = 1;
                        k = i;
                        break;
                    }
                }
                if(flag == 1){
                    for(int i = 0; i < 3; i++)
                        s[i].setProgress(0);
                    imgBtn.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    disabelCheckBox();
                }
                else{
                    Toast.makeText(MainActivity.this, "vui long tick", Toast.LENGTH_SHORT).show();
                }
            }
        });

        for(int i = 0; i < 3; i++){
            checked(i);
        }
    }

    public void notifyFinish(int n, SeekBar sb, CountDownTimer cdt, int i, int k){
        if(n >= sb.getMax()){
            cdt.cancel();
            Toast.makeText(MainActivity.this, "s" + (i + 1) +  " Win", Toast.LENGTH_SHORT).show();
            imgBtn.setVisibility(View.VISIBLE);

            if(i == k){
                Toast.makeText(MainActivity.this, "ban da win", Toast.LENGTH_SHORT).show();
                point += 10;
            }
            else{
                Toast.makeText(MainActivity.this, "ban da thua", Toast.LENGTH_SHORT).show();
                point -= 10;
            }
            tvPoint.setText(point + " ");
            enableCheckBox();
        }
    }
    public void checked(int i){
        c[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for(int j = 0; j < 3; j++)
                        if(i != j)
                            c[j].setChecked(false);
                }
            }
        });
    }

    public void enableCheckBox(){
        for(int i = 0; i < 3; i++)
            c[i].setEnabled(true);
    }

    public void disabelCheckBox(){
        for(int i = 0; i < 3; i++)
            c[i].setEnabled(false);
    }
}