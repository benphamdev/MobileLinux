package com.example.bai137v2;

public class ItemObject {
    private final int Id;
    private String NameItem;
    private String Description;
    private byte[] Image;

    public ItemObject(int id, String nameItem, String description, byte[] image) {
        Id = id;
        NameItem = nameItem;
        Description = description;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public String getNameItem() {
        return NameItem;
    }

    public void setNameItem(String nameItem) {
        NameItem = nameItem;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
