package com.example.bai10headerrequest.api;

import com.example.bai10headerrequest.model.ExchangeRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "https://api.apilayer.com/fixer/";
    //    https://api.apilayer.com/fixer/latest?base=USD&symbols=EUR,GBP
//    apikey : l9FcFJMoWIzM9PPIYJ36enyisMuo9yCT
//    cach3

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                                 .create();
    //    Interceptor interceptor = chain -> {
//        Request newRequest = chain.request();
//        Request.Builder builder = newRequest.newBuilder();
//        builder.addHeader("apikey", "l9FcFJMoWIzM9PPIYJ36enyisMuo9yCT");
//        return chain.proceed(builder.build());
//    };
    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(new AuthenticationInterceptor());
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

//    cach 1
//    @GET("latest")
//    Call<ExchangeRate> getExchangeRate(
//            @Header("apikey") String apiKey,
//            @Query("base") String base,
//            @Query("symbols") String symbols
//    );

//    cach2
//    @GET("latest")
//    @Headers("apikey:l9FcFJMoWIzM9PPIYJ36enyisMuo9yCT")
//    Call<ExchangeRate> getExchangeRate(
//            @Query("base") String base,
//            @Query("symbols") String symbols
//    );

    @GET("latest")
    Call<ExchangeRate> getExchangeRate(
            @Query("base") String base,
            @Query("symbols") String symbols
    );
}
