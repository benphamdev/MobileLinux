package com.example.uploadimageusingretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static String BASE_URL = "http://192.168.1.37:8081/api/v1/";
    private static Retrofit retrofit;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                 .create();

    public static Retrofit getRetrofit() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .writeTimeout(15, TimeUnit.MINUTES) // set timeout to 5 minutes
//                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
