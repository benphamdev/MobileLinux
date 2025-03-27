package com.example.bai9mapobjectfrresponsejsonn.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String login;
    private int id;
    @SerializedName("site_admin")
    private boolean siteAdmin;

}
