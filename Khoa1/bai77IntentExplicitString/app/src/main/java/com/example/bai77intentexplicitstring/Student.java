package com.example.bai77intentexplicitstring;

import java.io.Serializable;

public class Student implements Serializable {
    private String fullName;
    private int birth;

    public Student(String fullName, int birth) {
        this.fullName = fullName;
        this.birth = birth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }
}
