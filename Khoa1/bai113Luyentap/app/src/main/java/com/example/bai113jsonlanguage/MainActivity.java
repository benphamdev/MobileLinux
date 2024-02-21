package com.example.bai113jsonlanguage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    ImageButton imbVn, imbEn;
    TextView txvInfor;
    ExecutorService executorService;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupProgress();
    }

    private void setupProgress() {
        imbEn.setOnClickListener(v -> readJsonObject());
    }

    public void readJsonObject() {
        executorService.execute(() -> {
            StringBuilder ans = new StringBuilder();

            try {
                String stringUrl = "https://dummyjson.com/products";
                URL url = new URL(stringUrl);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();

                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    ans.append(line)
                       .append("\n");
                }

                handler.post(() -> parseJson(ans.toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void parseJson(String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray jsonProduct = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonProduct.length(); i++) {
                JSONObject objectItem = jsonProduct.getJSONObject(i);
                String id = objectItem.getString("id"),
                        title = objectItem.getString("title");
                JSONArray img = objectItem.getJSONArray("images");
                txvInfor.append(getLinkImage(img).toString() + "\n");
            }
//            txvInfor.setText(jsonProduct.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getLinkImage(JSONArray img) {
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < img.length(); i++) {
            try {
                a.add(img.getString(i) + "\n");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return a;
    }

    private void setupUI() {
        imbEn = findViewById(R.id.imageButton_en);
        imbVn = findViewById(R.id.imageButton_vn);
        txvInfor = findViewById(R.id.textView_infor);
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }
}