package com.example.bai1.model;

public class User {
    //vi du 1
//    private int Id;
//    private String Name;
//    private boolean isActived;
//    private Job job;
//    private List<Favorite> favorites;
//
//    public User() {}
//
//    public User(
//            int id, String name, boolean isActived, Job job, List<Favorite> favorites
//    ) {
//        Id = id;
//        Name = name;
//        this.isActived = isActived;
//        this.job = job;
//        this.favorites = favorites;
//    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        Id = id;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public boolean isActived() {
//        return isActived;
//    }
//
//    public void setActived(boolean actived) {
//        isActived = actived;
//    }
//
//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
//
//    public List<Favorite> getFavorites() {
//        return favorites;
//    }
//
//    public void setFavorites(List<Favorite> favorites) {
//        this.favorites = favorites;
//    }

    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
