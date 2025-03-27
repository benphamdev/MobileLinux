package com.example.bai10headerrequest.model;

public class ExchangeRate {
    private String base;
    private String date;

    public ExchangeRate(String base, String date) {
        this.base = base;
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
