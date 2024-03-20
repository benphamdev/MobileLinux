package com.example.bai8callnopathandnoparam.model;

public class MyResponse1 {
    private String login;

    public MyResponse1(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "MyRespone1{" +
                "login='" + login + '\'' +
                '}';
    }
}
