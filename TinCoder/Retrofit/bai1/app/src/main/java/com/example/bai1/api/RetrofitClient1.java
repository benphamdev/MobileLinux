package com.example.bai1.api;

import com.example.bai1.model.BaseClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient1 extends BaseClient {
    private final static Gson gson = new GsonBuilder().setDateFormat(
                                                              "yyyy-MM-dd'T'HH:mm:ssZ")
                                                      .create();
    private static ApiService apiService;

    public static ApiService getInstance() {
        if (apiService == null) {
            return apiService = new Retrofit.Builder()
                    .baseUrl("https://dummyjson.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }

}
