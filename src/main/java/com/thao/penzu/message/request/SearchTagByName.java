package com.thao.penzu.message.request;

public class SearchTagByName {
    private String name;

    public SearchTagByName() {
    }

    public SearchTagByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
