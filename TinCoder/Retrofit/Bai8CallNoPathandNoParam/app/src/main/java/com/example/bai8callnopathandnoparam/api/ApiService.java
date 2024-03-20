package com.example.bai8callnopathandnoparam.api;

import com.example.bai8callnopathandnoparam.model.MyResponse;
import com.example.bai8callnopathandnoparam.model.MyResponse1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_URL = "https://api.github.com";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/")
    Call<MyResponse> getRepos();

//    https://api.github.com/users/1
    @GET("/users/{id}")
    Call<MyResponse1> getRepos1(@Path("id") int id);
}
