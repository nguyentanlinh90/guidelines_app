package com.ntl.guidelinesapp.modules.send_data_activity_to_fragment;

import java.io.Serializable;

public class User implements Serializable {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
