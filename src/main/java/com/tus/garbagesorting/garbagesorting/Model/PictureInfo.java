package com.tus.garbagesorting.garbagesorting.Model;

public class PictureInfo {
    private int id;
    private String name;
    private String type;
    private String path;
    private int sort_times;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSort_times() {
        return sort_times;
    }

    public void setSort_times(int sort_times) {
        this.sort_times = sort_times;
    }

    public PictureInfo(int id, String name, String type, String path, int sort_times) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.path = path;
        this.sort_times = sort_times;
    }

}
