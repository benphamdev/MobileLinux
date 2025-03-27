package com.example.bai10headerrequest.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request();
        Request.Builder builder = newRequest.newBuilder();
        builder.addHeader("apikey", "l9FcFJMoWIzM9PPIYJ36enyisMuo9yCT");
        return chain.proceed(builder.build());
    }
}
