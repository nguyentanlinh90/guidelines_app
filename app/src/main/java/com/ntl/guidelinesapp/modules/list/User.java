package com.ntl.guidelinesapp.modules.list;

public class User {
    private int imgResource;
    private String name;
    private int type;

    public User(int imgResource, String name) {
        this.imgResource = imgResource;
        this.name = name;
    }

    public User(int imgResource, String name, int type) {
        this.imgResource = imgResource;
        this.name = name;
        this.type = type;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
