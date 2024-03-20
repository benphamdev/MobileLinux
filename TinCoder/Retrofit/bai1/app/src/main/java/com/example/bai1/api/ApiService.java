package com.example.bai1.api;

import com.example.bai1.model.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    //    Link API https://dummyjson.com/comments/1
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                 .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/comments/{id}")
    Call<Comment> getComment(
            @Path("id") int id
    );

    //    https://dummyjson.com/comments
    @GET("/comments")
    Call<List<Comment>> getComments();
}
