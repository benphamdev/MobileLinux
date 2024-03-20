package com.example.bai8callnopathandnoparam.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MyResponse {
    @SerializedName("current_user_url")
    private String currentUserUrl;

    public MyResponse(String currentUserUrl) {
        this.currentUserUrl = currentUserUrl;
    }

//    getter and setter
    public String getCurrentUserUrl() {
        return currentUserUrl;
    }

    public void setCurrentUserUrl(String currentUserUrl) {
        this.currentUserUrl = currentUserUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "MyResponse{" +
                "currentUserUrl='" + currentUserUrl + '\'' +
                '}';
    }
}
