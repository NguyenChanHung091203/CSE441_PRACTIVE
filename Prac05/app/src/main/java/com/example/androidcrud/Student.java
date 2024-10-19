package com.example.androidcrud;

import java.io.Serializable;

public class Student implements Serializable {
    private String mssv;
    private String name;
    private String studentClass;
    private double gpa;
    private String avatarUrl;

    public Student(String mssv, String name, String studentClass, double gpa, String avatarUrl) {
        this.mssv = mssv;
        this.name = name;
        this.studentClass = studentClass;
        this.gpa = gpa;
        this.avatarUrl = avatarUrl;
    }

    // Getter methods
    public String getMssv() {
        return mssv;
    }

    public String getName() {
        return name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public double getGpa() {
        return gpa;
    }
}
