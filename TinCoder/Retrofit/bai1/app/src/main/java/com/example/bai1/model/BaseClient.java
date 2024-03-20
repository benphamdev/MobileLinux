package com.example.bai1.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class BaseClient {
    private static final HttpLoggingInterceptor sLogging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient.Builder sClient = new OkHttpClient.Builder();

    static <S> S createService(Class<S> serviceClass) {
        if (!sClient.interceptors()
                    .contains(sLogging)) {
            sClient.addInterceptor(sLogging);
        }
        OkHttpClient client = sClient.build();
        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(client)
                .build()
                .create(serviceClass);
    }

}
