package com.ntl.guidelinesapp.modules.mvp;

public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public boolean isValidUser(String e, String p) {
        return e.equalsIgnoreCase("justsimple2910@gmail.com") && p.equalsIgnoreCase("123");
    }
}
