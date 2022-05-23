package com.ntl.guidelinesapp.modules.firebase.realtime_db;

import java.util.HashMap;
import java.util.Map;

public class Job {
    private String id;
    private String name;

    public Job() {
        // Default constructor required for calls to DataSnapshot.getValue(Job.class)
    }

    public Job(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        return map;
    }
}
