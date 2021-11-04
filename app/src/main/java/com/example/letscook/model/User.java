package com.example.letscook.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String userAvatar;
    private String dateOfBirth;
    private String job;
    private String userDescription;

    public User() {
    }

    public User(String userId, String username, String email, String password, String userAvatar, String dateOfBirth, String job, String userDescription) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userAvatar = userAvatar;
        this.dateOfBirth = dateOfBirth;
        this.job = job;
        this.userDescription = userDescription;
    }

    public User(String username, String email, String userAvatar, String dateOfBirth, String job, String userDescription) {
        this.username = username;
        this.email = email;
        this.userAvatar = userAvatar;
        this.dateOfBirth = dateOfBirth;
        this.job = job;
        this.userDescription = userDescription;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}
