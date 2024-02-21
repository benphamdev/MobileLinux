package com.example.bai45listview;

public class Fruit {
    private String nameFruit, description;
    private int imgFruit;

    public Fruit(String nameFruit, String description, int imgFruit) {
        this.nameFruit = nameFruit;
        this.description = description;
        this.imgFruit = imgFruit;
    }

    public String getNameFruit() {
        return nameFruit;
    }

    public void setNameFruit(String nameFruit) {
        this.nameFruit = nameFruit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgFruit() {
        return imgFruit;
    }

    public void setImgFruit(int imgFruit) {
        this.imgFruit = imgFruit;
    }
}
