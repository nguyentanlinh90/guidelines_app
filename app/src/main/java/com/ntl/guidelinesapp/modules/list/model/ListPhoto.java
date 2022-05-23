package com.ntl.guidelinesapp.modules.list.model;

import java.util.List;

public class ListPhoto {
    private int type;
    private List<Photo> photoList;

    public ListPhoto(int type, List<Photo> photoList) {
        this.type = type;
        this.photoList = photoList;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
