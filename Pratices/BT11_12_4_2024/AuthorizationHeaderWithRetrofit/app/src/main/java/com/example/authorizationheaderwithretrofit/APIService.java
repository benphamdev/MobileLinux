package com.example.authorizationheaderwithretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIService {
    @GET("user/myProfile")
    Call<BaseResponse<UserResponse>> getMyProfile(@Header("Authorization") String token);
}