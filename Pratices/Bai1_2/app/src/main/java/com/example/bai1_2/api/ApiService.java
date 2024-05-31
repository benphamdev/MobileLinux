package com.example.bai1_2.api;

import com.example.bai1_2.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

//    API: https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl("https://apilayer.net/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ApiService.class);


    //Hàm call API
    @GET("api/live")
    Call<Currency> converUsdToVnd(@Query("access_key") String access_key,
                                  @Query("currencies") String currencies,
                                  @Query("source") String source,
                                  @Query("format") int format);
}
