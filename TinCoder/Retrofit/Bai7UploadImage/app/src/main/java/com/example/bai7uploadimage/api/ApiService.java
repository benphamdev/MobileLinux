package com.example.bai7uploadimage.api;

import com.example.bai7uploadimage.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    String BASE_URL = "http://app.iotstar.vn/appfoods/";
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("categories.php")
    Call<List<User>> getCategories();

    @Multipart
    @POST("upload.php")
    Call<User> registerAccount(
            @Part(Const.KEY_NAME) RequestBody name,
            @Part MultipartBody.Part images,
            @Part(Const.KEY_IMAGES) RequestBody description
    );

}
