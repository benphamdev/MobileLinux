package com.example.bai8callnopathandnoparam;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai8callnopathandnoparam.api.ApiService;
import com.example.bai8callnopathandnoparam.model.MyResponse;
import com.example.bai8callnopathandnoparam.model.MyResponse1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnCallApi;
    EditText edtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
//        callApi();
        callApi1();
    }

    private void setUpUI() {
        btnCallApi = findViewById(R.id.btn_call_api);
        edtLogin = findViewById(R.id.edt_number);
    }

    private void callApi() {
        ApiService.apiService
                .getRepos()
                .enqueue(new Callback<MyResponse>() {
                    @Override
                    public void onResponse(
                            Call<MyResponse> call, Response<MyResponse> response
                    ) {
                        if (response.isSuccessful()) {
                            MyResponse myResponse = response.body();
                            Log.d("MainActivity", myResponse.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<MyResponse> call, Throwable t) {
                        Log.e("MainActivity", t.getMessage());
                    }
                });
    }

    private void callApi1() {
        btnCallApi.setOnClickListener(v -> {
            ApiService.apiService
                    .getRepos1(Integer.parseInt(edtLogin.getText()
                                                        .toString()))
                    .enqueue(new Callback<MyResponse1>() {
                        @Override
                        public void onResponse(
                                Call<MyResponse1> call, Response<MyResponse1> response
                        ) {
                            if (response.isSuccessful()) {
                                MyResponse1 myResponse = response.body();
                                Log.d("MainActivity", myResponse.toString());
                                edtLogin.setText(myResponse.getLogin());
                            }
                        }

                        @Override
                        public void onFailure(Call<MyResponse1> call, Throwable t) {
                            Log.e("MainActivity", t.getMessage());
                        }
                    });
        });

    }
}