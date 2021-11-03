package com.example.letscook.ui.profile;

import java.util.Date;

public class Account {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String birthDay;
    private String job;
    private String description;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String fullname, String email, String birthDay, String job, String description) {
        this.fullname = fullname;
        this.email = email;
        this.birthDay = birthDay;
        this.job = job;
        this.description = description;
    }

    public Account(String username, String password, String fullname, String email, String birthDay, String job, String description) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.birthDay = birthDay;
        this.job = job;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
