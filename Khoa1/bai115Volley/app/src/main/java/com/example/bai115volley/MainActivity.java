package com.example.bai115volley;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupBase();
    }

    private void setupUI() {
        textView = findViewById(R.id.textView);
    }

    private void setupBase() {
//        stringRequest();
//        jsonObjectRequest();
        jsonArrayRequest();
    }

    public void stringRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://www.google.com123";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT)
                                 .show()
                , error -> {
            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT)
                 .show();
            Log.e("Error", error.toString());
        });

        requestQueue.add(stringRequest);
    }

    public void jsonObjectRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://headers.jsontest.com/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {
            String sub = null;
            try {
                sub = response.getString("Host");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            textView.setText(sub);
        }, error -> {
            textView.setText(error.toString());
            Log.e("Error", error.toString());
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void jsonArrayRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/users/hadley/orgs";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null
                , response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = (JSONObject) response.get(i);
                    String login = jsonObject.getString("login");
                    Toast.makeText(this, login, Toast.LENGTH_LONG)
                         .show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, error -> Log.e("Error", error.toString()));
        
        requestQueue.add(jsonArrayRequest);
    }
}