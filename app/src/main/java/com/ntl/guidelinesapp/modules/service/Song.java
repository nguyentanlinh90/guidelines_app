package com.ntl.guidelinesapp.modules.service;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String single;
    private int resource;
    private int image;

    public Song(String title, String single, int resource, int image) {
        this.title = title;
        this.single = single;
        this.resource = resource;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
