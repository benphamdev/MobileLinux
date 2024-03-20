package com.example.bai1.model;

public class Favorite {
    private int Id;
    private String FavoriteName;

    public Favorite(int id, String favoriteName) {
        Id = id;
        FavoriteName = favoriteName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFavoriteName() {
        return FavoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        FavoriteName = favoriteName;
    }
}
