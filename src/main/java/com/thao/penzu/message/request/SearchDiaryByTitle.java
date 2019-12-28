package com.thao.penzu.message.request;

public class SearchDiaryByTitle {
    private String title;

    public SearchDiaryByTitle(String title) {
        this.title = title;
    }

    public SearchDiaryByTitle() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

