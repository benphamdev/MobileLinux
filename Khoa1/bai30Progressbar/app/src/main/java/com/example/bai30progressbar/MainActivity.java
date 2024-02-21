package com.example.bai30progressbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnDowload;
    ProgressBar pbProcess;
    SeekBar sbSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        process();
    }

    public void init() {
        btnDowload = findViewById(R.id.btnDowload);
        pbProcess = findViewById(R.id.progressBar2);
        sbSound = findViewById(R.id.seekBarSound);
    }

    public void process() {
//        bai30();
        bai32();
    }

    public void bai30() {
        btnDowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int tmp = pbProcess.getProgress();
                        if (tmp >= pbProcess.getMax())
                            tmp = 0;
                        pbProcess.setProgress(tmp + 10);
                    }

                    @Override
                    public void onFinish() {
                        this.start();
                        Toast.makeText(MainActivity.this, "Time out", Toast.LENGTH_SHORT).show();
                    }
                };

                countDownTimer.start();
//                while (tmp < pbProcess.getMax()) {
//                    tmp += 10;
//                    pbProcess.setProgress(tmp);
//                }
            }
        });
    }

    public void bai32() {
        sbSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("CCC", "value : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("CCC", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("CCC", "Stop");
            }
        });
    }

}