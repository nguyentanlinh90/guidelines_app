package com.ntl.guidelinesapp.modules.content_provider;

public class SMS {
    private String address;
    private String date;
    private String body;

    public SMS(String address, String date, String body) {
        this.address = address;
        this.date = date;
        this.body = body;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
