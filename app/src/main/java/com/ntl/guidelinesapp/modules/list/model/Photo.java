package com.ntl.guidelinesapp.modules.list.model;

import java.io.Serializable;

public class Photo implements Serializable {
    public static int TYPE_GRID = 1;
    public static int TYPE_LIST = 2;
    public static int TYPE_STAGGERED = 3;

    private int resource;
    private String url;
    private int type;

    public Photo(String url) {
        this.url = url;
    }

    public Photo(int resource) {
        this.resource = resource;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
