package com.example.part4.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id")
    private int Id;
    private String body;
    private int postId;
    private User user;

    public Comment() {
    }

    public Comment(int id, String body, int postId, User user) {
        this.Id = id;
        this.body = body;
        this.postId = postId;
        this.user = user;
    }
    //getter and setter

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
