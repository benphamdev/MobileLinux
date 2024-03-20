package com.example.part4.api;

import com.example.part4.model.Comment;
import com.example.part4.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    //    Link API https://jsonplaceholder.typicode.com/posts
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                 .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/comments/{id}")
    Call<Comment> getComment(
            @Path("id") int id
    );

    @POST("/posts")
    Call<Post> createPost(
            @Body Post post
    );
}
