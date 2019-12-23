package com.thao.penzu.message.request;

public class TagForm {
    private String name;

    public TagForm() {
    }

    public TagForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
