package com.example.bai111jsonobject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnConfirm;
    ExecutorService service;
    Handler handler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        process();
    }

    private void setupUI() {
        btnConfirm = findViewById(R.id.button_confirm);
        textView = findViewById(R.id.textView);
        service = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    private void process() {
        btnConfirm.setOnClickListener(v -> readJsonObject());
    }

    public void readJsonObject() {
        service.execute(() -> {
            StringBuilder ans = new StringBuilder();
            try {
                // object
//                String stringUrl =
//                        "https://jsonplaceholder.typicode.com/posts/1";
                // array nested object
                String stringUrl = "https://api.github.com/users/hadley/orgs";
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

                handler.post(() -> {
                    pasreJSon(ans.toString());
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void pasreJSon(String s) {
        try {
//            json object
//            JSONObject object = new JSONObject(s);

            // json array
//            JSONObject jsonArr = object.jsonArray("")

            JSONArray jsonArray = new JSONArray(s);
            Toast.makeText(this, String.valueOf(jsonArray), Toast.LENGTH_LONG)
                 .show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objectItem = jsonArray.getJSONObject(i);
                String name = objectItem.getString("login");
                Toast.makeText(this, name, Toast.LENGTH_SHORT)
                     .show();
            }

//            String userId = object.getString("userId"),
//                    title = object.getString("title");

            textView.setText(s);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}