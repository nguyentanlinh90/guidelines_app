package com.ntl.guidelinesapp.modules.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String username;
    private String password;
    @SerializedName("avt")
    private String avatar;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
