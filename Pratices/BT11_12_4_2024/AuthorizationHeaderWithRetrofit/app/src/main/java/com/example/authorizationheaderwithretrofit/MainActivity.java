package com.example.authorizationheaderwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIService apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getMyProfile("Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ0aGVqYXZhYmFua2luZyIsInN1YiI6IjIyMTYyMDA2QHN0dWRlbnQuaGNtdXRlLmVkdS52biIsImV4cCI6MTcxNTU5MjA2NiwiaWF0IjoxNzE1NTg4NDY2LCJqdGkiOiI2OTczY2NlNC03YjhlLTQ4NWUtYTgxNC1iMmM2ZDNhYWY0NGMiLCJzY29wZSI6IiJ9.5DVRmnHbatNQbtdrhX5WnzvOs9vgo2yrd-EkCuPvaiIj4ubElql6BNdKT2ucCvvUO_1ldNfU2JF3P5nVJG2wRg")
                .enqueue(new Callback<BaseResponse<UserResponse>>() {
                    @Override public void onResponse(
                            Call<BaseResponse<UserResponse>> call,
                            Response<BaseResponse<UserResponse>> response
                    ) {
                        if (response.isSuccessful()) {
                            UserResponse userResponse = response.body().getData();
                            Log.d("User", "onResponse: " + userResponse.getId());
                        }
                        else {
                            Log.d("Error token", "onResponse: " + response.message());
                        }
                    }

                    @Override public void onFailure(
                            Call<BaseResponse<UserResponse>> call, Throwable t
                    ) {
                        t.printStackTrace();
                    }
                });
    }
}