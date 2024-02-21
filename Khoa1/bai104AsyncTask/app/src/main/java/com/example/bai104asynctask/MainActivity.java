package com.example.bai104asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button solveButton;
    private TextView informationTextView;
    private ExecutorService executor;
    private ImageView imgView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupProcess();

    }

    private void setupUI() {
        solveButton = findViewById(R.id.button_solve);
        informationTextView = findViewById(R.id.textView_infor);
        imgView = findViewById(R.id.imageView_load);

        // Create a new single-threaded ExecutorService
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    private void setupProcess() {
//        bai104();
//        bai105();
        bai106();
    }

    public void bai104() {
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Execute the background task when the button is clicked
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Perform background task
                        doBackgroundTask();
                    }
                });
            }
        });
    }

    private void doBackgroundTask() {
        // Update UI to indicate the start of the task
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                informationTextView.setText("Starting task\n");
            }
        });

        // Simulate a background task
        for (int i = 0; i < 5; i++) {
            // Simulate work
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update UI with progress
            final String progress = "hello " + i + "\n";
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    informationTextView.append(progress);
                }
            });
        }

        // Update UI to indicate task completion
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                informationTextView.append("Task completed\n");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown the executor when the activity is destroyed
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void bai105() {
        solveButton.setOnClickListener(v -> dowloadImage());
    }

    public void dowloadImage() {
        Bitmap[] img = {null};
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String imgURL =
                            "https://i.pinimg.com/564x/a3/dd/2c/a3dd2c9d7028a36f89291ac308497cca.jpg";
                    URL url = new URL(imgURL);
                    HttpURLConnection httpURLConnection =
                            (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    img[0] = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imgView.setImageBitmap(img[0]);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void bai106() {
        solveButton.setOnClickListener(v -> getContentBrowser());
    }

    private void getContentBrowser() {
        // su dung lambda chu y
        final StringBuilder[] stringBuilder = {new StringBuilder()};
        executor.execute(() -> {
            String stringUrl =
                    "https://stackoverflow.com/questions/14418021/get-text-from-web-page-to-string?rq=3";
            URL url = null;
            try {
                url = new URL(stringUrl);
                URLConnection httpURLConnection = url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder[0].append(line)
                                    .append("\n");
                }

                bufferedReader.close();

                runOnUiThread(() -> {
                    Toast.makeText(this, stringBuilder[0].toString(), Toast.LENGTH_SHORT)
                         .show();
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
