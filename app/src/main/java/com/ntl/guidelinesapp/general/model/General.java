package com.ntl.guidelinesapp.general.model;

import java.io.Serializable;

public class General implements Serializable {
    private String name;

    public General(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
