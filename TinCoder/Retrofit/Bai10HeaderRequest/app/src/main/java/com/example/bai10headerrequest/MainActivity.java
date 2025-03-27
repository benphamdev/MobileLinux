package com.example.bai10headerrequest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai10headerrequest.api.ApiService;
import com.example.bai10headerrequest.model.ExchangeRate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        callApi();
    }

    private void setUpUI() {
        btnCallApi = findViewById(R.id.btn_call_api);
    }

    private void callApi() {
        String header = "l9FcFJMoWIzM9PPIYJ36enyisMuo9yCT";
        btnCallApi.setOnClickListener(v -> {
            // Call API here
            ApiService.apiService
                    .getExchangeRate(
                            "USD",
                            "EUR,GBP"
                    )//                    .getExchangeRate(header, "USD", "EUR,GBP")
                    .enqueue(new Callback<ExchangeRate>() {
                        @Override
                        public void onResponse(
                                Call<ExchangeRate> call, Response<ExchangeRate> response
                        ) {
                            if (response.isSuccessful()) {
                                ExchangeRate exchangeRate = response.body();
                                Log.d("RES", "onResponse: " + exchangeRate);
                            }
                        }

                        @Override
                        public void onFailure(Call<ExchangeRate> call, Throwable t) {
                            Log.e("RESTROFTI", "onFailure: " + t.getMessage());
                        }
                    });
        });
    }
}