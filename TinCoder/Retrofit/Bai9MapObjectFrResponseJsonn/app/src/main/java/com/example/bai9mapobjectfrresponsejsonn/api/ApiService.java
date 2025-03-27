package com.example.bai9mapobjectfrresponsejsonn.api;

import com.example.bai9mapobjectfrresponsejsonn.model.ListNationData;
import com.example.bai9mapobjectfrresponsejsonn.model.Person;
import com.example.bai9mapobjectfrresponsejsonn.model.TotalData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                 .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("posts")
    Call<List<Person>> getPersons();

    @GET("https://datausa.io/api/data?drilldowns=Nation&measures=Population")
    Call<ListNationData> getNations();

    @GET("https://api.coindesk.com/v1/bpi/currentprice.json")
    Call<TotalData> getTotalData();

}
