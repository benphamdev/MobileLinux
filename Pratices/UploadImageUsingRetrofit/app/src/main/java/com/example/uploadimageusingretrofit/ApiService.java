package com.example.uploadimageusingretrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @Multipart
    @POST("user/update-avatar")
    Call<BaseResponse<UserResponse>> uploadImage(
            @Part("user_id") RequestBody user_id,
            @Part MultipartBody.Part profilePicture
    );

    @Multipart
    @POST("post/create")
    Call<BaseResponse<com.banking.thejavabanking.models.entity.Post>> createPost(
            @Part("post") RequestBody post,
            @Part MultipartBody.Part file
    );

    @GET("post/list-post")
    Call<BaseResponse<PageResponse<List<PostResponse>>>> getAllPostBySort(
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize
    );}
