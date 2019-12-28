package com.thao.penzu.message.request;

public class SearchUserByName {
    private String name;

    public SearchUserByName(String name) {
        this.name = name;
    }

    public SearchUserByName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
