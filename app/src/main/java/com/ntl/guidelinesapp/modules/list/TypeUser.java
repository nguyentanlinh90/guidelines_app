package com.ntl.guidelinesapp.modules.list;

import java.util.List;

public class TypeUser {
    private String type;
    private List<User> list;

    public TypeUser(String type, List<User> list) {
        this.type = type;
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
